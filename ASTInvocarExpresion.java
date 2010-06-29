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
        Object [] ref =  procInfo.getRef().toArray();
        Object [] tsource =  procInfo.getIn().toArray();

        String reg = AssemblerInfo.getNombresRegAtPos(nextReg);
        String nreg = AssemblerInfo.getNombresRegAtPos(nextReg+1);

        for(int i = expresionEntrada.size() -1; i>=0; i--){

            boolean flag = ((Boolean) ref[i]).booleanValue();
            ASTExpresion expr = (ASTExpresion) expresiones[i];
            int tam = expr.getState().getTam();

            if(flag){

                Tipo source = procInfo.getTable().exist((String) tsource[i]).getTipo();
                fd.write("pop "+reg+"\n");
                fd.write("add "+reg+", "+tam+"-8\n");

                if(source instanceof Basico){
                    fd.write("pop "+nreg+"\n");
                    ASTCast cast = AssemblerInfo.checkCast(expr.getState(), source);
                    if(cast != null)
                        cast.generateCode(fd, nextReg+1, "", "");
                    fd.write("mov ["+reg+"], "+nreg+"\n");
                }
                else
                    InvocarUtilities.generateIdenPopCastCode(fd, nextReg, expr.getState(), source, ((ASTIdentificador) expr).getTable().getParent() == null);
            }
            else
                for(int k = 0; k < tam; k+=8)
                    fd.write("pop "+reg+"\n");

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
        String nreg = AssemblerInfo.getNombresRegAtPos(nextReg+1); 
        Iterator ite = expresionEntrada.iterator();
        Iterator itt = procInfo.getIn().iterator();
        LinkedList ref = procInfo.getRef();        
        Iterator itr = ref.iterator();
        ASTExpresion argumento;

        while(ite.hasNext()){

           argumento = (ASTExpresion) ite.next();
           Tipo dest = procInfo.getTable().exist((String) itt.next()).getTipo();

           if(argumento.getState() instanceof Basico && ((Basico) argumento.getState()).getNBasico() == 3){


               String si = AssemblerInfo.newLabel();
               String no = AssemblerInfo.newLabel();
               String end = AssemblerInfo.newLabel();

               argumento.generateCode(fd, nextReg, si, no);

               fd.write(si + ":\n");
               fd.write("push 1\n");    
               fd.write("jmp " + end + "\n");		    
               fd.write(no + ":\n");
               fd.write("push 0\n");    
               fd.write(end + ":\n");
           }
           else if(argumento instanceof ASTIdentificador){

               argumento.generateCode(fd, nextReg, "", "");

               if(argumento.getState() instanceof Basico){
                    ASTCast cast = AssemblerInfo.checkCast(dest,argumento.getState());
                    if(cast != null){
                        fd.write("mov "+nreg+", ["+reg+"]\n");
                        cast.generateCode(fd, nextReg+1, "", "");
                        fd.write("push qword "+nreg+"\n");
                    }
                    else
                        fd.write("push qword ["+reg+"]\n");
               }
               else
                   InvocarUtilities.generateIdenPushCastCode(fd, nextReg, dest , argumento.getState(), ((ASTIdentificador) argumento).getTable().getParent() == null);

               if(((Boolean) itr.next()).booleanValue()){
                   fd.write("sub "+reg+", "+argumento.getState().getTam()+"-8\n");
                   fd.write("push "+reg+"\n");
               }

           }
           else if(argumento instanceof ASTLiteralArreglo)
               ((ASTLiteralArreglo) argumento).generatePushCastCode(fd,nextReg, dest, ((ASTLiteralArreglo) argumento).getArreglos());
           else if(argumento instanceof ASTLiteralUR)
               ((ASTLiteralUR) argumento).generatePushCastCode(fd, nextReg, dest);
           else if(!(argumento instanceof ASTInvocarExpresion)){
               ASTCast cast = AssemblerInfo.checkCast(dest,argumento.getState());
               if(cast != null)
                   cast.generateCode(fd, nextReg, "", "");
               fd.write("push "+reg+"\n");
           }

        }

    }

}
