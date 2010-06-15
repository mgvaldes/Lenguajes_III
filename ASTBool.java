import java.io.*;

public class ASTBool extends ASTExpresion {

    //@ invariant left != null;
    //@ requires v != null;
    //@ requires l != null;
    public ASTBool(String v, ASTExpresion l, ASTExpresion r) {
	super(v,l,r);
    }

    public void update() {
	Tipo lstate = left.getState();

	if(right!=null) {      
	    Tipo rstate = right.getState();

            if(value.compareTo("==") == 0 || value.compareTo("!=") == 0)
  	      state = lstate.eq(rstate);
            else if(value.compareTo("&&") == 0 || value.compareTo("||") == 0)
  	      state = lstate.oa(rstate);
            else
  	      state = lstate.compare(rstate);

            if( lstate.equals(new Basico(1)) && rstate.equals(new Basico(2)) )
              left = new ASTCast(left,new Basico(2));

            if( lstate.equals(new Basico(2)) && rstate.equals(new Basico(1)) )
              right = new ASTCast(right,new Basico(2));
	}
	else 
	    state = lstate.compare();
    }

    public String printTree() {
	String m = new String(value + "( " + left.printTree() + ", " + right.printTree() + " )");
	return m;
    }

    public void generateCode(Writer fd, int nextReg, String si, String no) throws IOException {
	try {
            String reg = AssemblerInfo.getNombresRegAtPos(nextReg);
            String nreg = AssemblerInfo.getNombresRegAtPos(nextReg + 1);
            int basicType = ((Basico) left.getState()).getNBasico();

            switch(basicType){
                case 1:
                case 4:
                if (value.compareTo("==") == 0) {
		    left.generateCode(fd, nextReg, si, no);
                    AssemblerInfo.saveReg(fd, nextReg + 1);
                    right.generateCode(fd, nextReg+1, si, no);
                    fd.write("cmp " + reg + ", " + nreg + "\n");
                    AssemblerInfo.restoreReg(fd, nextReg + 1);
                    fd.write("je " + si + "\n");
                    fd.write("jmp " + no + "\n");
	        }
	        else if (value.compareTo("!=") == 0) {
		    left.generateCode(fd, nextReg, si, no);
                    AssemblerInfo.saveReg(fd, nextReg + 1);
                    right.generateCode(fd, nextReg+1, si, no);
                    fd.write("cmp " + reg + ", " + nreg + "\n");
                    AssemblerInfo.restoreReg(fd, nextReg + 1);
                    fd.write("jne " + si + "\n");
                    fd.write("jmp " + no + "\n");
	        }
	        else if (value.compareTo("<") == 0) {
		    left.generateCode(fd, nextReg, si, no);
		    AssemblerInfo.saveReg(fd, nextReg + 1);
		    right.generateCode(fd, nextReg + 1, si, no);	       
		    fd.write("cmp " + reg + ", " + nreg + "\n");
		    AssemblerInfo.restoreReg(fd, nextReg + 1);
		    fd.write("jl " + si + "\n");
		    fd.write("jmp " + no + "\n");
	        }
	        else if (value.compareTo("<=") == 0) {
		    left.generateCode(fd, nextReg, si, no);
		    AssemblerInfo.saveReg(fd, nextReg + 1);
		    right.generateCode(fd, nextReg + 1, si, no);	       
		    fd.write("cmp " + reg + ", " + nreg + "\n");
		    AssemblerInfo.restoreReg(fd, nextReg + 1);
		    fd.write("jle " + si + "\n");
		    fd.write("jmp " + no + "\n");
	        }
	        else if (value.compareTo(">") == 0) {
		    left.generateCode( fd, nextReg, si, no);
		    AssemblerInfo.saveReg(fd, nextReg + 1);
		    right.generateCode(fd, nextReg + 1, si, no);	       
		    fd.write("cmp " + reg + ", " + nreg + "\n");
		    AssemblerInfo.restoreReg(fd, nextReg + 1);
		    fd.write("jg " + si + "\n");
		    fd.write("jmp " + no + "\n");
	        }
	        else{
		    left.generateCode(fd, nextReg, si, no);
		    AssemblerInfo.saveReg(fd, nextReg + 1);
		    right.generateCode(fd, nextReg + 1, si, no);	       
		    fd.write("cmp " + reg + ", " + nreg + "\n");
		    AssemblerInfo.restoreReg(fd, nextReg + 1);
		    fd.write("jge " + si + "\n");
		    fd.write("jmp " + no + "\n");
	        }
                break;
            case 2:
	        if (value.compareTo("==") == 0) {

		    left.generateCode(fd, nextReg, si, no);

                    AssemblerInfo.saveReg(fd, nextReg + 1);
                    right.generateCode(fd, nextReg+1, si, no);

                    fd.write("push "+reg+"\n");
                    fd.write("fld qword ["+AssemblerInfo.getSp()+"]\n");
			
                    fd.write("push "+nreg+"\n");
                    fd.write("fld qword ["+AssemblerInfo.getSp()+"]\n");

                    fd.write("fcomip st1\n");
                    fd.write("fincstp\n");

                    fd.write("pop "+nreg+"\n");
                    fd.write("pop "+reg+"\n");

                    AssemblerInfo.restoreReg(fd, nextReg + 1);
                    fd.write("je " + si + "\n");
                    fd.write("jmp " + no + "\n");
                        
	        }
	        else if (value.compareTo("!=") == 0) {

                    AssemblerInfo.saveReg(fd, nextReg + 1);
                    right.generateCode(fd, nextReg+1, si, no);

                    fd.write("push "+reg+"\n");
                    fd.write("fld qword ["+AssemblerInfo.getSp()+"]\n");
			
                    fd.write("push "+nreg+"\n");
                    fd.write("fld qword ["+AssemblerInfo.getSp()+"]\n");

                    fd.write("fcomip st1\n");
                    fd.write("fincstp\n");

                    fd.write("pop "+nreg+"\n");
                    fd.write("pop "+reg+"\n");

                    AssemblerInfo.restoreReg(fd, nextReg + 1);
                    fd.write("jne " + si + "\n");
                    fd.write("jmp " + no + "\n");

	        }
	        else if (value.compareTo("<") == 0) {

		    left.generateCode(fd, nextReg, si, no);

                    AssemblerInfo.saveReg(fd, nextReg + 1);
                    right.generateCode(fd, nextReg+1, si, no);

                    fd.write("push "+reg+"\n");
                    fd.write("fld qword ["+AssemblerInfo.getSp()+"]\n");
			
                    fd.write("push "+nreg+"\n");
                    fd.write("fld qword ["+AssemblerInfo.getSp()+"]\n");

                    fd.write("fcomip st1\n");
                    fd.write("fincstp\n");

                    fd.write("pop "+nreg+"\n");
                    fd.write("pop "+reg+"\n");

                    AssemblerInfo.restoreReg(fd, nextReg + 1);
		    fd.write("jl " + si + "\n");
		    fd.write("jmp " + no + "\n");
	        }
	        else if (value.compareTo("<=") == 0) {

		    left.generateCode(fd, nextReg, si, no);

                    AssemblerInfo.saveReg(fd, nextReg + 1);
                    right.generateCode(fd, nextReg+1, si, no);

                    fd.write("push "+reg+"\n");
                    fd.write("fld qword ["+AssemblerInfo.getSp()+"]\n");
			
                    fd.write("push "+nreg+"\n");
                    fd.write("fld qword ["+AssemblerInfo.getSp()+"]\n");

                    fd.write("fcomip st1\n");
                    fd.write("fincstp\n");

                    fd.write("pop "+nreg+"\n");
                    fd.write("pop "+reg+"\n");

                    AssemblerInfo.restoreReg(fd, nextReg + 1);
		    fd.write("jle " + si + "\n");
		    fd.write("jmp " + no + "\n");
	        }
	        else if (value.compareTo(">") == 0) {

		    left.generateCode(fd, nextReg, si, no);

                    AssemblerInfo.saveReg(fd, nextReg + 1);
                    right.generateCode(fd, nextReg+1, si, no);

                    fd.write("push "+reg+"\n");
                    fd.write("fld qword ["+AssemblerInfo.getSp()+"]\n");
			
                    fd.write("push "+nreg+"\n");
                    fd.write("fld qword ["+AssemblerInfo.getSp()+"]\n");

                    fd.write("fcomip st1\n");
                    fd.write("fincstp\n");

                    fd.write("pop "+nreg+"\n");
                    fd.write("pop "+reg+"\n");

                    AssemblerInfo.restoreReg(fd, nextReg + 1);
		    fd.write("jg " + si + "\n");
		    fd.write("jmp " + no + "\n");
	        }
	        else{

		    left.generateCode(fd, nextReg, si, no);

                    AssemblerInfo.saveReg(fd, nextReg + 1);
                    right.generateCode(fd, nextReg+1, si, no);

                    fd.write("push "+reg+"\n");
                    fd.write("fld qword ["+AssemblerInfo.getSp()+"]\n");
			
                    fd.write("push "+nreg+"\n");
                    fd.write("fld qword ["+AssemblerInfo.getSp()+"]\n");

                    fd.write("fcomip st1\n");
                    fd.write("fincstp\n");

                    fd.write("pop "+nreg+"\n");
                    fd.write("pop "+reg+"\n");

                    AssemblerInfo.restoreReg(fd, nextReg + 1);

		    fd.write("jge " + si + "\n");
		    fd.write("jmp " + no + "\n");
	        }
                break;
            default:
	        if (value.compareTo("!") == 0) {
		    left.generateCode(fd, nextReg, no, si);
	        }
                else if (value.compareTo("==") == 0) {
		    String newsi = AssemblerInfo.newLabel();
		    String newno = AssemblerInfo.newLabel();
		    left.generateCode(fd, nextReg, newsi, newno);
		    fd.write(newsi + ": \n");
                    right.generateCode(fd, nextReg, si, no);
                    fd.write(newno + ": \n");
                    right.generateCode(fd, nextReg, no, si);
	        }
	        else if (value.compareTo("!=") == 0) {

		    String newsi = AssemblerInfo.newLabel();
		    String newno = AssemblerInfo.newLabel();
		    left.generateCode(fd, nextReg, newsi, newno);
		    fd.write(newsi + ": \n");
                    right.generateCode(fd, nextReg, no, si);
                    fd.write(newno + ": \n");
                    right.generateCode(fd, nextReg, si, no);
                }
	        else if (value.compareTo("&&") == 0) {
		    String nextL = AssemblerInfo.newLabel();
		    left.generateCode(fd, nextReg, nextL, no);
		    fd.write(nextL + ": \n");
                    right.generateCode(fd, nextReg, si, no);				
	        }
	        else if (value.compareTo("||") == 0) {
		    String nextL = AssemblerInfo.newLabel();
		    left.generateCode(fd, nextReg, si, nextL);
		    fd.write(nextL + ": \n");
                    right.generateCode(fd, nextReg, si, no);
                }
            }   
                
	}
	catch (IOException e) {
	    System.out.println("Error escribiendo en archivo de salida\n");
	}
    }
}

