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
	    AssemblerInfo.saveSpecificReg(fd, "rdi");
	    Basico t = new Basico(0);
	    
	    if (iden.getState() instanceof Basico) {
		if(iden.getTable().getParent() == null)
		    fd.write("mov rdi, [static + " + ((SymVar)iden.getTable().getSym(iden.getValue())).getOffset() + "]\n");
		else
		    fd.write("mov rdi, [" + AssemblerInfo.getFp() + " - " + ((SymVar)iden.getTable().getSym(iden.getValue())).getOffset() + "]\n");
		t = (Basico)iden.getState();
	    }
	    else if (iden.getState() instanceof Arreglo) {
		String reg = AssemblerInfo.getNombresRegAtPos(nextReg);
		iden.generateCode(fd, nextReg, "", "");
		fd.write("mov rdi, " + reg + "\n");
		t = (Basico) ((Arreglo)iden.getState()).getTipoBase();
	    }

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
