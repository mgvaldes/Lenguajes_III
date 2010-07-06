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
    }

}
