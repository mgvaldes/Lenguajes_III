import java.util.LinkedList;
import java.io.*;

public class ASTAsignacionExpr extends ASTExpresion {
    //@ invariant id != null;
    //@ invariant right != null;
  
    private ASTIdentificador id;

    //@ requires i != null;
    //@ requires r != null;
    public ASTAsignacionExpr(ASTIdentificador i, ASTExpresion r, Tipo s) {
	super("=", null, r);
	id = i;
	state = s;
    }

    //@ requires i != null;
    public void setId(ASTIdentificador i) {
	id = i;
    }

    public ASTIdentificador getId() {
	return id;
    }

    public ASTAsignacion getASTAsignacion() {
	LinkedList l = new LinkedList();
	l.add(id);
	return new ASTAsignacion(l, right);
    }

    public void update() {}

    public String printTree() {
	String m = new String(value + "( " + id.printTree() + ", " + right.printTree() + " )");
	return m;
    }

    public void generateCode(Writer fd, int nextReg, String si, String no) throws IOException {

	    String newsi = AssemblerInfo.newLabel();
	    String newno = AssemblerInfo.newLabel();
	    String end = AssemblerInfo.newLabel();
	    String reg = AssemblerInfo.getNombresRegAtPos(nextReg);

            right.generateCode(fd, nextReg, newsi, newno);

            if(right instanceof ASTIdentificador){

                fd.write("mov "+reg+",["+reg+"]\n");

		if(id.getTable().getParent() == null)
		    fd.write("mov [static + " + ((SymVar)id.getTable().getSym(id.getValue())).getOffset() + "], "+reg+"\n");
		else
		    fd.write("mov ["+AssemblerInfo.getFp()+" - " + ((SymVar)id.getTable().getSym(id.getValue())).getOffset() + "], "+reg+"\n");
            
            }	
	    else if (right.getState() instanceof Basico && ((Basico) right.getState()).getNBasico() == 3) {

		fd.write(newsi + ":\n");
		if(id.getTable().getParent() == null)
		    fd.write("mov [static + " + ((SymVar)id.getTable().getSym(id.getValue())).getOffset() + "], 1\n");
		else
		    fd.write("mov ["+AssemblerInfo.getFp()+" - " + ((SymVar)id.getTable().getSym(id.getValue())).getOffset() + "], 1\n");
		fd.write("mov " + reg + ", 1\n");
		fd.write("jmp " + end + "\n");		    

		fd.write(newno + ":\n");
		if(id.getTable().getParent() == null)
		    fd.write("mov [static + " + ((SymVar)id.getTable().getSym(id.getValue())).getOffset() + "], 0\n");
		else
		    fd.write("mov ["+AssemblerInfo.getFp()+" - " + ((SymVar)id.getTable().getSym(id.getValue())).getOffset() + "], 0\n");
		fd.write("mov " + reg + ", 0\n");    

		fd.write(end + ":\n");

	    }
	    else{

		if(id.getTable().getParent() == null)
		    fd.write("mov [static + " + ((SymVar)id.getTable().getSym(id.getValue())).getOffset() + "], "+reg+"\n");
		else
		    fd.write("mov ["+AssemblerInfo.getFp()+" - " + ((SymVar)id.getTable().getSym(id.getValue())).getOffset() + "], "+reg+"\n");

	    }
    }


}
