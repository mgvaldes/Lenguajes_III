import java.util.LinkedList;
import java.util.Iterator;
import java.io.*;

public class ASTInvocar extends ASTInstruccion {

    //@ invariant expresionEntrada != null;
    private String nombre;
    private LinkedList expresionEntrada;
    private Tipo state;

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

    public ASTInvocarExpresion toExpresion() {
	ASTInvocarExpresion r = new ASTInvocarExpresion(nombre, expresionEntrada, state);
	return r;
    }

    //@ requires proc != null;  
    public boolean check(SymProc proc) {
	LinkedList nombres = proc.getIn();
	SymTable tabla = proc.getTable();
	Tipo res;

	if(expresionEntrada == null)
	    return nombres.size() == 0;

	if(nombres.size() != expresionEntrada.size())
	    return false;

	Iterator it1 = nombres.iterator();
	Iterator it2 = expresionEntrada.iterator();
     
	while(it1.hasNext()) {
	    ASTExpresion e = (ASTExpresion)it2.next();
	    res = e.getState().asign(tabla.exist((String)it1.next()).getTipo());
	    if(res == null)
		return false;
	}

	return true;	
    }

    public boolean check(LinkedList argumentos) {
	Tipo res;

	if(expresionEntrada == null)
	    return argumentos.size() == 0;

	if(argumentos.size() != expresionEntrada.size())
	    return false;

	Iterator it1 = argumentos.iterator();
	Iterator it2 = expresionEntrada.iterator();

	while(it1.hasNext()) {
	    ASTExpresion e = (ASTExpresion)it2.next();
	    res = e.getState().asign( ((ASTIdentificador)it1.next()).getState() );
	    if(res == null)
		return false;
	}

	return true;	
    }

    public String printTree() {
	return nombre;
    }

    public void generateCode(Writer fd, int nextReg) throws IOException {

    }

}
