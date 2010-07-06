import java.io.*;

public class ASTPrintIdentificador extends ASTInstruccion {

    private ASTIdentificador iden;

    public ASTPrintIdentificador(ASTIdentificador i) {
	super("print");
        iden = i;
    }

    public void setIden(ASTIdentificador i) {
        iden = i;
    }

    public ASTIdentificador getIden() {
	return iden;
    }

    public String printTree() {
  	return name;
    }

    public void update() {}

    public void generateCode(Writer fd, int nextReg, String breakLabel, String returnLabel) throws IOException{

            String reg = AssemblerInfo.getNombresRegAtPos(nextReg); 
	    AssemblerInfo.saveSpecificReg(fd, "rdi");
	    Basico t = (Basico) iden.getState();
	    
            iden.generateCode(fd, nextReg, "", "");
            fd.write("mov rdi, [" + reg + "]\n");

	    switch(t.getNBasico()) {	
	    case 1:
		fd.write("call print_int\n");
		break;		
	    case 2:
		fd.write("call print_float\n");
		break;		
	    case 3:
		fd.write("call print_bool\n");
		break;		
	    default:
		fd.write("call print_char\n");
		break;		
	    }
	    
	    AssemblerInfo.restoreSpecificReg(fd, "rdi");	    
    }
}
