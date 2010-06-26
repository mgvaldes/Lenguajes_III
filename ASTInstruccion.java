import java.io.*;

public class ASTInstruccion {

    protected boolean ireturn;
    protected boolean ibreak;
    protected String name;

    public ASTInstruccion(String n) {
	name = n;
	ireturn = false;
	ibreak = false;
    }

    public void setIreturn(boolean i) {
  	ireturn = i;
    }
    public void setIbreak(boolean i) {
	ibreak = i;
    }

    public boolean getIreturn() {
	return ireturn;
    }

    public boolean getIbreak() {
	return ibreak;
    }

    public String getName() {
	return name;
    }

    public String printTree() {
  	return name;
    }

    public void update() {}

    public void generateCode(Writer fd, int nextReg, String breakLabel, String returnLabel) throws IOException {
        if(name.compareTo("break")==0)
            fd.write("jmp "+breakLabel+"\n");
    }

}
