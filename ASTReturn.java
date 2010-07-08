import java.io.*;

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
	    Tipo expr_state = new Basico(0); // Solo para inicializarlo

	    if (expr instanceof ASTIdentificador) {
		expr_state = ((SymVar)((ASTIdentificador)expr). getTable().getSym(expr.getValue())).getState();
	    }
	    else {
		expr_state = expr.getState();
	    }

	    if ((expr instanceof ASTLiteralArreglo) || (expr instanceof ASTLiteralUR)) {
		fd.write("mov " + reg + ", " + "rbp\n");
		fd.write("add " + reg + ", " + offset + "\n");
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

	    Tipo base_id_type;
	    Tipo base_expr_type;
	    ASTCast cast;
	    
	    if (dest instanceof Basico) {
		fd.write("mov " + reg1 + ", " + "rbp\n");
		fd.write("add " + reg1 + ", " + offset + "\n");
		base_id_type = dest;
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
	catch (Exception e) {
            e.printStackTrace();
    	    System.out.println("Error escribiendo en archivo de salida");
    	}
    }

}
