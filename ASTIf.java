import java.util.Iterator;
import java.util.LinkedList;
import java.io.*;

public class ASTIf extends ASTInstruccion {

    //@ invariant cond != null;
    //@ invariant bloques != null;
    //@ invariant els != null;    
    private LinkedList cond;
    private LinkedList bloques;
    private ASTBloque els;

    //@ requires co != null;
    //@ requires b != null;
    //@ requires e != null;
    public ASTIf(LinkedList co, LinkedList b, ASTBloque e) {
	super("if");
	cond = co;
	bloques = b;
	els =e;
    }

    //@ requires c != null;    
    public void setCond(LinkedList c) {
	cond =c;
    }

    //@ requires b != null;    
    public void setBloques(LinkedList b) {
	bloques =b;
    }

    //@ requires a != null;    
    public void setEls(ASTBloque a) {
	els = a;
    }

    public LinkedList getCond() {
	return cond;
    }

    public LinkedList getBloques() {
  	return bloques;
    }

    public ASTBloque getEls() {
  	return els;
    }

    public void update() {
	Iterator it = bloques.iterator();
	boolean flag = false;
	ASTBloque b;

	if(els == null)
	    return;

	while(it.hasNext()) {
	    b = (ASTBloque) it.next();
	    if(!b.getIreturn()) {
		flag = true;
		break;
	    }
	}

	if(!flag)
	    ireturn = true;

	it = bloques.iterator();

	while(it.hasNext()) {
	    b = (ASTBloque) it.next();
	    if(!b.getIbreak())
	        return;
	}

	ibreak = true;	
    }
  
    public String printTree() {
	Iterator itcond = cond.iterator();
	Iterator itbloques = bloques.iterator();

	String m = new String("IF( " + ((ASTExpresion)cond.getFirst()).printTree() + ",\n" + ((ASTBloque)bloques.getFirst()).printTree() + ",\n");
	itcond.next();
	itbloques.next();

	while(itcond.hasNext()) {
	    m = m.concat("ELIF( " + ((ASTExpresion)itcond.next()).printTree() + ",\n" + ((ASTBloque)itbloques.next()).printTree() + " ),\n");	    
	}

	if (els != null) {
	    m = m.concat("ELSE( " + els.printTree() + " )");
	}
	else {
	    m = m.substring(0, m.length() - 2);
	}

	return m;

    }

    public void generateCode(Writer fd, int nextReg, String breakLabel) throws IOException {
	try {
	    Iterator itc = cond.iterator();
	    Iterator itb = bloques.iterator();

	    String si = AssemblerInfo.newLabel();
	    String no = AssemblerInfo.newLabel();
	    String end = AssemblerInfo.newLabel();

	    ((ASTExpresion) itc.next()).generateCode(fd,nextReg, si, no);
	    fd.write(si + ":\n");
	    ((ASTBloque) itb.next()).generateCode(fd, nextReg, breakLabel);
	    fd.write("jmp " + end + "\n");

	    while(itc.hasNext()) {
		si = AssemblerInfo.newLabel();
		fd.write(no + ":\n");
		no = AssemblerInfo.newLabel();
		((ASTExpresion) itc.next()).generateCode(fd, nextReg, si, no);
		fd.write(si + ":\n");
		((ASTBloque) itb.next()).generateCode(fd, nextReg, breakLabel);
		fd.write("jmp " + end + "\n");
	    }

	    fd.write(no + ":\n");
	    if(els != null)
		els.generateCode(fd, nextReg, breakLabel);

	    fd.write(end + ":\n");

    	}
    	catch (Exception e) {
            e.printStackTrace();
    	    System.out.println("Error escribiendo en archivo de salida");
    	}
    }
}
