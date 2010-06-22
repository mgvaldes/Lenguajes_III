import java.util.Iterator;
import java.util.LinkedList;
import java.io.*;

public class ASTSwitch extends ASTInstruccion {

    private ASTExpresion exp;
    private LinkedList cases;
    private LinkedList bloques;
    private ASTBloque def;

    public ASTSwitch(ASTExpresion e, LinkedList ca, LinkedList b, ASTBloque d) {
	super("switch");
	exp = e;
	cases = ca;
	bloques = b;
	def = d;
    }

    public void setExp(ASTExpresion e) {
	exp = e;
    }

    public void setCases(LinkedList b) {
	cases = b;
    }

    public void setBloques(LinkedList b) {
	bloques = b;
    }

    public void setDef(ASTBloque a) {
	def = a;
    }

    public ASTExpresion getExp() {
	return exp;
    }

    public LinkedList getCases() {
	return cases;
    }

    public LinkedList getBloques() {
	return bloques;
    }

    public ASTBloque getDef() {
	return def;
    }

    public void update() {

	Iterator it = bloques.iterator();
	boolean flag = false;
	ASTBloque b;

	while(it.hasNext()) {
	    b = (ASTBloque) it.next();
	    if(!b.getIreturn()) {
		flag = true;
		break;
	    }
	}

	if(!def.getIreturn())
	    flag = true;

	if(!flag)
	    ireturn = true;

	it = bloques.iterator();

	while(it.hasNext()) {
	    b = (ASTBloque) it.next();
	    if(!b.getIbreak())
		return;
	}

	if(!def.getIbreak())
	    return;

	ibreak = true;	
    }
  
    public String printTree() {

	Iterator itcases = cases.iterator();
	Iterator itbloques = bloques.iterator();

	String m = new String("SWITCH( " + exp.printTree() + ",\n");

	while(itcases.hasNext()) {
	    m = m.concat("CASE( " + ((ASTConst)itcases.next()).printTree() + ",\n" + ((ASTBloque)itbloques.next()).printTree() + " ),\n");	    
	}

	if (def != null) {
	    m = m.concat("DEFAULT( " + def.printTree() + " )");
	}
	else {
	    m = m.substring(0, m.length() - 2);
	}

	return m;

    }

    public void generateCode(Writer fd, int nextReg, String breakLabel, String returnLabel) throws IOException {
	    String si = AssemblerInfo.newLabel();
	    String no = AssemblerInfo.newLabel();	    
	    String next = AssemblerInfo.newLabel();
	    String end = AssemblerInfo.newLabel();
	    String reg = AssemblerInfo.getNombresRegAtPos(nextReg);
	    String nreg = AssemblerInfo.getNombresRegAtPos(nextReg + 1);
	    Iterator casesIt = cases.iterator();
	    Iterator bloquesIt = bloques.iterator();
	    ASTConst aux;
	    
	    if (cases.size() == 0) {
		def.generateCode(fd, nextReg,breakLabel, returnLabel);
		fd.write("jmp " + end + "\n");
	    }
	    else {				
		if (((Basico)exp.getState()).getNBasico() == 3) {
		    String new_si = AssemblerInfo.newLabel();		    
		    String new_no = AssemblerInfo.newLabel();

		    exp.generateCode(fd, nextReg, si, no);		
		    fd.write(si + ": \n");
		    
		    while (casesIt.hasNext()) {
			fd.write(next + ": \n");
			next = AssemblerInfo.newLabel();

			aux = (ASTConst)casesIt.next();	
			aux.generateCode(fd, nextReg, new_si, next);			

			fd.write(new_si + ": \n");
			((ASTBloque)bloquesIt.next()).generateCode(fd, nextReg,breakLabel, returnLabel);
			fd.write("jmp " + end + "\n");

			new_si = AssemblerInfo.newLabel();
		    }

		    casesIt = cases.iterator();
		    bloquesIt = bloques.iterator();
		    fd.write(no + ": \n");
		    
		    while (casesIt.hasNext()) {
			fd.write(next + ": \n");
			next = AssemblerInfo.newLabel();

			aux = (ASTConst)casesIt.next();		
			aux.generateCode(fd, nextReg, next, new_no);

			fd.write(new_no + ": \n");
			((ASTBloque)bloquesIt.next()).generateCode(fd, nextReg, breakLabel, returnLabel);
			fd.write("jmp " + end + "\n");

			new_no = AssemblerInfo.newLabel();
		    }		    

		    fd.write(next + ": \n");
		    def.generateCode(fd, nextReg,breakLabel, returnLabel);
		    fd.write("jmp " + end + "\n");
		}
		else {
		    exp.generateCode(fd, nextReg, si, no);		
		    
		    while (casesIt.hasNext()) {
			fd.write(next + ": \n");
			next = AssemblerInfo.newLabel();
			
			aux = (ASTConst)casesIt.next();
			AssemblerInfo.saveReg(fd, nextReg + 1);
			aux.generateCode(fd, nextReg + 1, si, no);
			
			fd.write("cmp " + reg + ", " + nreg + "\n");
			AssemblerInfo.restoreReg(fd, nextReg + 1);
			fd.write("jne " + next + "\n");	      		    		    
			((ASTBloque)bloquesIt.next()).generateCode(fd, nextReg + 1, breakLabel, returnLabel);
			fd.write("jmp " + end + "\n");
		    }
		    
		    fd.write(next + ": \n");
		    def.generateCode(fd, nextReg, breakLabel, returnLabel);
		}
	    }
	    
	    fd.write(end + ": \n");	    
    }
}
