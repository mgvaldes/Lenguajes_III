import java.util.LinkedList;
import java.util.Iterator;
import java.io.*;

public class ASTInvocarExpresion extends ASTExpresion {

    private String nombre;
    private LinkedList expresionEntrada;
    private SymProc procInfo;

    public ASTInvocarExpresion(String n, LinkedList e, Tipo s) {
	super("invocar", null, null);
	nombre = n;
	expresionEntrada = e;
	state = s;
    }

    public void setNombre(String n) {
  	nombre = n;
    }

    public void setExpresionEntrada(LinkedList e) {
	expresionEntrada = e;
    }

    public void setSymProc(SymProc p){
        procInfo = p;
    }
    public String getNombre() {
	return nombre;
    }

    public LinkedList getExpresionEntrada() {
	return expresionEntrada;
    }

    public SymProc getProcInfo(){
        return procInfo;
    }

    public void update() {}

    public String printTree() {
	return nombre;
    }

    public void generateCode(Writer fd, int nextReg, String si, String no) throws IOException {

        AssemblerInfo.saveRegLlamado(fd, nextReg);

        empilarParametros(fd, 0);

        fd.write("call proc"+nombre+"\n");

        desempilarParametros(fd, 0, si, no);

        AssemblerInfo.restoreRegLlamado(fd, nextReg);
         
    }

    public void desempilarParametros(Writer fd, int nextReg, String si, String no) throws IOException{

        Object [] expresiones = expresionEntrada.toArray();
        String reg = AssemblerInfo.getNombresRegAtPos(nextReg);
        String nreg = AssemblerInfo.getNombresRegAtPos(nextReg+1);

        Iterator itr = procInfo.getRef().iterator();

        for(int i = expresionEntrada.size() -1; i>=0; i--){

            boolean flag = ((Boolean) itr.next()).booleanValue();
            int tam = ((ASTExpresion) expresiones[i]).getState().getTam();

            if(flag){
                fd.write("pop "+nreg+"\n");
                fd.write("add "+nreg+", "+tam+"\n");
            }
            
            for(int k = 0; k < tam; k+=8){
                fd.write("pop "+reg+"\n");
                if(flag){
                    fd.write("mov ["+nreg+"], "+reg+"\n");
                    fd.write("sub "+nreg+", 8\n");
                }
            }

        }

        if(state instanceof Basico && ((Basico) state).getNBasico() == 3){
            fd.write("pop "+reg+"\n");
            fd.write("cmp "+reg+", 1\n");
            fd.write("je "+si+"\n");
            fd.write("jmp "+no+"\n");
        }

    }

    public void empilarParametros(Writer fd, int nextReg) throws IOException{

        String reg = AssemblerInfo.getNombresRegAtPos(nextReg); 
        Iterator ite = expresionEntrada.iterator();
        Iterator itt = procInfo.getIn().iterator();
        LinkedList ref = procInfo.getRef();        
        Iterator itr = ref.iterator();
        ASTExpresion argumento;

        while(ite.hasNext()){

           argumento = (ASTExpresion) ite.next();
           Tipo dest = procInfo.getTable().exist((String) itt.next()).getTipo();

           String si = AssemblerInfo.newLabel();
           String no = AssemblerInfo.newLabel();
           String end = AssemblerInfo.newLabel();

           argumento.generateCode(fd, nextReg, si, no);

           if(argumento.getState() instanceof Basico && ((Basico) argumento.getState()).getNBasico() == 3){
               fd.write(si + ":\n");
               fd.write("push 1\n");    
               fd.write("jmp " + end + "\n");		    
               fd.write(no + ":\n");
               fd.write("push 0\n");    
               fd.write(end + ":\n");
           }
           else if(argumento instanceof ASTIdentificador){

               if(argumento.getState() instanceof Basico)
                   fd.write("push qword ["+reg+"]\n");
               else
                   AssemblerInfo.generateIdenPushCastCode(fd, nextReg, dest , argumento.getState());
           }
           else if(argumento instanceof ASTLiteralArreglo)
               ((ASTLiteralArreglo) argumento).generatePushCastCode(fd,nextReg, dest, ((ASTLiteralArreglo) argumento).getArreglos());
           else if(argumento instanceof ASTLiteralUR)
               ((ASTLiteralUR) argumento).generatePushCastCode(fd, nextReg, dest);
           else if(!(argumento instanceof ASTInvocarExpresion))
               fd.write("push "+reg+"\n");

           if(((Boolean) itr.next()).booleanValue())
               fd.write("push "+reg+"\n");        

        }

    }

}
