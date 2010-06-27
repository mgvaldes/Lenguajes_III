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
		     "   static resb " + size + "\n\n" +
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

    public static void generateIdenPushCastCode(Writer fd, int nextReg, Tipo dest, Tipo source) throws IOException{

        String reg = AssemblerInfo.getNombresRegAtPos(nextReg); 
        String nreg = getNombresRegAtPos(nextReg+1);  

        if(source instanceof Arreglo){

            Tipo bsource = ((Arreglo) source).getTipoBase();
            Tipo bdest = ((Arreglo) dest).getTipoBase();

            if(bsource instanceof Basico){
                ASTCast cast = AssemblerInfo.checkCast(bdest, bsource);
                for(int i = 0; i < source.getTam(); i += 8){
                    if(cast != null){
                        fd.write("mov "+nreg+", ["+reg+"]\n");
                        cast.generateCode(fd, nextReg+1, "", "");
                        fd.write("push qword ["+nreg+"]\n");
                    }
                    else
                        fd.write("push qword ["+reg+"]\n");
                    fd.write("add "+reg+",8\n");
                }
            }
            else{
                for(int i = 0; i < source.getTam(); i += bsource.getTam())
                    generateIdenPushCastCode(fd, nextReg, bdest, bsource);
            }
        }
       else if(source instanceof Registro){

            Iterator its = ((Registro) source).getTipos().iterator();
            Iterator itd = ((Registro) dest).getTipos().iterator();

            Tipo esource;
            Tipo edest;

            while(its.hasNext()){

                esource = (Tipo) its.next();
                edest = (Tipo) itd.next();

                if(esource instanceof Basico){
                    ASTCast cast = AssemblerInfo.checkCast(edest, esource);
                    if(cast != null){
                        fd.write("mov "+nreg+", ["+reg+"]\n");
                        cast.generateCode(fd, nextReg+1, "", "");
                        fd.write("push qword ["+nreg+"]\n");
                    }
                    else
                        fd.write("push qword ["+reg+"]\n");
                    fd.write("add "+reg+",8\n");
                }
                else
                    generateIdenPushCastCode(fd, nextReg, edest, esource);
            }
        }
       else if(source instanceof Union){

            String nnreg = getNombresRegAtPos(nextReg+2);  
            String end = newLabel();

            Iterator ittd = ((Union) dest).getTipos().iterator();
            Iterator itts = ((Union) source).getTipos().iterator();

            fd.write("mov "+nreg+", ["+reg+"]\n");
            fd.write("push qword ["+reg+"]\n");
            fd.write("add "+reg+",8\n");

            int i = 0;

            while(itts.hasNext()){

                Tipo edest = (Tipo) ittd.next();
                Tipo esource = (Tipo) ittd.next();

                String nextLabel = newLabel();

                fd.write("cmp "+nreg+", "+i+"\n");
                fd.write("jne "+nextLabel+"\n");

                if(esource instanceof Basico){
                    ASTCast cast = AssemblerInfo.checkCast(edest, esource);
                    if(cast != null){
                        fd.write("mov "+nnreg+", ["+reg+"]\n");
                        cast.generateCode(fd, nextReg+2, "", "");
                        fd.write("push qword ["+nnreg+"]\n");
                    }
                    else
                        fd.write("push qword ["+reg+"]\n");

                    fd.write("add "+reg+",8\n");
                }
                else
                    generateIdenPushCastCode(fd, nextReg, edest, esource);

                fd.write("jmp "+end+"\n");

                fd.write(nextLabel+":\n");

            }

            fd.write(end+":\n");

        }
    }

    public static void generateIdenPopCastCode(Writer fd, int nextReg, Tipo dest, Tipo source) throws IOException{

        String reg = AssemblerInfo.getNombresRegAtPos(nextReg); 
        String nreg = AssemblerInfo.getNombresRegAtPos(nextReg+1); 

        if(source instanceof Arreglo){

            Tipo bsource = ((Arreglo) source).getTipoBase();
            Tipo bdest = ((Arreglo) dest).getTipoBase();

            if(bsource instanceof Basico){
                ASTCast cast = AssemblerInfo.checkCast(bdest, bsource);

                for(int i = 0; i < source.getTam(); i += 8){
                    fd.write("pop "+nreg+"\n");
                    if(cast != null)
                        cast.generateCode(fd, nextReg+1, "", "");
                    fd.write("mov ["+reg+"], "+nreg+"\n");
                    fd.write("sub "+reg+",8\n");
                }

            }
            else{
                for(int i = 0; i < source.getTam(); i += bsource.getTam())
                    generateIdenPopCastCode(fd, nextReg, bdest, bsource);
            }
        }
       else if(source instanceof Registro){

            Object[] its = ((Registro) source).getTipos().toArray(); 
            Object[] itd = ((Registro) dest).getTipos().toArray(); 

            Tipo esource;
            Tipo edest;

            for(int i = ((Registro) source).getTipos().size() -1; i>=0; i--){

                esource = (Tipo) its[i];
                edest = (Tipo) itd[i];

                if(esource instanceof Basico){
                    ASTCast cast = AssemblerInfo.checkCast(edest, esource);
                    fd.write("pop "+nreg+"\n");
                    if(cast != null)
                        cast.generateCode(fd, nextReg+1, "", "");
                    fd.write("mov ["+reg+"], "+nreg+"\n");
                    fd.write("sub "+reg+",8\n");
                }
                else
                    generateIdenPopCastCode(fd, nextReg, edest, esource);
            }
        }
       else if(source instanceof Union){

            for(int k = 0; k < source.getTam(); k+=8){
                fd.write("pop "+reg+"\n");
                fd.write("mov ["+reg+"], "+nreg+"\n");
                fd.write("sub "+reg+", 8\n");
            }

            generateUnionCastCode(fd, nextReg, dest, source);

       }

    }

    private static void generateUnionCastCode(Writer fd, int nextReg, Tipo dest, Tipo source) throws IOException{

        String reg = AssemblerInfo.getNombresRegAtPos(nextReg); 
        String nreg = AssemblerInfo.getNombresRegAtPos(nextReg+1);
        String nnreg = AssemblerInfo.getNombresRegAtPos(nextReg+2);  

        if(source instanceof Arreglo){

            Tipo bsource = ((Arreglo) source).getTipoBase();
            Tipo bdest = ((Arreglo) dest).getTipoBase();

            if(bsource instanceof Basico){
                ASTCast cast = AssemblerInfo.checkCast(bdest, bsource);
                for(int i = 0; i < source.getTam(); i += 8){
                    if(cast != null){
                        fd.write("mov "+nreg+", ["+reg+"]\n");
                        cast.generateCode(fd, nextReg+1, "", "");
                        fd.write("mov ["+reg+"], "+nreg+"\n");
                    }
                    fd.write("add "+reg+",8\n");
                }
            }
            else{
                for(int i = 0; i < source.getTam(); i += bsource.getTam())
                    generateUnionCastCode(fd, nextReg, bdest, bsource);
            }
        }
       else if(source instanceof Registro){

            Iterator its = ((Registro) source).getTipos().iterator();
            Iterator itd = ((Registro) dest).getTipos().iterator();

            Tipo esource;
            Tipo edest;

            while(its.hasNext()){

                esource = (Tipo) its.next();
                edest = (Tipo) itd.next();

                if(esource instanceof Basico){
                    ASTCast cast = AssemblerInfo.checkCast(edest, esource);
                    if(cast != null){
                        fd.write("mov "+nreg+", ["+reg+"]\n");
                        cast.generateCode(fd, nextReg+1, "", "");
                        fd.write("mov ["+reg+"], "+nreg+"\n");
                    }
                    fd.write("add "+reg+",8\n");
                }
                else
                    generateUnionCastCode(fd, nextReg, edest, esource);
            }
        }
       else if(source instanceof Union){

            String end = newLabel();

            Iterator ittd = ((Union) dest).getTipos().iterator();
            Iterator itts = ((Union) source).getTipos().iterator();

            fd.write("mov "+nreg+", ["+reg+"]\n");
            fd.write("add "+reg+",8\n");

            int i = 0;

            while(itts.hasNext()){

                Tipo edest = (Tipo) ittd.next();
                Tipo esource = (Tipo) ittd.next();

                String nextLabel = newLabel();

                fd.write("cmp "+nreg+", "+i+"\n");
                fd.write("jne "+nextLabel+"\n");

                if(esource instanceof Basico){
                    ASTCast cast = AssemblerInfo.checkCast(edest, esource);
                    if(cast != null){
                        fd.write("mov "+nnreg+", ["+reg+"]\n");
                        cast.generateCode(fd, nextReg+2, "", "");
                        fd.write("mov ["+reg+"], "+nnreg+"\n");
                    }
                    fd.write("add "+reg+",8\n");
                }
                else
                    generateUnionCastCode(fd, nextReg, edest, esource);

                fd.write("jmp "+end+"\n");

                fd.write(nextLabel+":\n");

            }

            fd.write(end+":\n");

        }

    }

}
