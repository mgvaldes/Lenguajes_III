import java.io.*;

public class ASTWhile extends ASTInstruccion {

    private ASTExpresion cond;
    private ASTBloque bloque;

    public ASTWhile(ASTExpresion co, ASTBloque b) {
	super("while");
	cond = co;
	bloque = b;
    }

    public void setCond(ASTExpresion c) {
  	cond = c;
    }

    public void setBloque(ASTBloque b) {
	bloque = b;
    }

    public ASTExpresion getCond() {
	return cond;
    }

    public ASTBloque getBloque() {
	return bloque;
    }

    public void update() {

	if(bloque.getIreturn())
	    ireturn = true;

	if(bloque.getIreturn())
	    ibreak = true;	
    }

    public String printTree() {
	String m = new String("WHILE( " + cond.printTree() + ",\n" + bloque.printTree() + " )");
	return m;
    }

    public void generateCode( Writer fd, int nextReg) throws IOException {
	try {

          String si = AssemblerInfo.newLabel();
          String exp = AssemblerInfo.newLabel();
          String no = AssemblerInfo.newLabel();

          fd.write("jmp "+exp+"\n");

          fd.write(si+":\n");

          bloque.generateCode(fd,nextReg, no);

          fd.write(exp+":\n");

          cond.generateCode(fd, nextReg, si,no);

          fd.write(no+":\n");


    	}
    	catch (Exception e) {
    	    System.out.println("Error escribiendo en archivo de salida\n");
    	}
    }
}
