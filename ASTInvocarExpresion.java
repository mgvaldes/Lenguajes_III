import java.util.LinkedList;
import java.io.*;

public class ASTInvocarExpresion extends ASTExpresion {

    private String nombre;
    private LinkedList expresionEntrada;
    private LinkedList ref;

    public ASTInvocarExpresion(String n, LinkedList e, Tipo s) {
	super("invocar", null, null);
	nombre = n;
	expresionEntrada = e;
	state = s;
        ref = null;
    }

    public void setNombre(String n) {
  	nombre = n;
    }

    public void setExpresionEntrada(LinkedList e) {
	expresionEntrada = e;
    }


    public void setRef(LinkedList r){
        ref = r;
    }

    public String getNombre() {
	return nombre;
    }

    public LinkedList getExpresionEntrada() {
	return expresionEntrada;
    }

    public LinkedList getRef(){
        return ref;
    }

    public void update() {}

    public String printTree() {
	return nombre;
    }

    public void generateCode(Writer fd, int nextReg) throws IOException {

    }

}
