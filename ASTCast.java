import java.io.*;

public class ASTCast extends ASTExpresion {

    public ASTCast(ASTExpresion l, Tipo s) {
	super("cast", l, null);
        state = s;
    }

    public void update(){}
  
    public String printTree() {	
        String m;
        if(left != null)
 	  m = new String(value + "( " + left.printTree() + " )");
        else
          m = new String(value + "( null )");
	return m;
    }

    public void generateCode(Writer fd, int nextReg, String si, String no) throws IOException {	
            String reg = AssemblerInfo.getNombresRegAtPos(nextReg);

            if(left != null)
              left.generateCode(fd, nextReg, si, no);

            switch(((Basico) state).getNBasico()){
            case 1:
                fd.write("push " + reg + "\n");
                fd.write("fld qword [" + AssemblerInfo.getSp() + "]\n");
                fd.write("fistp qword [" + AssemblerInfo.getSp() + "]\n");
                fd.write("pop " + reg + "\n");
                break;
            case 2:
                fd.write("push " + reg + "\n");
                fd.write("fild qword [" + AssemblerInfo.getSp() + "]\n");
                fd.write("fstp qword [" + AssemblerInfo.getSp() + "]\n");
                fd.write("pop " + reg + "\n");
                break;
            default:
                String nreg = AssemblerInfo.getNombresRegAtPos(nextReg + 1);

                AssemblerInfo.saveReg(fd, nextReg + 1);

                fd.write("mov " + nreg + ", 256\n");

                if( reg == "rax")
                    fd.write("push rdx\n");
                else if( reg == "rdx")
                    fd.write("push rax\n");
                else {
                    fd.write("push rdx\n");
                    fd.write("push rax\n");
                }

                fd.write("mov rdx, 0\n");
                fd.write("mov rax, " + reg + "\n");

                fd.write("idiv " + nreg + "\n");

                fd.write("mov "+ reg +", rdx \n");

                if( reg == "rax")
                    fd.write("pop rdx\n");
                else if( reg == "rdx")
                    fd.write("pop rax\n");
                else {
                    fd.write("pop rax\n");
                    fd.write("pop rdx\n");
                }

                AssemblerInfo.restoreReg(fd, nextReg + 1);

                break;
            }
                
    }
}
