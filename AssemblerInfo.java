import java.io.*;
import java.util.LinkedList;
import java.util.Iterator;

public class AssemblerInfo {
    
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

    public static void writeProgram(Writer fd, int size, SymTable table, LinkedList globales, LinkedList procedimientos) {
	try {
	    fd.write("%include \"asm_io.inc\"\n\n" +
		     "section .bss\n" +
		     "   static resb " + size + "\n" +
		     "   memsfpu resb 108\n\n" +
		     "section .text\n\n");

            Iterator it = procedimientos.iterator();
            String proc = "";
            SymProc procedimiento;

            while(it.hasNext()){
                proc = (String) it.next();
                procedimiento = (SymProc)table.getSym(proc);
                if(!proc.equals("main")){
                    fd.write("proc"+proc+":\n");
                    writeProc(fd, procedimiento);
                    fd.write("\n");
               }
            }
	
            fd.write("   global main\n" +
		     "main:\n");

            while(it.hasNext())
                ((ASTAsignacion)it.next()).generateCode(fd,0,"","");

             writeProc(fd, (SymProc) table.getSym("main"));

	}
	catch (Exception e) {
            e.printStackTrace();
	    System.out.println("Error escribiendo en archivo de salida.");
	}	
    }

    public static void saveRegLlamado(Writer fd,int n) throws IOException{

            if(n>nombresRegSize)
                n = nombresRegSize;
	    
            for(int i = 0; i<n; i++)
                fd.write("push "+nombresReg[i]+"\n");

    }

    public static void restoreRegLlamado(Writer fd,int n) throws IOException{

            if(n>nombresRegSize)
                n = nombresRegSize;
	    
            for(int i = n-1; i>=0; i--)
                fd.write("pop "+nombresReg[i]+"\n");
    }

    public static void writeProc(Writer fd, SymProc pro) throws IOException {
	int size = pro.getTamlocal();
        String returnL = newLabel();
	
	    fd.write("enter " + Integer.toString(size) + ", 0\n");
	    pro.getBloque().generateCode(fd, 0,"", returnL);
            fd.write(returnL+":\n");
	    fd.write("leave\nret\n");
    }

    public static void saveSpecificReg(Writer fd, String reg) throws IOException {
	    fd.write("push " + reg + "\n");
    }

    public static void restoreSpecificReg(Writer fd, String reg) throws IOException {
	    fd.write("pop " + reg + "\n");
    }

    public static void saveReg(Writer fd, int reg) throws IOException {
	    if (reg >= nombresRegSize) 
		fd.write("push " +  nombresReg[reg % nombresRegSize] + "\n");
    }

    public static void restoreReg(Writer fd, int reg) throws IOException {
	    if (reg >= nombresRegSize) 
		fd.write("pop " + nombresReg[reg % nombresRegSize] + "\n");
    }

    public static ASTCast checkCast(Tipo dest, Tipo source) {

        if (source == null) return null;
        if (source == null) return null;

        if (dest.equals(new Basico(1)) && source.equals(new Basico(2)))
            return new ASTCast(null, new Basico(1));

        if (dest.equals(new Basico(2)) && source.equals(new Basico(1)))
            return new ASTCast(null, new Basico(2));

        if (dest.equals(new Basico(4)) && source.equals(new Basico(1)))
            return new ASTCast(null, new Basico(4));

	return null;
    }

}
