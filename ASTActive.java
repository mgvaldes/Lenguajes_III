import java.io.*;

public class ASTActive extends ASTBool {

    public ASTActive(ASTIdentificador l, ASTIdentificador r) {
        super("activeon", l, r);
        state = new Basico(3);
    }

    public String printTree() {
  	return " ACTIVE("+left.printTree()+" , "+ right.printTree()+")";
    }

    public void update() {}

    public void generateCode(Writer fd, int nextReg, String si, String no) throws IOException {

        String reg = AssemblerInfo.getNombresRegAtPos(nextReg);

        left.generateCode(fd, nextReg, "", "");
        fd.write("mov "+reg+", ["+reg+"]\n");

        int pos = ((Union) left.getState()).getCampos().indexOf(right.getValue());

        fd.write("cmp "+reg+", "+pos+"\n");
        fd.write("je "+si+"\n");
        fd.write("jmp "+no+"\n");
    }
}
