import java.util.LinkedList;
import java.util.Iterator;
import java.io.*;

public class ASTAsignacion extends ASTInstruccion {

    private LinkedList ids;
    private ASTExpresion expr;

    public ASTAsignacion(LinkedList l, ASTExpresion e) {
	super("asig");
	ids = l;
	expr = e;
    }

    public void setIds(LinkedList l) {
	ids = l;
    }   

    public void setExpr(ASTExpresion e) {
	expr = e;
    }

    public LinkedList getIds() {
	return ids;
    }

    public ASTExpresion getExpr() {
	return expr;
    }

    public Tipo getState() {
	return expr.getState();
    }

    public void update() {}

    public String printTree() {

	Iterator it = ids.iterator();
	String idents = new String("[ ");
	while(it.hasNext()) {
	    idents = idents.concat(((ASTIdentificador)it.next()).printTree() + " ");
	}
	idents = idents.concat("]");

	if (expr != null) {
	    String m = new String("ASIG( " + idents + ", " + expr.printTree() + " )");
	    return m;
	}
	else {
	    String m = new String("ASIG( " + idents + " )");
	    return m;
	}
    }
    
    public void generateCode(Writer fd, int nextReg, String breakLabel, String returnLabel) throws IOException {
	try {
	    String si = AssemblerInfo.newLabel();
	    String no = AssemblerInfo.newLabel();
	    String end = AssemblerInfo.newLabel();

            String reg = AssemblerInfo.getNombresRegAtPos(nextReg); 
	    String reg1 = AssemblerInfo.getNombresRegAtPos(nextReg + 1); 
	    String reg2 = AssemblerInfo.getNombresRegAtPos(nextReg + 2);
	    Tipo expr_state = new Basico(0); // Solo para inicializarlo
	    
	    if (expr != null) {
		if (!(expr instanceof ASTLiteralArreglo) && 
		    !(expr instanceof ASTLiteralUR)) {
		    //Esto tengo que hacerlo porque el state de los identificadores puede cambiar con los metodos de update().
		    if (expr instanceof ASTIdentificador) {
			expr_state = ((SymVar)((ASTIdentificador)expr). getTable().getSym(expr.getValue())).getState();
		    }
		    else {
			expr_state = expr.getState();
		    }
		    
		    //Caso especial para las expresiones booleanas
		    if (expr_state instanceof Basico && ((Basico)expr_state).getNBasico() == 3) {
			expr.generateCode(fd, nextReg, si, no);
			if (!(expr instanceof ASTIdentificador)) {
			    fd.write(si + ":\n");
			    fd.write("mov " + reg + ", 1\n");    
			    fd.write("jmp " + end + "\n");		    
			    fd.write(no + ":\n");
			    fd.write("mov " + reg + ", 0\n");    
			    fd.write(end + ":\n");
			}		       
		    }
		    else {
			expr.generateCode(fd, nextReg, si, no);
		    }				
		}       

		Iterator it = ids.iterator();

		ASTIdentificador id;
		ASTCast aux_cast;
		int offset;
		Tipo aux_state;

		while(it.hasNext()) {
		    id = (ASTIdentificador)it.next();
		    offset = ((SymVar)id.getTable().getSym(id.getValue())).getOffset();
		    aux_state = ((SymVar)id.getTable().getSym(id.getValue())).getState();
		    AssemblerInfo.saveReg(fd, nextReg + 1);
		    id.generateCode(fd, nextReg + 1, si, no);

		    if (aux_state instanceof Basico) {	
			if (expr instanceof ASTIdentificador) {
			    fd.write("mov " + reg + ", [" + reg + "]\n");
			}

			fd.write("mov [" + reg1 + "], " + reg + "\n");
			
			// aux_cast = AssemblerInfo.checkCast(aux_state, expr_state);
			// if (aux_cast != null) {
			//     System.out.println("cast");
			//     if (expr instanceof ASTIdentificador) {
			// 	System.out.println("idddd");
			// 	fd.write("mov " + reg + ", [" + reg + "]\n");
			//     }
			//     aux_cast.generateCode(fd, nextReg, "", "");
			// }
			// else {
			//     System.out.println("no cast");
			// }

			// fd.write("mov [" + reg1 + "], " + reg + "\n");
		    }
		    else if (aux_state instanceof Arreglo) { 
			if (expr instanceof ASTLiteralArreglo) {
			    ((ASTLiteralArreglo)expr).generateCode(fd, nextReg + 1, (Arreglo)id.getState());
			}
			else if ((expr instanceof ASTIdentificador) && 
				 (((ASTIdentificador)expr).getAcceso().getHijo() == null)) {
			    //Caso de asignacion de arreglos.
			    int offs = 0;

			    AssemblerInfo.saveReg(fd, nextReg + 2);
			    while (offs < ((Arreglo)aux_state).getTam()) {
				fd.write("mov " + reg2 + ", [" + reg + " - " + offs + "]\n");
				fd.write("mov [" + reg1 + " - " + offs + "], " + reg2 + "\n");
				offs += 8;
			    }
			    AssemblerInfo.restoreReg(fd, nextReg + 2);
			}
			else {
			    if (expr instanceof ASTIdentificador) {
				fd.write("mov " + reg + ", [" + reg + "]\n");
			    }
			    
			    fd.write("mov [" + reg1 + "], " + reg + "\n");
			}
		    }
		    else if (aux_state instanceof Registro) {
			if (expr instanceof ASTLiteralUR) {
			    ((ASTLiteralUR)expr).generateCode(fd, nextReg + 1, (Registro)id.getState());
			}
			else if ((expr instanceof ASTIdentificador) && (((ASTIdentificador)expr).getAcceso().getHijo() == null)) {
			    //Caso de asignacion de registros.
			    int offs = 0;

			    AssemblerInfo.saveReg(fd, nextReg + 2);
			    while (offs < ((Registro)aux_state).getTam()) {
				fd.write("mov " + reg2 + ", [" + reg + " - " + offs + "]\n");
				fd.write("mov [" + reg1 + " - " + offs + "], " + reg2 + "\n");
				offs += 8;
			    }
			    AssemblerInfo.restoreReg(fd, nextReg + 2);
			}
			else {
			    if (expr instanceof ASTIdentificador) {
				fd.write("mov " + reg + ", [" + reg + "]\n");
			    }
			    
			    fd.write("mov [" + reg1 + "], " + reg + "\n");
			}
		    }
		    else if (aux_state instanceof Union) {  
			if (expr instanceof ASTLiteralUR) {
			    ((ASTLiteralUR)expr).generateCode(fd, nextReg + 1, (Union)id.getState());
			}
			else if ((expr instanceof ASTIdentificador) && (((ASTIdentificador)expr).getAcceso().getHijo() == null)) {
			    //Caso de asignacion de uniones.
			    int offs = 0;

			    AssemblerInfo.saveReg(fd, nextReg + 2);
			    while (offs < ((Union)aux_state).getTam()) {
				fd.write("mov " + reg2 + ", [" + reg + " - " + offs + "]\n");
				fd.write("mov [" + reg1 + " - " + offs + "], " + reg2 + "\n");
				offs += 8;
			    }
			    AssemblerInfo.restoreReg(fd, nextReg + 2);
			}
			else {
			    if (expr instanceof ASTIdentificador) {
				fd.write("mov " + reg + ", [" + reg + "]\n");
			    }
			    
			    fd.write("mov [" + reg1 + "], " + reg + "\n");
			}
		    }

<<<<<<< HEAD
		    AssemblerInfo.restoreReg(fd, nextReg + 1);
=======
>>>>>>> 0c894c7cc8503d31229e44e009f72dae4aedff51
		}
	    }
    	}
    	catch (Exception e) {
            e.printStackTrace();
    	    System.out.println("Error escribiendo en archivo de salida");
    	}
    }
}


