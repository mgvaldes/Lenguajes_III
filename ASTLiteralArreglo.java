import java.util.LinkedList;
import java.util.Iterator;
import java.io.*;

public class ASTLiteralArreglo extends ASTExpresion {

    //@ invariant arreglos != null;
    private LinkedList arreglos;
    private boolean flag;

    //@ requires a != null;
    public ASTLiteralArreglo(LinkedList a) {
  	super("literal", null, null);
	arreglos = a;
	flag = false;
    }

    public void update() {
	if(state != null) {
	    if(state instanceof Arreglo)
		checkList(((Arreglo) state).getSub(), arreglos);
	    else
		state = null;
	}
    }
    
    //@ requires lista != null;
    public void checkList(Tipo t, LinkedList lista) {
	if(t == null)
	    return;
	else {
	    Iterator it = lista.iterator();	    
	    int size;	    
	    Object o = it.next();
	    
	    if(o instanceof ASTConst) {
		if(!flag) { 
		    flag = true;
		    if(t.asign(((ASTConst) o).getState()) == null)
			state = null;
		}		
	    }
	    else {
		LinkedList l = (LinkedList) o;
		size = l.size();
		
		while(it.hasNext()) {
		    l = (LinkedList) it.next();

		    if(l.size() != size) {
			state = null;
			return;
		    }
		    else
			checkList(((Arreglo) t).getSub(),l);
		}        
		    
	    }		
	}

    }
  
    public String printTree() {	
	String m = new String(value);
	return m;
    }

    public void calcElements(LinkedList list, LinkedList finalList) {
	Iterator it = list.iterator();
	Object aux;

	while (it.hasNext()) {
	    aux = it.next();
	    if (aux instanceof LinkedList) {
		calcElements((LinkedList)aux, finalList);
	    }
	    else {
		finalList.add((ASTConst)aux);
	    }
	}
    }

    public void generateCode(Writer fd, int nextReg, Tipo type) throws IOException {
	String reg = AssemblerInfo.getNombresRegAtPos(nextReg); 
	String reg1 = AssemblerInfo.getNombresRegAtPos(nextReg + 1); 
	LinkedList elements = new LinkedList();
	this.calcElements(arreglos, elements);
	Iterator it = elements.iterator();
	int tamBase = ((Arreglo)type).getTipoBase().getTam();
	int offset = 0;

	AssemblerInfo.saveReg(fd, nextReg + 1);
	while (it.hasNext()) {
	    ((ASTConst)it.next()).generateCode(fd, nextReg + 1, "", "");
	    fd.write("mov [" + reg + " + " + offset + "], " + reg1 + "\n");
	    offset += tamBase;
	}
	AssemblerInfo.restoreReg(fd, nextReg + 1);
    }
}