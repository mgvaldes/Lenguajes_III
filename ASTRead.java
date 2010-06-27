import java.io.*;

public class ASTRead extends ASTExpresion {

    public ASTRead(Tipo s) {
        super("read", null, null);
        state = s;
    }

    public String printTree() {
  	return "READ(" + state + ")";
    }

    public void update() {}

    public void generateCode(Writer fd, int nextReg, String si, String no) throws IOException {
            String reg = AssemblerInfo.getNombresRegAtPos(nextReg);

	    switch(((Basico) state).getNBasico()) {
            case 1:
	        fd.write("push rdi\n");
	        fd.write("push " + reg + "\n");
	        fd.write("mov rdi, " + AssemblerInfo.getSp() + "\n");
 	        fd.write("call read_int\n");
	        fd.write("pop " + reg + "\n");
	        fd.write("pop rdi\n");
	        break;
	    case 2:
	        fd.write("push rdi\n");
	        fd.write("push " + reg + "\n");
	        fd.write("mov rdi, " + AssemblerInfo.getSp() + "\n");
 	        fd.write("call read_float\n");
	        fd.write("pop " + reg + "\n");
	        fd.write("pop rdi\n");
	        break;
	    case 3:
	        fd.write("push rdi\n");
	        fd.write("push " + reg + "\n");
	        fd.write("mov rdi, " + AssemblerInfo.getSp() + "\n");
 	        fd.write("call read_bool\n");
	        fd.write("pop " + reg + "\n");
	        fd.write("cmp " + reg + ",1\n");
	        fd.write("pop rdi\n");
	        fd.write("je " + si + "\n");
	        fd.write("jmp " + no + "\n");
	        break;
	    default:
	        fd.write("push rdi\n");
	        fd.write("push " + reg + "\n");
	        fd.write("mov rdi, " + AssemblerInfo.getSp() + "\n");
 	        fd.write("call read_char\n");
	        fd.write("pop " + reg + "\n");
	        fd.write("pop rdi\n");
	        break;
	    }
    }
}
