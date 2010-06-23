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

    public void updateState(){

        state = inferType(arreglos);
        checkList(((Arreglo) state).getSub(), arreglos);

    }

    public void update() {}

    public void finalCheck(Tipo real){

        if(real.asign(state) == null )
            state = null;

    }

    public Tipo inferType(LinkedList lista) {
       if(lista.getFirst() instanceof ASTConst)
           return new Arreglo(lista.size(), ((ASTConst) lista.getFirst()).getState());

       if(lista.getFirst() instanceof ASTLiteralUR)
           return new Arreglo(lista.size(), ((ASTLiteralUR) lista.getFirst()).getState());

       int size = lista.size();

       return new Arreglo(size, inferType((LinkedList) lista.getFirst()));
    }
    
    //@ requires lista != null;
    private void checkList(Tipo t, LinkedList lista) {
	if(t == null)
	    return;
	else {
	    Iterator it = lista.iterator();	    
	    int size;	    
	    Object o = it.next();
	    
	    if(o instanceof ASTExpresion) {
		if(!flag) { 
		    flag = true;
                    if(t.asign(((ASTExpresion) o).getState()) == null)
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
/*
    public void generatePushCastCode(Writer fd, int nextReg, Tipo dest, LinkedList lista){

        Iterator it = lista.iterator();

        while(it.hasNext()){

            Object o = it.next();
	        
            if(o instanceof ASTConst) {

                ASTConst con = (ASTConst) o;
                Basico ba = (Basico) con.getState();
                String reg = AssemblerInfo.getNombresRegAtPos(nextReg); 

                ASTCast cast = AssemblerInfo.checkCast(dest, ba);

                switch(ba.getNBasico()){
                    case 1:
                        fd.write("mov "+reg+", "+ba.getCaseInt()+"\n");
                    case 2:
                        fd.write("mov "+reg+", "+Long.toHexString(Double.doubleToLongBits(ba.getCaseFloat()))+"\n");
                    case 3:
                        if(ba.getCaseBool())
                            fd.write("mov "+reg", 1\n");
                        else
                            fd.write("mov "+reg", 0\n");
                    default:
                        char c = ba.getCaseChar.charAt(0);
                        int ascii = (int) c;
                        fd.write("mov "+reg", "+ Integer.toString(ascii) +"\n");
                }

               if(cast != null)
                   cast.generateCode(fd, nextReg, "", "");

               fd.write("push "+reg+"\n");

            }
            else if(o instanceof ASTLiteralUR)
                (ASTLiteralUR) o).generatePushCastCode(fd, nextReg, dest);
            else
                generatePushCastCode(fd, nextReg, dest, (LinkedList) o);

        }
            
    }

*/

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
		finalList.add(aux);
	    }
	}
    }

    public void generateCode(Writer fd, int nextReg, Tipo type) throws IOException {
	String reg = AssemblerInfo.getNombresRegAtPos(nextReg); 
	String reg1 = AssemblerInfo.getNombresRegAtPos(nextReg + 1); 
	LinkedList elements = new LinkedList();
	calcElements(arreglos, elements);
	Iterator it = elements.iterator();
	int tamBase = ((Arreglo)type).getTipoBase().getTam();
	int offset = 0;
	ASTExpresion aux_expr;
	String si = AssemblerInfo.newLabel();
	String no = AssemblerInfo.newLabel();
	String end = AssemblerInfo.newLabel();
	
	AssemblerInfo.saveReg(fd, nextReg + 1);
	while (it.hasNext()) {
	    aux_expr = it.next();
	    
	    if (aux_expr instanceof Basico) {
		if (((Basico)aux_expr).getState().getNBasico() == 3) {
		    aux_expr.generateCode(fd, nextReg + 1, si, no);
		    fd.write(si + ":\n");
		    fd.write("mov " + reg1 + ", 1\n");    
		    fd.write("jmp " + end + "\n");		    
		    fd.write(no + ":\n");
		    fd.write("mov " + reg1 + ", 0\n");    
		    fd.write(end + ":\n");
		}
		else {
		    aux_expr.generateCode(fd, nextReg + 1, "", "");
		}

		fd.write("mov [" + reg + " - " + offset + "], " + reg1 + "\n");	    
		offset += tamBase;
	    }	    
	}
	AssemblerInfo.restoreReg(fd, nextReg + 1);
    }
}
