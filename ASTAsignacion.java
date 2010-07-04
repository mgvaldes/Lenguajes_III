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
		    //Esto tengo que hacerlo porque el state de los identificadores 
		    //puede cambiar con los metodos de update().
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
		ASTCast cast;
		int offset;
		Tipo aux_state;
		Tipo base_id_type;
		Tipo base_expr_type; // Solo para que no chille
		
		while(it.hasNext()) {
		    id = (ASTIdentificador)it.next();
		    offset = ((SymVar)id.getTable().getSym(id.getValue())).getOffset();
		    aux_state = ((SymVar)id.getTable().getSym(id.getValue())).getState();
		    AssemblerInfo.saveReg(fd, nextReg + 1);
		    id.generateCode(fd, nextReg + 1, si, no);

		    if (aux_state instanceof Basico) {	
			base_id_type = aux_state;
			base_expr_type = new Basico(0); // Solo para que no chille
			
			if (expr instanceof ASTIdentificador) {
			    fd.write("mov " + reg + ", [" + reg + "]\n");
			    
			    base_expr_type = ((ASTIdentificador)expr).getTipoAcceso(expr_state, ((ASTIdentificador)expr).getAcceso());				
			}
			else {
			    base_expr_type = expr_state;
			}
			
			cast = AssemblerInfo.checkCast(base_id_type, base_expr_type);
			if (cast != null) {
			    cast.generateCode(fd, nextReg, "", "");
			}			    
			
			fd.write("mov [" + reg1 + "], " + reg + "\n");
		    }
		    else if (aux_state instanceof Arreglo) { 
			if (expr instanceof ASTLiteralArreglo) {
			    ((ASTLiteralArreglo)expr).generateCode(fd, nextReg + 1, (Arreglo)id.getState());
			}
			else if ((expr instanceof ASTIdentificador) && 
				 (((ASTIdentificador)expr).getAcceso().getHijo() == null)) {
			    //Caso de asignacion de arreglos.
			    int offs = 0;
			    base_id_type = ((Arreglo)aux_state).getTipoBase();
			    base_expr_type = ((Arreglo)expr_state).getTipoBase();

			    cast = AssemblerInfo.checkCast(base_id_type, base_expr_type);
			    AssemblerInfo.saveReg(fd, nextReg + 2);
			    while (offs < ((Arreglo)aux_state).getTam()) {
				fd.write("mov " + reg2 + ", [" + reg + " - " + offs + "]\n");
				if (cast != null) {
				    cast.generateCode(fd, nextReg + 2, "", "");
				}
				fd.write("mov [" + reg1 + " - " + offs + "], " + reg2 + "\n");
				offs += 8;
			    }
			    AssemblerInfo.restoreReg(fd, nextReg + 2);
			}
			else {
			    base_id_type = ((Arreglo)aux_state).getTipoBase();
			    base_expr_type = new Basico(0); // Solo para que no chille

			    if (expr instanceof ASTIdentificador) {
				fd.write("mov " + reg + ", [" + reg + "]\n");
				
				base_expr_type = ((ASTIdentificador)expr).getTipoAcceso(expr_state, ((ASTIdentificador)expr).getAcceso());				
			    }
			    else {
				base_expr_type = expr_state;
			    }

			    cast = AssemblerInfo.checkCast(base_id_type, base_expr_type);
			    if (cast != null) {
				cast.generateCode(fd, nextReg, "", "");
			    }			    
			    
			    fd.write("mov [" + reg1 + "], " + reg + "\n");
			}
		    }
		    else if (aux_state instanceof Registro) {
			if (expr instanceof ASTLiteralUR) {
			    ((ASTLiteralUR)expr).generateCode(fd, nextReg + 1, (Registro)id.getState());
			}
			else if ((expr instanceof ASTIdentificador) && 
				 (((ASTIdentificador)expr).getAcceso().getHijo() == null)) {
			    //Caso de asignacion de registros.
			    int offs = 0;
			    int pos = 0;

			    AssemblerInfo.saveReg(fd, nextReg + 2);
			    while (offs < ((Registro)aux_state).getTam()) {
				base_id_type = (Tipo)((LinkedList)((Registro)aux_state).getTipos()).get(pos);
				base_expr_type = (Tipo)((LinkedList)((Registro)expr.getState()).getTipos()).get(pos);
				cast = AssemblerInfo.checkCast(base_id_type, base_expr_type);

				fd.write("mov " + reg2 + ", [" + reg + " - " + offs + "]\n");
				if (cast != null) {
				    cast.generateCode(fd, nextReg + 2, "", "");
				}
				fd.write("mov [" + reg1 + " - " + offs + "], " + reg2 + "\n");
				offs += 8;
				pos++;
			    }
			    AssemblerInfo.restoreReg(fd, nextReg + 2);
			}
			else {
			    base_id_type = ((ASTIdentificador)id).getTipoAcceso(aux_state, ((ASTIdentificador)id).getAcceso());
			    base_expr_type = new Basico(0); // Solo para que no chille

			    if (expr instanceof ASTIdentificador) {
				fd.write("mov " + reg + ", [" + reg + "]\n");
				
				base_expr_type = ((ASTIdentificador)expr).getTipoAcceso(expr_state, ((ASTIdentificador)expr).getAcceso());				
			    }
			    else {
				base_expr_type = expr_state;
			    }

			    cast = AssemblerInfo.checkCast(base_id_type, base_expr_type);
			    if (cast != null) {
				cast.generateCode(fd, nextReg, "", "");
			    }			    
			    
			    fd.write("mov [" + reg1 + "], " + reg + "\n");
			}
		    }
		    else if (aux_state instanceof Union) {  
			if (expr instanceof ASTLiteralUR) {
			    ((ASTLiteralUR)expr).generateCode(fd, nextReg + 1, (Union)id.getState());
			}
			else if ((expr instanceof ASTIdentificador) && 
				 (((ASTIdentificador)expr).getAcceso().getHijo() == null)) {
			    //Caso de asignacion de uniones.
			    int offs = 0;
			    int pos = 0;

			    AssemblerInfo.saveReg(fd, nextReg + 2);
			    while (offs < ((Union)aux_state).getTam()) {
				base_id_type = (Tipo)((LinkedList)((Union)aux_state).getTipos()).get(pos);
				base_expr_type = (Tipo)((LinkedList)((Union)expr.getState()).getTipos()).get(pos);
				cast = AssemblerInfo.checkCast(base_id_type, base_expr_type);

				fd.write("mov " + reg2 + ", [" + reg + " - " + offs + "]\n");
				if (cast != null) {
				    cast.generateCode(fd, nextReg + 2, "", "");
				}
				fd.write("mov [" + reg1 + " - " + offs + "], " + reg2 + "\n");
				offs += 8;
				pos++;
			    }
			    AssemblerInfo.restoreReg(fd, nextReg + 2);
			}
			else {
			    base_id_type = ((ASTIdentificador)id).getTipoAcceso(aux_state, ((ASTIdentificador)id).getAcceso());
			    base_expr_type = new Basico(0); // Solo para que no chille

			    if (expr instanceof ASTIdentificador) {
				fd.write("mov " + reg + ", [" + reg + "]\n");
				
				base_expr_type = ((ASTIdentificador)expr).getTipoAcceso(expr_state, ((ASTIdentificador)expr).getAcceso());				
			    }
			    else {
				base_expr_type = expr_state;
			    }

			    cast = AssemblerInfo.checkCast(base_id_type, base_expr_type);
			    if (cast != null) {
				cast.generateCode(fd, nextReg, "", "");
			    }			    
			    
			    fd.write("mov [" + reg1 + "], " + reg + "\n");
			}
		    }
		    AssemblerInfo.restoreReg(fd, nextReg + 1);
		}
	    }
    	}
    	catch (Exception e) {
            e.printStackTrace();
    	    System.out.println("Error escribiendo en archivo de salida");
    	}
    }
}


