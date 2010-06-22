import java.util.LinkedList;
import java.util.Iterator;
import java.io.*;

public class ASTInvocar extends ASTInstruccion {

    //@ invariant expresionEntrada != null;
    private String nombre;
    private LinkedList expresionEntrada;
    private Tipo state;
    private LinkedList ref;

    //@ requires e != null;
    public ASTInvocar(String n, LinkedList e, Tipo s) {
	super("invocar");
	nombre = n;
	expresionEntrada = e;
	state = s;
        ref = null;
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

    public void setRef(LinkedList r){
        ref = r;
    }

    public ASTInvocarExpresion toExpresion() {
	ASTInvocarExpresion r = new ASTInvocarExpresion(nombre, expresionEntrada, state);
	return r;
    }

    public LinkedList getRef(){
        return ref;
    }

    //@ requires proc != null;  
    public boolean check(SymProc proc) {
	LinkedList nombres = proc.getIn();
	SymTable tabla = proc.getTable();
        ref = proc.getRef();
	Tipo res;

	if(expresionEntrada == null)
	    return nombres.size() == 0;

	if(nombres.size() != expresionEntrada.size())
	    return false;

	Iterator it1 = nombres.iterator();
	Iterator it2 = expresionEntrada.iterator();
	Iterator it3 = ref.iterator();
     
	while(it1.hasNext()) {

	    ASTExpresion e = (ASTExpresion)it2.next();

            if(((Boolean) it3.next()).booleanValue() && !(e instanceof ASTIdentificador))
                return false;

	    res = e.getState().asign(tabla.exist((String)it1.next()).getTipo());
	    if(res == null)
		return false;
	}

	return true;	
    }

    public boolean check(Promesa pro) {

        LinkedList argumentos = pro.getArgumentos();
        ref = pro.getReferencias();

	Tipo res;

	if(expresionEntrada == null)
	    return argumentos.size() == 0;

	if(argumentos.size() != expresionEntrada.size())
	    return false;

	Iterator it1 = argumentos.iterator();
	Iterator it2 = expresionEntrada.iterator();
	Iterator it3 = ref.iterator();

	while(it1.hasNext()) {
	    ASTExpresion e = (ASTExpresion)it2.next();

            if(((Boolean) it3.next()).booleanValue() && !(e instanceof ASTIdentificador))
                return false;

	    res = e.getState().asign( ((ASTIdentificador)it1.next()).getState() );

	    if(res == null)
		return false;
	}

	return true;	
    }

    public String printTree() {
	return nombre;
    }

    public void generateCode(Writer fd, int nextReg, String breakLabel, String returnLabel) throws IOException {

        AssemblerInfo.saveRegLlamado(fd, nextReg);

        empilarParametros(fd, nextReg);

        AssemblerInfo.restoreRegLlamado(fd, nextReg);
         
    }

    public void empilarParametros(Writer fd, int nextReg) throws IOException{

        String reg = AssemblerInfo.getNombresRegAtPos(nextReg); 
        Iterator ite = expresionEntrada.iterator();
        Iterator itr = ref.iterator();
        ASTExpresion argumento;

        while(ite.hasNext()){

            argumento = (ASTExpresion) ite.next();

            String si = AssemblerInfo.newLabel();
            String no = AssemblerInfo.newLabel();
            String end = AssemblerInfo.newLabel();

           argumento.generateCode(fd, nextReg, si, no);

           if(argumento.getState() instanceof Basico && ((Basico) argumento.getState()).getNBasico() == 3){
               fd.write(si + ":\n");
               fd.write("push 1\n");    
               fd.write("jmp " + end + "\n");		    
               fd.write(no + ":\n");
               fd.write("psuh 0\n");    
               fd.write(end + ":\n");
           }
           else if(argumento instanceof ASTIdentificador){
           }
           else if(argumento instanceof ASTLiteralArreglo){
           }
           else if(argumento instanceof ASTLiteralUR){
           }
           else{
               fd.write("push "+reg+"\n");
           }

           if(((Boolean) itr.next()).booleanValue())
               fd.write("push "+reg+"\n");        

        }

    }

}
