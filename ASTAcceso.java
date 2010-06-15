import java.io.*;

public class ASTAcceso {

    protected ASTAcceso hijo;

    public ASTAcceso() {
	hijo = null;
    }

    public ASTAcceso(ASTAcceso h) {
	hijo = h;
    }

    public void setHijo(ASTAcceso h) {
	hijo = h;
    }

    public ASTAcceso getHijo() {
	return hijo;
    }

    public boolean isNull() {
  	return hijo == null;
    }

    public Tipo check(Tipo t) {
	if(hijo == null)
	    return t;
	else
	    return null;
    }

    public String printTree() {
	String m = new String("ACCESO(");
	if (hijo != null)
	    m = m.concat(hijo.printTree());
	
	m = m.concat(")");
	return m;
    }
    
    public void generateCode(Writer fd, int nextReg, Tipo type) throws IOException {};
}
