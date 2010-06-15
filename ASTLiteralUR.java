import java.util.LinkedList;
import java.util.Iterator;
import java.io.*;

public class ASTLiteralUR extends ASTExpresion {

    //@ invariant asignaciones != null;
    private LinkedList asignaciones;

    //@ requires a != null;
    public ASTLiteralUR(LinkedList a) {
	super("literal", null, null);
	asignaciones = a;
    }

    public void update() {
	String id;
	ASTAsignacion a;
	ASTExpresion e;
	String campo;
	Tipo t;
	boolean flag = false;

	if(state != null) {
	    if(state instanceof Registro) {
		if(((Registro) state).getCampos().size() == asignaciones.size()) {
		    Iterator ica = ((Registro) state).getCampos().iterator();
		    Iterator iti = ((Registro) state).getTipos().iterator();
		    Iterator iid = asignaciones.iterator();

		    while(ica.hasNext()) {
			a = (ASTAsignacion) iid.next();			
			id = ((ASTIdentificador) a.getIds().getFirst()).getValue();
			e = a.getExpr();
			campo = (String) ica.next();
			t = (Tipo) iti.next();

			if(!id.equals(campo)) {
			    flag = true; 
			    break;
			}
			else {
			    if(t.asign(e.getState()) == null)
				state = null;
			}
		    }

		    if(flag)
			state = null;
		}
		else
		    state = null;
	    }
	    else if(state instanceof Union) {
		if(asignaciones.size() == 1) {
		    a = (ASTAsignacion) asignaciones.getFirst();
		    id = ((ASTIdentificador) a.getIds().getFirst()).getValue();
		    e = a.getExpr();

		    Iterator ica = ((Union) state).getCampos().iterator();
		    Iterator iti = ((Union) state).getTipos().iterator();

		    while(ica.hasNext()) {
			campo = (String) ica.next();
			t = (Tipo) iti.next();

			if(id.equals(campo)) {   
			    flag = true;
			    if(t.asign(e.getState()) == null)
				state = null;
			    break;
			}
		    }

		    if(!flag)
			state = null;
            
		}
		else
		    state = null;
	    }
	    else
		state = null;
	}
    }
  
    public String printTree() {	
	String m = new String(value);
	return m;
    }

    public void generateCode(Writer fd, int nextReg) throws IOException {

    }
}
