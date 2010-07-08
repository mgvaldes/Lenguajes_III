import java.util.LinkedList;
import java.util.Iterator;
import java.io.*;

public class ASTInvocarExpresion extends ASTExpresion {

    private String nombre;
    private LinkedList expresionEntrada;
    private SymProc procInfo;

    public ASTInvocarExpresion(String n, LinkedList e, Tipo s, SymProc pInfo) {
	super("invocar", null, null);
	nombre = n;
	expresionEntrada = e;
	state = s;
	procInfo = pInfo;
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

        InvocarUtilities.empilarParametros(fd, 0, expresionEntrada, procInfo);

        fd.write("call proc"+nombre+"\n");

        desempilarParametros(fd, 0, si, no);

        AssemblerInfo.restoreRegLlamado(fd, nextReg);         
    }

    public void desempilarParametros(Writer fd, int nextReg, String si, String no) throws IOException {
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

                boolean global = ((ASTIdentificador) expr).getTable().getParent() == null;

                Tipo source = procInfo.getTable().exist((String) tsource[i]).getTipo();
                fd.write("pop "+reg+"\n");

                if(source instanceof Basico){
                    fd.write("pop "+nreg+"\n");
                    ASTCast cast = AssemblerInfo.checkCast(expr.getState(), source);
                    if(cast != null)
                        cast.generateCode(fd, nextReg+1, "", "");
                    fd.write("mov ["+reg+"], "+nreg+"\n");
                }
                else
		  InvocarUtilities.generateIdenPopCastCode(fd, nextReg, expr.getState(), source, global);
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
}
