import java.util.Iterator;
import java.util.LinkedList;
import java.io.*;
	
public class ASTBloque extends ASTInstruccion {

    private SymTable table;
    private LinkedList insts;
	    
    public ASTBloque( LinkedList i, SymTable t) {
	super("bloque");
	insts = i;
	table = t;
    }

    public void setTable(SymTable t) {
	table = t;
    }

    public void setInsts(LinkedList i) {
	insts = i;
    }

    //@ ensures \result != null;    
    public SymTable getTable() {
  	return table;
    }

    public LinkedList getInsts() {
  	return insts;
    }

    public void update(){}
    
    public String printTree() {

	Iterator it = insts.iterator();
	String ainst = new String("");

	while(it.hasNext()) {
	    ainst = ainst.concat(((ASTInstruccion)it.next()).printTree() + ",\n");
	}

	ainst = ainst.substring(0, ainst.length() - 2);
	String m = new String("BLOQUE( " + ainst + " )");
	return m;

    }

    public void generateCode(Writer fd, int nextReg, String breakLabel) throws IOException {
	try {
	    Iterator it = insts.iterator();
	
	    while (it.hasNext()) {
		((ASTInstruccion)it.next()).generateCode(fd, nextReg, breakLabel);
	    }

    	}
    	catch (Exception e) {
    	    System.out.println("Error escribiendo en archivo de salida\n");
    	}
    }
}
