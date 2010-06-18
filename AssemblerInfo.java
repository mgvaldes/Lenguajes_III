import java.io.*;
import java.util.LinkedList;
import java.util.Iterator;

public class AssemblerInfo {
    
    //Agregar "rdi" y "rax"
    private static String[] nombresReg = {"rax", "rbx", "rcx", "rdx", "rdi", "rsi", "r8", "r9", "r10", "r11", "r12", "r13", "r14", "r15"};
    private static String fp = "rbp";
    private static String sp = "rsp";
    private static int nombresRegSize = 14;
    private static int labelNum = -1;

    public static String getNombresRegAtPos(int regPos) {	
	return nombresReg[regPos % nombresRegSize];
    }

    public static String getFp() {
	return fp;
    }

    public static String getSp() {
	return sp;
    }

    public static int getNombresRegSize() {
	return nombresRegSize;
    }

    public static String newLabel() {
	String label = new String("label");
	labelNum++;
	label = label.concat(Integer.toString(labelNum));
	return label;
    }

    public static void writeStart(Writer fd, int size, LinkedList globales) {
	try {
	    fd.write("%include \"asm_io.inc\"\n\n" +
		     "section .bss\n" +
		     "   static resb " + size + "\n\n" +
		     "section .text\n" +
		     "   global main\n" +
		     "main:\n");

            Iterator it = globales.iterator();

            while(it.hasNext())
                ((ASTAsignacion)it.next()).generateCode(fd,0,"");

	}
	catch (Exception e) {
	    System.out.println("Error escribiendo en archivo de salida\n");
	}	
    }

    public static void writeMain(Writer fd, SymProc pro) {
	int size = pro.getTamlocal();
	
	try {
	    fd.write("enter " + Integer.toString(size) + ", 0\n");
	    pro.getBloque().generateCode(fd, 0,"");
	    fd.write("leave\nret\n");
	}
	catch (Exception e) {
	    System.out.println("Error escribiendo en archivo de salida\n");
	}	
    }

    public static void saveSpecificReg(Writer fd, String reg) throws IOException {
	try {
	    fd.write("push " + reg + "\n");
	}
	catch (Exception e) {
	    System.out.println("Error escribiendo en archivo de salida\n");
	}
    }

    public static void restoreSpecificReg(Writer fd, String reg) throws IOException {
	try {
	    fd.write("pop " + reg + "\n");
	}
	catch (Exception e) {
	    System.out.println("Error escribiendo en archivo de salida\n");
	}
    }

    public static void saveReg(Writer fd, int reg) throws IOException {
	try {
	    if (reg >= nombresRegSize) {
		fd.write("push " +  nombresReg[reg % nombresRegSize] + "\n");
	    }	    
	}
	catch (Exception e) {
	    System.out.println("Error escribiendo en archivo de salida\n");
	}
    }

    public static void restoreReg(Writer fd, int reg) throws IOException {
	try {
	    if (reg >= nombresRegSize) {
		fd.write("pop " + nombresReg[reg % nombresRegSize] + "\n");
	    }	    
	}
	catch (Exception e) {
	    System.out.println("Error escribiendo en archivo de salida\n");
	}
    }
}
