import java.util.LinkedList;
import java.io.*;

public class ASTInvocarExpresion extends ASTExpresion {

    private String nombre;
    private LinkedList expresionEntrada;

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

    public String getNombre() {
	return nombre;
    }

    public LinkedList getExpresionEntrada() {
	return expresionEntrada;
    }

    public void update() {}

    public String printTree() {
	return nombre;
    }

    public void generateCode(Writer fd, int nextReg) throws IOException {

    }

}
