import java.io.*;

public class ASTReturn extends ASTInstruccion {

    private int offset;

    public ASTReturn(int o) {
        super("return");
	ireturn = true;
	ibreak = false;
        offset = o;
    }

    public void setOffset(int o){
        offset = o;
    }


    public int getOffset(){
        return offset;
    }  


    public void generateCode(Writer fd, int nextReg, String breakLabel, String returnLabel) throws IOException {
    }

}
