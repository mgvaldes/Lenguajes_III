import java.io.*;

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
	if(acceso != null && !acceso.isNull()) {
	    state = acceso.check(state);
	}
    }
  
    public String printTree() {
	String m = new String("IDENT( " + value + " )");
	return m;
    }

    public void generateCode(Writer fd, int nextReg, String si, String no)  throws IOException {
	try {	    
	    String reg = AssemblerInfo.getNombresRegAtPos(nextReg);
	    Tipo aux_state = ((SymVar)getTable().getSym(getValue())).getState();

	    if (aux_state instanceof Basico) {
                if(getTable().getParent() == null)
                    fd.write("mov " + reg + ", [static + " + ((SymVar)getTable().getSym(getValue())).getOffset() + "]\n");
                else
                    fd.write("mov " + reg + ", [" + AssemblerInfo.getFp() + " - " + ((SymVar)getTable().getSym(getValue())).getOffset() + "]\n");

		if (((Basico)state).getNBasico() == 3) {
		    fd.write("cmp " + reg + ", 1\n");
		    fd.write("je " + si + "\n");
		    fd.write("jmp " + no + "\n");
		}
	    }
	    else if (aux_state instanceof Arreglo) {
		if(getTable().getParent() == null) {
                    fd.write("mov " + reg + ", static \n");
		    fd.write("add " + reg + ((SymVar)getTable().getSym(getValue())).getOffset() + "\n");
		}
                else {
		    fd.write("mov " + reg + ", " + AssemblerInfo.getFp() + "\n");	
                    fd.write("sub " + reg + ", " + ((SymVar)getTable().getSym(getValue())).getOffset() + "\n");
		}

		if (acceso != null) {
		    if (acceso.getHijo() != null) {
			acceso.generateCode(fd, nextReg, aux_state);			
		    }
		}
	    }
	}
	catch (IOException e) {
	    System.out.println("Error escribiendo en archivo de salida\n");
	}
    }
}
