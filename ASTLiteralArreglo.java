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

    public void updateState() {
        state = inferType(arreglos);
        checkList(((Arreglo) state).getSub(), arreglos);

    }

    public LinkedList getArreglos(){
        return arreglos;
    }

    public void update() {}

    public void finalCheck(Tipo real){


        if(real.asign(state) == null )
            state = null;
        else
            refreshState(((Arreglo) real).getSub(), arreglos);

    }

    public void refreshState(Tipo real, LinkedList lista){

	if(real == null)
	    return;
	else {
	    Iterator it = lista.iterator();	    
	    Object o = it.next();
	    
	    if(o instanceof ASTExpresion) {

                if(o instanceof ASTLiteralUR)
                    ((ASTLiteralUR) o).refreshState(real);

                while(it.hasNext()){
                    o = it.next();
                    if(o instanceof ASTLiteralUR)
                        ((ASTLiteralUR) o).refreshState(real);
                }
	    }
	    else {
		LinkedList l = (LinkedList) o;

		refreshState(((Arreglo) real).getSub(),l);
		
		while(it.hasNext()) 
		    refreshState(((Arreglo) real).getSub(),l);
		    
	    }		
	}
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
    private boolean checkList(Tipo t, LinkedList lista) {
	if(t == null)
	    return false;
	else {
	    Iterator it = lista.iterator();	    
	    int size;	    
	    Object o = it.next();
	    
	    if(o instanceof ASTExpresion) {
		if(!flag) { 
		    flag = true;
                    if(t.asign(((ASTExpresion) o).getState()) == null){
                        state = null;
                        return false;
                    }
		}
	    }
	    else {

		LinkedList l = (LinkedList) o;
		size = l.size();
		checkList(((Arreglo) t).getSub(),l);

		while(it.hasNext()) {
		    l = (LinkedList) it.next();

		    if(l.size() != size) {
			state = null;
			return false;
		    }
		    else
			if(!checkList(((Arreglo) t).getSub(),l)) return false;
		}        
		    
	    }		
	}

        return true;

    }

    public void generatePushCastCode(Writer fd, int nextReg, Tipo dest, LinkedList lista) throws IOException{

        Iterator it = lista.iterator();

        while(it.hasNext()){

            Object o = it.next();
	        
            if(o instanceof ASTExpresion) {

               ASTExpresion expr = (ASTExpresion) o;
               String reg = AssemblerInfo.getNombresRegAtPos(nextReg); 
               String nreg = AssemblerInfo.getNombresRegAtPos(nextReg); 

               if(expr.getState() instanceof Basico && ((Basico) expr.getState()).getNBasico() == 3){

                   String si = AssemblerInfo.newLabel();
                   String no = AssemblerInfo.newLabel();
                   String end = AssemblerInfo.newLabel();

                   expr.generateCode(fd, nextReg, si, no);

                   fd.write(si + ":\n");
                   fd.write("push 1\n");    
                   fd.write("jmp " + end + "\n");		    
                   fd.write(no + ":\n");
                   fd.write("push 0\n");    
                   fd.write(end + ":\n");

               }
               else if(expr instanceof ASTIdentificador){
                   expr.generateCode(fd, nextReg, "", "");

                   if(expr.getState() instanceof Basico){
                       ASTCast cast = AssemblerInfo.checkCast(dest, expr.getState());

                       if(cast != null){
                            fd.write("mov "+nreg+", ["+reg+"]\n");
                            cast.generateCode(fd, nextReg+1, "", "");
                            fd.write("push qword ["+nreg+"]\n");
                       }
                       else
                           fd.write("push qword ["+reg+"]\n");
                   }
                   else
                       InvocarUtilities.generateIdenPushCastCode(fd, nextReg, dest , expr.getState(),  ((ASTIdentificador) expr).getTable().getParent() == null);
               }
               else if(expr instanceof ASTLiteralUR)
                    ((ASTLiteralUR) expr).generatePushCastCode(fd, nextReg, dest);
               else if(!(expr instanceof ASTInvocarExpresion)){
                   expr.generateCode(fd, nextReg, "", "");
                   ASTCast cast = AssemblerInfo.checkCast(dest, expr.getState());
                   if(cast != null)
                       cast.generateCode(fd, nextReg, "","");
                   fd.write("push "+reg+"\n");
               }

            }
            else
                generatePushCastCode(fd, nextReg, ((Arreglo) dest).getSub(), (LinkedList) o);
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
	String si;
	String no;
	String end;
	
	AssemblerInfo.saveReg(fd, nextReg + 1);
	while (it.hasNext()) {
	    aux_expr = (ASTExpresion)it.next();
	    si = AssemblerInfo.newLabel();
	    no = AssemblerInfo.newLabel();
	    end = AssemblerInfo.newLabel();
	    
	    if (aux_expr.getState() instanceof Basico) {
		if (((Basico)aux_expr.getState()).getNBasico() == 3) {
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
	    else if ((aux_expr.getState() instanceof Registro) || (aux_expr.getState() instanceof Union)) {
		((ASTLiteralUR)aux_expr).generateCode(fd, nextReg, aux_expr.getState());
	    }
	}
	AssemblerInfo.restoreReg(fd, nextReg + 1);
    }
}
