import java.util.LinkedList;
import java.util.Iterator;
import java.io.*;

public class ASTAsignacion extends ASTInstruccion {

    private LinkedList ids;
    private ASTExpresion expr;
    private LinkedList casts;
    private boolean isDeclaration;

    public ASTAsignacion(LinkedList l, ASTExpresion e, LinkedList c) {
	super("asig");
	ids = l;
	expr = e;
        casts = c;
        isDeclaration = false;
    }

    public void setIds(LinkedList l) {
	ids = l;
    }   

    public void setExpr(ASTExpresion e) {
	expr = e;
    }

    public void setIsDeclaration(boolean b) {
	isDeclaration = b;
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

    public LinkedList getCasts(){
        return casts;
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

	    String si = AssemblerInfo.newLabel();
	    String no = AssemblerInfo.newLabel();
	    String end = AssemblerInfo.newLabel();
	    //En este registro esta calculada la expresion que se va a asignarx
            String reg = AssemblerInfo.getNombresRegAtPos(nextReg); 
	    String reg1 = AssemblerInfo.getNombresRegAtPos(nextReg + 1);
	    Tipo expr_state = new Basico(0); // Solo para inicializarlo

            if (expr != null) {
		if (!(expr instanceof ASTLiteralArreglo) && !(expr instanceof ASTLiteralUR)) {
		    //Esto tengo que hacerlo porque el state de los identificadores puede cambiar con los metodos de update().
		    if (expr instanceof ASTIdentificador) {
			expr_state = ((SymVar)((ASTIdentificador)expr).getTable().getSym(expr.getValue())).getState();
		    }
		    else {
			expr_state = expr.getState();
		    }
		    
		    //Caso especial para las expresiones booleanas
		    if (expr_state instanceof Basico && ((Basico)expr_state).getNBasico() == 3) {
			expr.generateCode(fd, nextReg, si, no);
			fd.write(si + ":\n");
			fd.write("mov " + reg + ", 1\n");    
			fd.write("jmp " + end + "\n");		    
			fd.write(no + ":\n");
			fd.write("mov " + reg + ", 0\n");    
			fd.write(end + ":\n");
		    }

		    if (expr instanceof ASTIdentificador) {
			expr.generateCode(fd, nextReg, si, no);
			if ((((ASTIdentificador)expr).getAcceso().getHijo() != null) || (expr_state instanceof Basico)) {
			    //Esto es para evitar el caso de la asignacion directa de arreglos o registros, o asignacion de identificadores de tipo basico
			    fd.write("mov " + reg + ", [" + reg + "]\n");
			}
		    }
		    else {
			expr.generateCode(fd, nextReg, si, no);	       
		    }
		}

                Iterator it = ids.iterator();
                Iterator itc = casts.iterator();

                ASTIdentificador id;
                ASTCast ct;
	        int offset;
	        Tipo aux_state;

                while(it.hasNext()) {

	            id = (ASTIdentificador)it.next();
	            ct = (ASTCast)itc.next();

                    if(ct != null){
                        AssemblerInfo.saveSpecificReg(fd, reg);
                        ct.generateCode(fd, nextReg, si, no);
                    }

                    offset = ((SymVar)id.getTable().getSym(id.getValue())).getOffset();
		    aux_state = ((SymVar)id.getTable().getSym(id.getValue())).getState();

	            if (aux_state instanceof Basico) {

                        if(id.getTable().getParent() == null)
		            fd.write("mov [static + " + offset + "], " + reg + "\n");
		        else
		            fd.write("mov [" + AssemblerInfo.getFp() + "+ " + offset + "], " + reg + "\n");
	            }
	            else if (aux_state instanceof Arreglo) {		    
		        AssemblerInfo.saveReg(fd, nextReg + 1);
                        id.generateCode(fd, nextReg + 1, si, no);

                        if (expr instanceof ASTLiteralArreglo) {
			    ((ASTLiteralArreglo)expr).generateCode(fd, nextReg + 1, (Arreglo)id.getState());
			}
			else if ((expr instanceof ASTIdentificador) && (((ASTIdentificador)expr).getAcceso().getHijo() == null)) {
			    //Caso de asignacion de arreglos.
			    int tamBase = ((Arreglo)aux_state).getTipoBase().getTam();
			    int offs = 0;
			    String reg2 = AssemblerInfo.getNombresRegAtPos(nextReg + 2);

			    AssemblerInfo.saveReg(fd, nextReg + 2);
			    for (int i = 0; i < ((Arreglo)aux_state).getTam(); i++) {
				fd.write("mov " + reg2 + ", [" + reg + " - " + offs + "]\n");
				fd.write("mov [" + reg1 + " - " + offs + "], " + reg2 + "\n");
				offs += tamBase;
			    }
			    AssemblerInfo.restoreReg(fd, nextReg + 2);
			}
			else {
			    fd.write("mov [" + reg1 + "], " + reg + "\n");
			}

                        AssemblerInfo.restoreReg(fd, nextReg + 1);
		    }
		    else if (aux_state instanceof Registro) {		    
		        AssemblerInfo.saveReg(fd, nextReg + 1);
                        id.generateCode(fd, nextReg + 1, si, no);

                        if (expr instanceof ASTLiteralUR) {
			    ((ASTLiteralUR)expr).generateCode(fd, nextReg + 1, (Registro)id.getState());
			}
			else {
			    fd.write("mov [" + reg1 + "], " + reg + "\n");
			}

			AssemblerInfo.restoreReg(fd, nextReg + 1);

			    // else if ((expr instanceof ASTIdentificador) && (((ASTIdentificador)expr).getAcceso().getHijo() == null)) {
			    //     int tamBase = ((Arreglo)aux_state).getTipoBase().getTam();
			    //     int offs = 0;
			    //     String reg2 = AssemblerInfo.getNombresRegAtPos(nextReg + 2);

			    //     AssemblerInfo.saveReg(fd, nextReg + 2);
			    //     for (int i = 0; i < ((Arreglo)aux_state).getTam(); i++) {
			    // 	fd.write("mov " + reg2 + ", [" + reg + " - " + offs + "]\n");
			    // 	fd.write("mov [" + reg1 + " - " + offs + "], " + reg2 + "\n");
			    // 	offs += tamBase;
			    //     }
			    //     AssemblerInfo.restoreReg(fd, nextReg + 2);
			    // }
			    // else {
			    //}
		    }
		    else if (aux_state instanceof Union) {		    
		        AssemblerInfo.saveReg(fd, nextReg + 1);			
                        id.generateCode(fd, nextReg + 1, si, no);

                        if (expr instanceof ASTLiteralUR) {
			    ((ASTLiteralUR)expr).generateCode(fd, nextReg + 1, (Union)id.getState());
			}
			else {
			    fd.write("mov [" + reg1 + "], " + reg + "\n");
			}
			
			AssemblerInfo.restoreReg(fd, nextReg + 1);
		    }
		    
                    if(ct != null)
                        AssemblerInfo.restoreSpecificReg(fd, reg);
                }
            }
    }
}


