import java.io.*;

public class ASTAritmetica extends ASTExpresion {

    //@ invariant left != null;
    //@ requires v != null;
    //@ requires l != null;
	
    public ASTAritmetica(String v, ASTExpresion l, ASTExpresion r) {
	super(v, l, r);
    }

    public void update() {
	Tipo lstate = left.getState();

	if(right!=null) {
	    Tipo rstate = right.getState();

            if(value == "%")
	        state = lstate.mod(rstate);
            else
	        state = lstate.aritmetica(rstate);

            if(state != null && state.equals(new Basico(2))) {		
		if(lstate.equals(new Basico(1)))
		    left = new ASTCast(left,new Basico(2));
		
		if(rstate.equals(new Basico(1)))
		    right = new ASTCast(right,new Basico(2));
            }

	}
	else 
	    state = lstate.aritmetica();
    }
  
    public String printTree() {	
	String m = new String(value + "( " + left.printTree() + ", " + right.printTree() + " )");
	return m;
    }

    public void generateCode(Writer fd, int nextReg, String si, String no) throws IOException {	
	try {
            String reg = AssemblerInfo.getNombresRegAtPos(nextReg);
            String nreg = AssemblerInfo.getNombresRegAtPos(nextReg+1);	
	    int basicType = ((Basico)state).getNBasico();

	    switch (basicType) {
	    case 1:
		if (value.compareTo("+") == 0) {
		    left.generateCode(fd, nextReg, si, no);		    
		    AssemblerInfo.saveReg(fd, nextReg + 1);
		    right.generateCode(fd, nextReg + 1, si, no);
		    fd.write("add " + reg + ", " + nreg + "\n");		
		    AssemblerInfo.restoreReg(fd, nextReg + 1);
		}	 
		else if (value.compareTo("-") == 0) {

		    left.generateCode(fd, nextReg, si, no);
		    if (right == null) {
		    
			fd.write("neg " + reg + "\n");

		    }
		    else {

			AssemblerInfo.saveReg(fd, nextReg + 1);
			right.generateCode(fd, nextReg + 1, si, no);
			fd.write("sub " + reg + ", " + nreg + "\n");
			AssemblerInfo.restoreReg(fd, nextReg + 1); 

		    }
		}
		else if (value.compareTo("*") == 0) {

		    left.generateCode(fd, nextReg, si, no);
		    AssemblerInfo.saveReg(fd, nextReg + 1);
		    right.generateCode(fd, nextReg + 1, si, no);
		    fd.write("imul " + reg + ", " + nreg + "\n");
		    AssemblerInfo.restoreReg(fd, nextReg + 1);

		}
		else if (value.compareTo("/") == 0){

		    left.generateCode(fd, nextReg, si, no);
		    AssemblerInfo.saveReg(fd, nextReg + 1);
		    right.generateCode(fd, nextReg + 1, si, no);

                    if( reg == "rax"){
                        fd.write("push rbx\n");
                        fd.write("push rdx\n");
                    }
                    else if( reg == "rbx"){
                        fd.write("push rax\n");
                        fd.write("push rdx\n");
                    }
                    else if( reg == "rdx"){
                        fd.write("push rax\n");
                        fd.write("push rbx\n");
                    }
                    else{
                        fd.write("push rax\n");
                        fd.write("push rbx\n");
                        fd.write("push rdx\n");
                    }

                    if(nreg == "rax"){
                        fd.write("mov rbx, "+nreg+"\n");
                        fd.write("mov rax, " + reg + "\n");
                    }
                    else{
                        fd.write("mov rax, " + reg + "\n");
                        fd.write("mov rbx, "+nreg+"\n");
                    }

                    fd.write("mov rdx, 0\n");

		    fd.write("idiv rbx \n");

                    fd.write("mov "+ reg +", rax \n");


                    if( reg == "rax"){
                        fd.write("pop rdx\n");
                        fd.write("pop rbx\n");
                    }
                    else if( reg == "rbx"){
                        fd.write("pop rdx\n");
                        fd.write("pop rax\n");
                    }
                    else if( reg == "rdx"){
                        fd.write("push rbx\n");
                        fd.write("pop rax\n");
                    }
                    else{
                        fd.write("pop rdx\n");
                        fd.write("pop rbx\n");
                        fd.write("pop rax\n");
                    }


                    AssemblerInfo.restoreReg(fd, nextReg + 1);
		}
		else if (value.compareTo("%") == 0) {

		    left.generateCode(fd, nextReg, si, no);
		    AssemblerInfo.saveReg(fd, nextReg + 1);
		    right.generateCode(fd, nextReg + 1, si, no);

                    if( reg == "rax"){
                        fd.write("push rbx\n");
                        fd.write("push rdx\n");
                    }
                    else if( reg == "rbx"){
                        fd.write("push rax\n");
                        fd.write("push rdx\n");
                    }
                    else if( reg == "rdx"){
                        fd.write("push rax\n");
                        fd.write("push rbx\n");
                    }
                    else{
                        fd.write("push rax\n");
                        fd.write("push rbx\n");
                        fd.write("push rdx\n");
                    }

                    if(nreg == "rax"){
                        fd.write("mov rbx, "+nreg+"\n");
                        fd.write("mov rax, " + reg + "\n");
                    }
                    else{
                        fd.write("mov rax, " + reg + "\n");
                        fd.write("mov rbx, "+nreg+"\n");
                    }

                    fd.write("mov rdx, 0\n");

		    fd.write("idiv rbx \n");

                    fd.write("mov "+ reg +", rdx \n");


                    if( reg == "rax"){
                        fd.write("pop rdx\n");
                        fd.write("pop rbx\n");
                    }
                    else if( reg == "rbx"){
                        fd.write("pop rdx\n");
                        fd.write("pop rax\n");
                    }
                    else if( reg == "rdx"){
                        fd.write("push rbx\n");
                        fd.write("pop rax\n");
                    }
                    else{
                        fd.write("pop rdx\n");
                        fd.write("pop rbx\n");
                        fd.write("pop rax\n");
                    }


                    AssemblerInfo.restoreReg(fd, nextReg + 1);

		}

		break;

	    case 2:

		if (value.compareTo("+") == 0) {
		
		    left.generateCode(fd, nextReg, si, no);		    
		    AssemblerInfo.saveReg(fd, nextReg + 1);
		    right.generateCode(fd, nextReg + 1, si, no);
			
		    fd.write("push "+reg+"\n");
		    fd.write("fld qword ["+AssemblerInfo.getSp()+"]\n");
			
		    fd.write("push "+nreg+"\n");
		    fd.write("fadd qword ["+AssemblerInfo.getSp()+"]\n");
			
		    fd.write("fstp qword ["+AssemblerInfo.getSp()+" + 8]\n");
		    
		    fd.write("pop " + nreg + "\n");
		    fd.write("pop " + reg + "\n");
		    
		    AssemblerInfo.restoreReg(fd, nextReg + 1);

		}	 
		else if (value.compareTo("-") == 0) {
		
		    if(right == null){

			left.generateCode(fd, nextReg, si, no);		    
			
			fd.write("push "+reg+"\n");
			fd.write("fld qword ["+AssemblerInfo.getSp()+"]\n");
			
			fd.write("fchs\n");
		    
			fd.write("fstp qword ["+AssemblerInfo.getSp()+"]\n");
		    
			fd.write("pop " + reg + "\n");

		    }
		    else{

			left.generateCode(fd, nextReg, si, no);		    
			AssemblerInfo.saveReg(fd, nextReg + 1);
			right.generateCode(fd, nextReg + 1, si, no);
			
			fd.write("push "+reg+"\n");
			fd.write("fld qword ["+AssemblerInfo.getSp()+"]\n");
			
			fd.write("push "+nreg+"\n");
			fd.write("fsub qword ["+AssemblerInfo.getSp()+"]\n");
		    
			fd.write("fstp qword ["+AssemblerInfo.getSp()+" + 8]\n");
		    
			fd.write("pop " + nreg + "\n");
			fd.write("pop " + reg + "\n");
			
			AssemblerInfo.restoreReg(fd, nextReg + 1);
		    }		    		    
		}
	        else if (value.compareTo("*") == 0) {
		
		    left.generateCode(fd, nextReg, si, no);		    
		    AssemblerInfo.saveReg(fd, nextReg + 1);
		    right.generateCode(fd, nextReg + 1, si, no);
			
		    fd.write("push "+reg+"\n");
		    fd.write("fld qword ["+AssemblerInfo.getSp()+"]\n");
			
		    fd.write("push "+nreg+"\n");
		    fd.write("fmul qword ["+AssemblerInfo.getSp()+"]\n");
			
		    fd.write("fstp qword ["+AssemblerInfo.getSp()+" + 8]\n");
		    
		    fd.write("pop " + nreg + "\n");
		    fd.write("pop " + reg + "\n");

		    AssemblerInfo.restoreReg(fd, nextReg + 1);


		}
		else if ((value.compareTo("/") == 0)) {
		
		    left.generateCode(fd, nextReg, si, no);		    
		    AssemblerInfo.saveReg(fd, nextReg + 1);
		    right.generateCode(fd, nextReg + 1, si, no);
			
		    fd.write("push "+reg+"\n");
		    fd.write("fld qword ["+AssemblerInfo.getSp()+"]\n");
			
		    fd.write("push "+nreg+"\n");
		    fd.write("fdiv qword ["+AssemblerInfo.getSp()+"]\n");
			
		    fd.write("fstp qword ["+AssemblerInfo.getSp()+" + 8]\n");
		    
		    fd.write("pop " + nreg + "\n");
		    fd.write("pop " + reg + "\n");
		    AssemblerInfo.restoreReg(fd, nextReg + 1);

		}
		break;
	    default:
		break;
	    }	    
    	}
	catch (Exception e) {
    	    System.out.println("Error escribiendo en archivo de salida\n");
    	}
    }
}
