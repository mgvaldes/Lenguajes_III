import java.io.*;
import java.util.LinkedList;

public class ASTIdentificador extends ASTExpresion {
    
    private SymTable table;
    private ASTAcceso acceso;

    //@ requires v != null;    
    public ASTIdentificador(String v, SymTable t, ASTAcceso a, Tipo s) {
	super(v, null, null);
	table = t;
	acceso = a;
	state = s;
    }

    public void setTable(SymTable t) {
  	table = t;
    }

    public void setAcceso(ASTAcceso a) {
  	acceso = a;
    }

    public SymTable getTable() {
  	return table;
    }
    
    public ASTAcceso getAcceso() {
  	return acceso;
    }

    public void update() {
	if(acceso != null) {
	    state = acceso.check(state);
	}
    }

    public Tipo getTipoAcceso(Tipo aux_type, ASTAcceso acc) {
	Tipo t = aux_type;
	ASTAcceso a = acc;
	
	while (a != null) {
	    if (a.getHijo() != null) {
		if (a instanceof ASTAccesoArreglo) {
		    t = ((Arreglo)t).getSub();
		    //t = ((Arreglo)t).getTipoBase();
		    //return t;
		}
		else {
		    if (t instanceof Registro) {		    
			t = (Tipo)((LinkedList)((Registro)t).getTipos()).get(((LinkedList)((Registro)t).getCampos()).indexOf(((ASTAccesoUR)a).getCampo()));
		    }
		    else if (t instanceof Union) {
			t = (Tipo)((LinkedList)((Union)t).getTipos()).get(((LinkedList)((Union)t).getCampos()).indexOf(((ASTAccesoUR)a).getCampo()));
		    }
		
		}
	    }
	    a = a.getHijo();
	}
	
	return t;
    }
  
    public String printTree() {
	String m = new String("IDENT( " + value + " )");
	return m;
    }

    public void generateCode(Writer fd, int nextReg, String si, String no)  throws IOException {

	    String reg = AssemblerInfo.getNombresRegAtPos(nextReg);
	    Tipo aux_state = ((SymVar)getTable().getSym(getValue())).getState();

	    if(getTable().getParent() == null) {
		fd.write("mov " + reg + ", static \n");
		fd.write("add " + reg + ((SymVar)getTable().getSym(getValue())).getOffset() + "\n");
	    }
	    else {
		fd.write("mov " + reg + ", " + AssemblerInfo.getFp() + "\n");	
		fd.write("sub " + reg + ", " + ((SymVar)getTable().getSym(getValue())).getOffset() + "\n");
	    }
	    
	    if ((aux_state instanceof Arreglo) || (aux_state instanceof Registro) || (aux_state instanceof Union)) {
		if (acceso != null) {
		    if (acceso.getHijo() != null) {
			acceso.generateCode(fd, nextReg, aux_state);			
		    }
		}
	    }
    }
}
