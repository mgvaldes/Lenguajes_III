import java.io.*;
import java.util.LinkedList;

public class ASTReturn extends ASTInstruccion {

    private int offset;
    private Tipo dest;
    private ASTExpresion expr;

    public ASTReturn(int o) {
        super("return");
	ireturn = true;
	ibreak = false;
        offset = o;
        dest = null;
        expr = null;
    }

    public ASTReturn(int o, Tipo d, ASTExpresion e) {
        super("return");
	ireturn = true;
	ibreak = false;
        offset = o;
        dest = d;
        expr = e;
    }

    public void setOffset(int o){
        offset = o;
    }

    public int getOffset(){
        return offset;
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

	    if (!(expr instanceof ASTLiteralArreglo) && 
		!(expr instanceof ASTLiteralUR)) {
		if (expr instanceof ASTIdentificador) {
		    expr_state = ((SymVar)((ASTIdentificador)expr). getTable().getSym(expr.getValue())).getState();
		}
		else {
		    expr_state = expr.getState();
		}
	    
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

	    Tipo base_id_type = dest;
	    Tipo base_expr_type;
	    ASTCast cast;
	    fd.write("mov " + reg1 + ", " + "rbp\n");
	    fd.write("add " + reg1 + ", " + offset + "\n");
	    
	    if (dest instanceof Basico) {
		base_expr_type = new Basico(0); // Solo para que no chille

		if (expr instanceof ASTIdentificador) {
		    fd.write("mov " + reg + ", [" + reg + "]\n");
			    
		    base_expr_type = ((ASTIdentificador)expr).getTipoAcceso(expr_state, ((ASTIdentificador)expr).getAcceso());				
		}
		else if (expr instanceof ASTInvocarExpresion) {			    
		    base_expr_type = ((SymProc)((ASTInvocarExpresion)expr).getProcInfo()).getState();
		    fd.write("pop " + reg + "\n");
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
	    else if (dest instanceof Arreglo) {
	    	base_expr_type = new Basico(0); // Solo para que no chille		

	    	if (expr instanceof ASTLiteralArreglo) {
	    	    ((ASTLiteralArreglo)expr).generateCode(fd, nextReg + 1, (Arreglo)dest);
		}
	    	else if ((expr instanceof ASTIdentificador) && 
	    		 (((ASTIdentificador)expr).getAcceso() == null)) {
		    
	    	    //Caso de asignacion de arreglos.
	    	    int offs = 0;

	    	    base_id_type = ((Arreglo)dest).getTipoBase();
	    	    base_expr_type = ((Arreglo)expr_state).getTipoBase();
		    
	    	    cast = AssemblerInfo.checkCast(base_id_type, base_expr_type);
	    	    AssemblerInfo.saveReg(fd, nextReg + 2);
	    	    while (offs < ((Arreglo)dest).getTam()) {
	    		fd.write("mov " + reg2 + ", [" + reg + " - " + offs + "]\n");
	    		if (cast != null) {
	    		    cast.generateCode(fd, nextReg + 2, "", "");
	    		}
	    		fd.write("mov [" + reg1 + " - " + offs + "], " + reg2 + "\n");
	    		offs += 8;
	    	    }
	    	    AssemblerInfo.restoreReg(fd, nextReg + 2);
	    	}
		else if (expr instanceof ASTInvocarExpresion) {
		    int offs = ((Arreglo)dest).getTam() - 8;
		    base_id_type = ((Arreglo)dest).getTipoBase();
		    base_expr_type = ((Arreglo)((SymProc)((ASTInvocarExpresion)expr).getProcInfo()).getState()).getTipoBase();

		    cast = AssemblerInfo.checkCast(base_id_type, base_expr_type);
		    while (offs >= 0) {
			fd.write("pop " + reg + "\n");
			if (cast != null) {
			    cast.generateCode(fd, nextReg, "", "");
			}
			fd.write("mov [" + reg1 + " - " + offs + "], " + reg + "\n");
			offs -= 8;
		    }
		}
		else {
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
	    else if (dest instanceof Registro) {
		if (expr instanceof ASTLiteralUR) {
		    ((ASTLiteralUR)expr).generateCode(fd, nextReg + 1, dest);
		}
		else if ((expr instanceof ASTIdentificador) && 
			 (((ASTIdentificador)expr).getAcceso() == null)) {
		    //Caso de asignacion de registros.
		    int offs = 0;
		    int pos = 0;

		    AssemblerInfo.saveReg(fd, nextReg + 2);
		    while (offs < ((Registro)dest).getTam()) {
			base_id_type = (Tipo)((LinkedList)((Registro)dest).getTipos()).get(pos);
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
		else if (expr instanceof ASTInvocarExpresion) {
		    int offs = ((Registro)dest).getTam() - 8;
		    int pos = ((Registro)dest).getTam()/8 - 1;
		    
		    while (offs >= 0) {
			base_id_type = (Tipo)((LinkedList)((Registro)dest).getTipos()).get(pos);
			base_expr_type = (Tipo)((LinkedList)((Registro)expr.getState()).getTipos()).get(pos);
			cast = AssemblerInfo.checkCast(base_id_type, base_expr_type);
			
			fd.write("pop " + reg + "\n");
			if (cast != null) {
			    cast.generateCode(fd, nextReg, "", "");
			}
			fd.write("mov [" + reg1 + " - " + offs + "], " + reg + "\n");
			offs -= 8;
			pos--;
		    }
		}
		else {
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
	    else if (dest instanceof Union) {
		if (expr instanceof ASTLiteralUR) {
		    ((ASTLiteralUR)expr).generateCode(fd, nextReg + 1, dest);
		}
		else if ((expr instanceof ASTIdentificador) && 
			 (((ASTIdentificador)expr).getAcceso() == null)) {
		    //Caso de asignacion de registros.
		    int offs = 0;
		    int pos = 0;

		    AssemblerInfo.saveReg(fd, nextReg + 2);
		    while (offs < ((Union)dest).getTam()) {
			base_id_type = (Tipo)((LinkedList)((Union)dest).getTipos()).get(pos);
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
		else if (expr instanceof ASTInvocarExpresion) {
		    int offs = ((Union)dest).getTam() - 8;
		    int pos = ((Union)dest).getTam()/8 - 1;
		    
		    while (offs >= 0) {
			base_id_type = (Tipo)((LinkedList)((Union)dest).getTipos()).get(pos);
			base_expr_type = (Tipo)((LinkedList)((Union)expr.getState()).getTipos()).get(pos);
			cast = AssemblerInfo.checkCast(base_id_type, base_expr_type);
			
			fd.write("pop " + reg + "\n");
			if (cast != null) {
			    cast.generateCode(fd, nextReg, "", "");
			}
			fd.write("mov [" + reg1 + " - " + offs + "], " + reg + "\n");
			offs -= 8;
			pos--;
		    }
		}
		else {
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
	}
	catch (Exception e) {
            e.printStackTrace();
    	    System.out.println("Error escribiendo en archivo de salida");
	    
    	}
    }

}
