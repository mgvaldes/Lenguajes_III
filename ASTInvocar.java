import java.util.LinkedList;
import java.util.Iterator;
import java.io.*;

public class ASTInvocar extends ASTInstruccion {

    //@ invariant expresionEntrada != null;
    private String nombre;
    private LinkedList expresionEntrada;
    private Tipo state;
    private SymProc procInfo;

    //@ requires e != null;
    public ASTInvocar(String n, LinkedList e, Tipo s) {
	super("invocar");
	nombre = n;
	expresionEntrada = e;
	state = s;
    }

    public void setNombre(String n) {
	nombre = n;
    }

    //@ requires e != null;
    public void  setExpresionEntrada(LinkedList e) {
	expresionEntrada = e;
    }

    public String getNombre() {
	return nombre;
    }

    public LinkedList getExpresionEntrada() {
	return expresionEntrada;
    }

    public void setSymProc(SymProc p){
        procInfo = p;
    }

    public ASTInvocarExpresion toExpresion() {
	ASTInvocarExpresion r = new ASTInvocarExpresion(nombre, expresionEntrada, state);
        r.setSymProc(procInfo);
	return r;
    }

    public SymProc getProcInfo(){
        return procInfo;
    }

    //@ requires proc != null;  
    public boolean check(SymProc proc) {
	LinkedList nombres = proc.getIn();
	SymTable tabla = proc.getTable();
        LinkedList ref = proc.getRef();
        procInfo = proc;

	if(expresionEntrada == null)
	    return nombres.size() == 0;

	if(nombres.size() != expresionEntrada.size())
	    return false;

	Iterator it1 = nombres.iterator();
	Iterator it2 = expresionEntrada.iterator();
	Iterator it3 = ref.iterator();
     
	while(it1.hasNext()) {

            Tipo tin = tabla.exist((String)it1.next()).getTipo();
	    ASTExpresion e = (ASTExpresion)it2.next();

            if(e instanceof ASTLiteralUR)
                ((ASTLiteralUR) e).finalCheck(tin);

            else if(e instanceof ASTLiteralArreglo)
                ((ASTLiteralArreglo) e).finalCheck(tin);

            if(((Boolean) it3.next()).booleanValue()){

                if(!(e instanceof ASTIdentificador))
                    return false;

                if(e.getState().asign(tin) == null)
                    return false;

            }

	    if(tin.asign(e.getState()) == null)
		return false;
	}

	return true;	
    }

    public boolean check(Promesa pro) {

        LinkedList argumentos = pro.getArgumentos();
        LinkedList ref = pro.getReferencias();

	Tipo res;

	if(expresionEntrada == null)
	    return argumentos.size() == 0;

	if(argumentos.size() != expresionEntrada.size())
	    return false;

	Iterator it1 = argumentos.iterator();
	Iterator it2 = expresionEntrada.iterator();
	Iterator it3 = ref.iterator();

	while(it1.hasNext()) {

            Tipo tin = ((ASTIdentificador)it1.next()).getState();
	    ASTExpresion e = (ASTExpresion)it2.next();

            if(e instanceof ASTLiteralUR)
                ((ASTLiteralUR) e).finalCheck(tin);
            else if(e instanceof ASTLiteralArreglo)
                ((ASTLiteralArreglo) e).finalCheck(tin);

            if(((Boolean) it3.next()).booleanValue()){

                if(!(e instanceof ASTIdentificador))
                    return false;

                if(e.getState().asign(tin) == null)
                    return false;

            }

	    if(tin.asign(e.getState()) == null)
		return false;
	}

	return true;	
    }

    public String printTree() {
	return nombre;
    }

    public void generateCode(Writer fd, int nextReg, String breakLabel, String returnLabel) throws IOException {

        AssemblerInfo.saveRegLlamado(fd, nextReg);

        InvocarUtilities.empilarParametros(fd, 0, expresionEntrada, procInfo);

        fd.write("call proc"+nombre+"\n");

        desempilarParametros(fd, 0);

        AssemblerInfo.restoreRegLlamado(fd, nextReg); 
         
    }

    public void desempilarParametros(Writer fd, int nextReg) throws IOException{

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
		  InvocarUtilities.generateIdenPopCastCode(fd, nextReg, expr.getState(), source,  global);
            }
            else
                for(int k = 0; k < tam; k+=8)
                    fd.write("pop "+reg+"\n");

        }

        for(int k = 0; k < state.getTam(); k+=8)
            fd.write("pop "+reg+"\n");

    }

}
