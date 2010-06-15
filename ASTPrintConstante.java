import java.io.*;

public class ASTPrintConstante extends ASTInstruccion {

    private ASTConst constante;

    public ASTPrintConstante(ASTConst c) {
	super("print");
        constante = c;
    }

    public void setConstante(ASTConst c) {
	constante = c;
    }

    public ASTConst getConstante() {
	return constante;
    }

    public String printTree() {
  	return name;
    }

    public void update() {}

    public void generateCode(Writer fd, int nextReg) {
	try {
	    AssemblerInfo.saveSpecificReg(fd, "rdi");
	    
	    Basico t = (Basico) constante.getState();
	    
	    switch(t.getNBasico()) {		
	    case 1:
		fd.write("mov rdi, " + constante.getCaseInt() + "\n");
		fd.write("call print_int\n");
		break;		
	    case 2:
		fd.write("mov rdi, 0x" + Long.toHexString(Double.doubleToLongBits(constante.getCaseFloat())) + "\n");
		fd.write("call print_float\n");
		break;		
	    case 3:		
		if(constante.getCaseBool())
		    fd.write("mov rdi, 1\n");
		else
		    fd.write("mov rdi, 0\n");
		
		fd.write("call print_bool\n");
		break;		
	    default:
		fd.write("mov rdi, " + Integer.toString((int)constante.getCaseChar().charAt(0)) + "\n");
		fd.write("call print_char\n");
		break;		
	    }
	    
	    AssemblerInfo.restoreSpecificReg(fd, "rdi");
	    
	}
	catch (Exception e) {
	    System.out.println("Error escribiendo en archivo de salida\n");
	}        
    }
}
