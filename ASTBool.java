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
		    if (left instanceof ASTIdentificador) {
			fd.write("mov " + reg + ", [" + reg + "]\n");
		    }
                    AssemblerInfo.saveReg(fd, nextReg + 1);
                    right.generateCode(fd, nextReg + 1, si, no);
		    if (right instanceof ASTIdentificador) {
			fd.write("mov " + nreg + ", [" + nreg + "]\n");
		    }
                    fd.write("cmp " + reg + ", " + nreg + "\n");
                    AssemblerInfo.restoreReg(fd, nextReg + 1);
                    fd.write("je " + si + "\n");
                    fd.write("jmp " + no + "\n");
	        }
	        else if (value.compareTo("!=") == 0) {
		    left.generateCode(fd, nextReg, si, no);
                    if (left instanceof ASTIdentificador) {
			fd.write("mov " + reg + ", [" + reg + "]\n");
		    }
                    AssemblerInfo.saveReg(fd, nextReg + 1);
                    right.generateCode(fd, nextReg + 1, si, no);
		    if (right instanceof ASTIdentificador) {
			fd.write("mov " + nreg + ", [" + nreg + "]\n");
		    }
                    fd.write("cmp " + reg + ", " + nreg + "\n");
                    AssemblerInfo.restoreReg(fd, nextReg + 1);
                    fd.write("jne " + si + "\n");
                    fd.write("jmp " + no + "\n");
	        }
	        else if (value.compareTo("<") == 0) {
		    left.generateCode(fd, nextReg, si, no);
		    if (left instanceof ASTIdentificador) {
			fd.write("mov " + reg + ", [" + reg + "]\n");
		    }
                    AssemblerInfo.saveReg(fd, nextReg + 1);
                    right.generateCode(fd, nextReg + 1, si, no);
		    if (right instanceof ASTIdentificador) {
			fd.write("mov " + nreg + ", [" + nreg + "]\n");
		    }
		    fd.write("cmp " + reg + ", " + nreg + "\n");
		    AssemblerInfo.restoreReg(fd, nextReg + 1);
		    fd.write("jl " + si + "\n");
		    fd.write("jmp " + no + "\n");
	        }
	        else if (value.compareTo("<=") == 0) {
		    left.generateCode(fd, nextReg, si, no);
		    if (left instanceof ASTIdentificador) {
			fd.write("mov " + reg + ", [" + reg + "]\n");
		    }
                    AssemblerInfo.saveReg(fd, nextReg + 1);
                    right.generateCode(fd, nextReg + 1, si, no);
		    if (right instanceof ASTIdentificador) {
			fd.write("mov " + nreg + ", [" + nreg + "]\n");
		    }
		    fd.write("cmp " + reg + ", " + nreg + "\n");
		    AssemblerInfo.restoreReg(fd, nextReg + 1);
		    fd.write("jle " + si + "\n");
		    fd.write("jmp " + no + "\n");
	        }
	        else if (value.compareTo(">") == 0) {
		    left.generateCode( fd, nextReg, si, no);
		    if (left instanceof ASTIdentificador) {
			fd.write("mov " + reg + ", [" + reg + "]\n");
		    }
                    AssemblerInfo.saveReg(fd, nextReg + 1);
                    right.generateCode(fd, nextReg + 1, si, no);
		    if (right instanceof ASTIdentificador) {
			fd.write("mov " + nreg + ", [" + nreg + "]\n");
		    }
		    fd.write("cmp " + reg + ", " + nreg + "\n");
		    AssemblerInfo.restoreReg(fd, nextReg + 1);
		    fd.write("jg " + si + "\n");
		    fd.write("jmp " + no + "\n");
	        }
	        else {
		    left.generateCode(fd, nextReg, si, no);
		    if (left instanceof ASTIdentificador) {
			fd.write("mov " + reg + ", [" + reg + "]\n");
		    }
                    AssemblerInfo.saveReg(fd, nextReg + 1);
                    right.generateCode(fd, nextReg + 1, si, no);
		    if (right instanceof ASTIdentificador) {
			fd.write("mov " + nreg + ", [" + nreg + "]\n");
		    }
		    fd.write("cmp " + reg + ", " + nreg + "\n");
		    AssemblerInfo.restoreReg(fd, nextReg + 1);
		    fd.write("jge " + si + "\n");
		    fd.write("jmp " + no + "\n");
	        }
                break;
            case 2:
	        if (value.compareTo("==") == 0) {

		    left.generateCode(fd, nextReg, si, no);
		    if (left instanceof ASTIdentificador) {
			fd.write("mov " + reg + ", [" + reg + "]\n");
		    }

                    AssemblerInfo.saveReg(fd, nextReg + 1);
                    right.generateCode(fd, nextReg + 1, si, no);
		    if (right instanceof ASTIdentificador) {
			fd.write("mov " + nreg + ", [" + nreg + "]\n");
		    }

                    fd.write("push " + reg + "\n");
                    fd.write("fld qword [" + AssemblerInfo.getSp() + "]\n");
			
                    fd.write("push " + nreg + "\n");
                    fd.write("fld qword [" + AssemblerInfo.getSp() + "]\n");

                    fd.write("fcomip st1\n");
                    fd.write("finit\n");

                    fd.write("pop " + nreg + "\n");
                    fd.write("pop " + reg + "\n");

                    AssemblerInfo.restoreReg(fd, nextReg + 1);
                    fd.write("je " + si + "\n");
                    fd.write("jmp " + no + "\n");
                        
	        }
	        else if (value.compareTo("!=") == 0) {

		    left.generateCode(fd, nextReg, si, no);

                    if (left instanceof ASTIdentificador) {
			fd.write("mov " + reg + ", [" + reg + "]\n");
		    }

                    AssemblerInfo.saveReg(fd, nextReg + 1);
                    right.generateCode(fd, nextReg + 1, si, no);
		    if (right instanceof ASTIdentificador) {
			fd.write("mov " + nreg + ", [" + nreg + "]\n");
		    }

                    fd.write("push " + reg + "\n");
                    fd.write("fld qword [" + AssemblerInfo.getSp() + "]\n");
			
                    fd.write("push " + nreg + "\n");
                    fd.write("fld qword [" + AssemblerInfo.getSp() + "]\n");

                    fd.write("fcomip st1\n");
                    fd.write("finit\n");

                    fd.write("pop " + nreg + "\n");
                    fd.write("pop " + reg + "\n");

                    AssemblerInfo.restoreReg(fd, nextReg + 1);
                    fd.write("jne " + si + "\n");
                    fd.write("jmp " + no + "\n");

	        }
	        else if (value.compareTo("<") == 0) {

		    left.generateCode(fd, nextReg, si, no);

		    if (left instanceof ASTIdentificador) {
			fd.write("mov " + reg + ", [" + reg + "]\n");
		    }

                    AssemblerInfo.saveReg(fd, nextReg + 1);
                    right.generateCode(fd, nextReg + 1, si, no);
		    if (right instanceof ASTIdentificador) {
			fd.write("mov " + nreg + ", [" + nreg + "]\n");
		    }

                    fd.write("push " + reg + "\n");
                    fd.write("fld qword ["+AssemblerInfo.getSp()+"]\n");
			
                    fd.write("push " + nreg + "\n");
                    fd.write("fld qword [" + AssemblerInfo.getSp() + "]\n");

                    fd.write("fcomip st1\n");
                    fd.write("finit\n");

                    fd.write("pop " + nreg + "\n");
                    fd.write("pop " + reg + "\n");

                    AssemblerInfo.restoreReg(fd, nextReg + 1);
		    fd.write("jg " + si + "\n");
		    fd.write("jmp " + no + "\n");
	        }
	        else if (value.compareTo("<=") == 0) {

		    left.generateCode(fd, nextReg, si, no);
		    if (left instanceof ASTIdentificador) {
			fd.write("mov " + reg + ", [" + reg + "]\n");
		    }

                    AssemblerInfo.saveReg(fd, nextReg + 1);
                    right.generateCode(fd, nextReg + 1, si, no);
		    if (right instanceof ASTIdentificador) {
			fd.write("mov " + nreg + ", [" + nreg + "]\n");
		    }
                    fd.write("push " + reg + "\n");
                    fd.write("fld qword [" + AssemblerInfo.getSp() + "]\n");
			
                    fd.write("push " + nreg + "\n");
                    fd.write("fld qword [" + AssemblerInfo.getSp() + "]\n");

                    fd.write("fcomip st1\n");
                    fd.write("finit\n");

                    fd.write("pop " + nreg + "\n");
                    fd.write("pop " + reg + "\n");

                    AssemblerInfo.restoreReg(fd, nextReg + 1);
		    fd.write("jge " + si + "\n");
		    fd.write("jmp " + no + "\n");
	        }
	        else if (value.compareTo(">") == 0) {

		    left.generateCode(fd, nextReg, si, no);
		    if (left instanceof ASTIdentificador) {
			fd.write("mov " + reg + ", [" + reg + "]\n");
		    }

                    AssemblerInfo.saveReg(fd, nextReg + 1);
                    right.generateCode(fd, nextReg + 1, si, no);
		    if (right instanceof ASTIdentificador) {
			fd.write("mov " + nreg + ", [" + nreg + "]\n");
		    }

                    fd.write("push " + reg + "\n");
                    fd.write("fld qword [" + AssemblerInfo.getSp() + "]\n");
			
                    fd.write("push " + nreg + "\n");
                    fd.write("fld qword [" + AssemblerInfo.getSp() + "]\n");

                    fd.write("fcomip st1\n");
                    fd.write("finit\n");

                    fd.write("pop " + nreg + "\n");
                    fd.write("pop " + reg + "\n");

                    AssemblerInfo.restoreReg(fd, nextReg + 1);
		    fd.write("jl " + si + "\n");
		    fd.write("jmp " + no + "\n");
	        }
	        else{

		    left.generateCode(fd, nextReg, si, no);
		    if (left instanceof ASTIdentificador) {
			fd.write("mov " + reg + ", [" + reg + "]\n");
		    }

                    AssemblerInfo.saveReg(fd, nextReg + 1);
                    right.generateCode(fd, nextReg + 1, si, no);
		    if (right instanceof ASTIdentificador) {
			fd.write("mov " + nreg + ", [" + nreg + "]\n");
		    }

                    fd.write("push " + reg + "\n");
                    fd.write("fld qword [" + AssemblerInfo.getSp() + "]\n");
			
                    fd.write("push " + nreg + "\n");
                    fd.write("fld qword [" + AssemblerInfo.getSp() + "]\n");

                    fd.write("fcomip st1\n");
                    fd.write("finit\n");

                    fd.write("pop " + nreg + "\n");
                    fd.write("pop " + reg + "\n");

                    AssemblerInfo.restoreReg(fd, nextReg + 1);
		    fd.write("jle " + si + "\n");
		    fd.write("jmp " + no + "\n");
	        }
                break;
            default:
	        if (value.compareTo("!") == 0) {
		    left.generateCode(fd, nextReg, no, si);

		    if (left instanceof ASTIdentificador) {
			fd.write("mov " + reg + ", [" + reg + "]\n");
			fd.write("cmp " + reg + ", 1\n");
			fd.write("je " + no + "\n");
			fd.write("jmp " + si + "\n");
		    }
	        }
                else if (value.compareTo("==") == 0) {
		    String newsi = AssemblerInfo.newLabel();
		    String newno = AssemblerInfo.newLabel();

		    if ((left instanceof ASTIdentificador) && (right instanceof ASTIdentificador)) {
			left.generateCode(fd, nextReg, si, no);
			fd.write("mov " + reg + ", [" + reg + "]\n");			
			AssemblerInfo.saveReg(fd, nextReg + 1);
			right.generateCode(fd, nextReg + 1, si, no);
			fd.write("mov " + nreg + ", [" + nreg + "]\n");

			fd.write("cmp " + reg + ", " + nreg + "\n");
			AssemblerInfo.restoreReg(fd, nextReg + 1);
			fd.write("je " + si + "\n");
			fd.write("jmp " + no + "\n");
		    }
		    else if ((left instanceof ASTIdentificador) && !(right instanceof ASTIdentificador)) {
			left.generateCode(fd, nextReg, si, no);
			fd.write("mov " + reg + ", [" + reg + "]\n");			
			right.generateCode(fd, nextReg, newsi, newno);
			fd.write(newsi + ": \n");
			fd.write("cmp " + reg + ", 1\n");
			fd.write("je " + si + "\n");
			fd.write("jmp " + no + "\n");
			fd.write(newno + ": \n");
			fd.write("cmp " + reg + ", 0\n");
			fd.write("je " + si + "\n");
			fd.write("jmp " + no + "\n");
		    }
		    else if (!(left instanceof ASTIdentificador) && (right instanceof ASTIdentificador)) {
			left.generateCode(fd, nextReg, newsi, newno);
			right.generateCode(fd, nextReg, si, no);
			fd.write("mov " + reg + ", [" + reg + "]\n"); 			
			fd.write(newsi + ": \n");			
			fd.write("cmp " + reg + ", 1\n");
			fd.write("je " + si + "\n");
			fd.write("jmp " + no + "\n");
			fd.write(newno + ": \n");			
			fd.write("cmp " + reg + ", 0\n");
			fd.write("je " + si + "\n");
			fd.write("jmp " + no + "\n");			
		    }
		    else {
			left.generateCode(fd, nextReg, newsi, newno);	       	
			fd.write(newsi + ": \n");
			right.generateCode(fd, nextReg, si, no);
			fd.write(newno + ": \n");
			right.generateCode(fd, nextReg, no, si);			
		    }
	        }
	        else if (value.compareTo("!=") == 0) {		    
		    String newsi = AssemblerInfo.newLabel();
		    String newno = AssemblerInfo.newLabel();

		    if ((left instanceof ASTIdentificador) && (right instanceof ASTIdentificador)) {
			left.generateCode(fd, nextReg, si, no);
			fd.write("mov " + reg + ", [" + reg + "]\n");			
			right.generateCode(fd, nextReg + 1, si, no);
			fd.write("mov " + nreg + ", [" + nreg + "]\n");
			fd.write("cmp " + reg + ", " + nreg + "\n");
			fd.write("jne " + si + "\n");
			fd.write("jmp " + no + "\n");
		    }
		    else if ((left instanceof ASTIdentificador) && !(right instanceof ASTIdentificador)) {
			left.generateCode(fd, nextReg, si, no);
			fd.write("mov " + reg + ", [" + reg + "]\n");			
			right.generateCode(fd, nextReg + 1, newsi, newno);
			fd.write(newsi + ": \n");
			fd.write("cmp " + reg + ", 1\n");
			fd.write("jne " + si + "\n");
			fd.write("jmp " + no + "\n");
			fd.write(newno + ": \n");
			fd.write("cmp " + reg + ", 0\n");
			fd.write("jne " + si + "\n");
			fd.write("jmp " + no + "\n");
		    }
		    else if (!(left instanceof ASTIdentificador) && (right instanceof ASTIdentificador)) {
			left.generateCode(fd, nextReg, newsi, newno);
			right.generateCode(fd, nextReg + 1, si, no);
			fd.write("mov " + nreg + ", [" + nreg + "]\n"); 		    
			fd.write(newsi + ": \n");			
			fd.write("cmp " + nreg + ", 1\n");
			fd.write("jne " + si + "\n");
			fd.write("jmp " + no + "\n");
			fd.write(newno + ": \n");			
			fd.write("cmp " + nreg + ", 0\n");
			fd.write("jne " + si + "\n");
			fd.write("jmp " + no + "\n");			
		    }
		    else {
			left.generateCode(fd, nextReg, newsi, newno);
			fd.write(newsi + ": \n");
			right.generateCode(fd, nextReg + 1, no, si);
			fd.write(newno + ": \n");
			right.generateCode(fd, nextReg + 1, si, no);
		    }
                }
	        else if (value.compareTo("&&") == 0) {
		    String nextL = AssemblerInfo.newLabel();
		    AssemblerInfo.saveReg(fd, nextReg + 1);

		    if ((left instanceof ASTIdentificador) && (right instanceof ASTIdentificador)) {
			left.generateCode(fd, nextReg, si, no);
			fd.write("mov " + reg + ", [" + reg + "]\n");			
			right.generateCode(fd, nextReg + 1, si, no);
			fd.write("mov " + nreg + ", [" + nreg + "]\n");
			fd.write("cmp " + reg + ", " + nreg + "\n");
			fd.write("je " + si + "\n");
			fd.write("jmp " + no + "\n");
		    }
		    else if ((left instanceof ASTIdentificador) && !(right instanceof ASTIdentificador)) {
			left.generateCode(fd, nextReg, si, no);
			fd.write("mov " + reg + ", [" + reg + "]\n");			
			right.generateCode(fd, nextReg + 1, nextL, no);
			fd.write(nextL + ": \n");
			fd.write("cmp " + reg + ", 1\n");
			fd.write("je " + si + "\n");
			fd.write("jmp " + no + "\n");
		    }
		    else if (!(left instanceof ASTIdentificador) && (right instanceof ASTIdentificador)) {
			left.generateCode(fd, nextReg, nextL, no);
			fd.write(nextL + ": \n");			
			right.generateCode(fd, nextReg + 1, si, no);
			fd.write("mov " + nreg + ", [" + nreg + "]\n"); 		   
			fd.write("cmp " + nreg + ", 1\n");
			fd.write("je " + si + "\n");
			fd.write("jmp " + no + "\n");
		    }
		    else {
			left.generateCode(fd, nextReg, nextL, no);
			fd.write(nextL + ": \n");
			right.generateCode(fd, nextReg + 1, si, no);      
		    }
		    AssemblerInfo.restoreReg(fd, nextReg + 1);
	        }
	        else if (value.compareTo("||") == 0) {
		    String nextL = AssemblerInfo.newLabel();

		    if ((left instanceof ASTIdentificador) && (right instanceof ASTIdentificador)) {
			left.generateCode(fd, nextReg, si, no);
			fd.write("mov " + reg + ", [" + reg + "]\n");
			fd.write("cmp " + reg + ", 1\n");
			fd.write("je " + si + "\n");			
			right.generateCode(fd, nextReg + 1, si, no);
			fd.write("mov " + nreg + ", [" + nreg + "]\n");
			fd.write("cmp " + nreg + ", 1\n");
			fd.write("je " + si + "\n");			
			fd.write("jmp " + no + "\n");
		    }
		    else if ((left instanceof ASTIdentificador) && !(right instanceof ASTIdentificador)) {
			left.generateCode(fd, nextReg, si, no);
			fd.write("mov " + reg + ", [" + reg + "]\n");	
			fd.write("cmp " + reg + ", 1\n");
			fd.write("je " + si + "\n");					
			right.generateCode(fd, nextReg + 1, si, no);
		    }
		    else if (!(left instanceof ASTIdentificador) && (right instanceof ASTIdentificador)) {
			left.generateCode(fd, nextReg, si, nextL);
			fd.write(nextL + ": \n");	
			right.generateCode(fd, nextReg + 1, si, no);
			fd.write("mov " + nreg + ", [" + nreg + "]\n"); 	
			fd.write("cmp " + nreg + ", 1\n");
			fd.write("je " + si + "\n");
			fd.write("jmp " + no + "\n");
		    }
		    else {
			left.generateCode(fd, nextReg, si, nextL);
			fd.write(nextL + ": \n");
			right.generateCode(fd, nextReg + 1, si, no);
		    }
                }
            }   
                
	}
	catch (IOException e) {
	    System.out.println("Error escribiendo en archivo de salida\n");
	}
    }
}

