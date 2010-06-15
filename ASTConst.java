import java.io.*;

public class ASTConst extends ASTExpresion {
	
    private long caseInt;
    private double caseFloat;
    private String caseChar;
    private boolean caseBool;

    public ASTConst(long ca) {
	super(null,null,null);
	state = new Basico(1);
	caseInt = ca;
    }

    public ASTConst(double f) {
	super(null,null,null);
	state = new Basico(2);
	caseFloat = f;
    }

    public ASTConst(boolean b) {
	super(null,null,null);
	state = new Basico(3);
	caseBool = b;
    }       

    public ASTConst(String ch) {
	super(null,null,null);
	state = new Basico(4);
	caseChar = ch;
    }

    public void setCaseInt(long i) {
	caseInt = i;
    }

    public void setCaseFloat(double f) {
	caseFloat = f;
    }

    public void setCaseChar(String c) {
	caseChar = c;
    }

    public void setCaseBool(boolean b) {
	caseBool = b;
    }

    public long getCaseInt() {
	return caseInt;
    }

    public double getCaseFloat() {
	return caseFloat;
    }

    public String getCaseChar() {
	return caseChar;
    }

    public boolean getCaseBool() {
	return caseBool;
    }

    public void update() {};

    public String printTree() {
	String m = new String();

	switch (((Basico)state).getNBasico()) {
	case 1:	    
	    return "CONST( " + caseInt + " )";
	case 2:
	    return "CONST( " + caseFloat + " )";
	case 3:
	    return "CONST( " + caseBool + " )";
	case 4:	    
	    return "CONST( " + caseChar + " )";
	}

	return m;
    }    

    public void generateCode(Writer fd, int nextReg, String si, String no) throws IOException {
	int basicType = ((Basico)state).getNBasico();
	String reg = AssemblerInfo.getNombresRegAtPos(nextReg);	
	
	try {
	    switch (basicType) {
	    case 1:
		fd.write("mov " + reg + ", " + caseInt + "\n");
		break;
	    case 2:
		fd.write("mov " + reg + ", 0x" + Long.toHexString(Double.doubleToLongBits(caseFloat)) + "\n");
		break;
	    case 3:
		if (caseBool)
		    fd.write("jmp " + si + "\n");
		else
		    fd.write("jmp " + no + "\n");
		break;
	    case 4:
		char c = caseChar.charAt(0);
		int ascii = (int) c;
		fd.write("mov " + reg + ", " + Integer.toString(ascii) + "\n");
		break;
	    default:
	    }	       	    
    	}
    	catch (Exception e) {
    	    System.out.println("Error escribiendo en archivo de salida\n");
    	}
    }
}
