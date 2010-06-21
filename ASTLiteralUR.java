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

    public void finalCheck(Tipo real){

        if( real.asign(state) == null )
            state = null;

    }

    public Tipo inferType(){

       Iterator it = asignaciones.iterator();

       ASTAsignacion a;
       ASTExpresion e;
       String id;

       LinkedList tipos = new LinkedList();
       LinkedList campos = new LinkedList();

			
       while(it.hasNext()){

           a = (ASTAsignacion) it.next();
           e = a.getExpr();
           id = ((ASTIdentificador) a.getIds().getFirst()).getValue();

           tipos.add(e.getState());
           campos.add(id);

       }

       Registro result = new Registro(tipos, campos);

       if(tipos.size() == 1)
           result.setPUnion(true);

       return result;

           
    }

    public void updateState(){
        state = inferType();
    }

    public void update() {}

    public String printTree() {	
	String m = new String(value);
	return m;
    }

    public void generateCode(Writer fd, int nextReg, Tipo type) throws IOException {
	try {
	    String reg = AssemblerInfo.getNombresRegAtPos(nextReg); 
	    String reg1 = AssemblerInfo.getNombresRegAtPos(nextReg + 1);
	    Iterator asigs = asignaciones.iterator();
	    ASTAsignacion aux;
	    String si = AssemblerInfo.newLabel();
	    String no = AssemblerInfo.newLabel();
	    String end = AssemblerInfo.newLabel();
	    Tipo expr_state = new Basico(0); // Solo para inicializarlo
	    Tipo aux_type = new Basico(0); // Solo para inicializarol

	    while (asigs.hasNext()) {
		aux = (ASTAsignacion)asigs.next();
		fd.write("add " + reg + ", " + ((Registro)type).getOffset((String)((ASTIdentificador)((LinkedList)aux.getIds()).getFirst()).getValue()) + "\n");
		
		AssemblerInfo.saveReg(fd, nextReg + 1);		    
		if (!(aux.getExpr() instanceof ASTLiteralArreglo) && !(aux.getExpr() instanceof ASTLiteralUR)) {
		    if (aux.getExpr() instanceof ASTIdentificador) {
			expr_state = ((SymVar)((ASTIdentificador)aux.getExpr()).getTable().getSym(aux.getExpr().getValue())).getState();
		    }
		    else {
			expr_state = aux.getExpr().getState();
		    }
		    
		    if (expr_state instanceof Basico && ((Basico)expr_state).getNBasico() == 3) {
			aux.getExpr().generateCode(fd, nextReg + 1, si, no);
			fd.write(si + ":\n");
			fd.write("mov " + reg1 + ", 1\n");    
			fd.write("jmp " + end + "\n");		    
			fd.write(no + ":\n");
			fd.write("mov " + reg1 + ", 0\n");    
			fd.write(end + ":\n");
		    }
		    else if ((expr_state instanceof Arreglo) || (expr_state instanceof Registro)) {
			aux.getExpr().generateCode(fd, nextReg + 1, si, no);	
			if (((ASTIdentificador)aux.getExpr()).getAcceso().getHijo() != null) {
			    fd.write("mov " + reg1 + ", [" + reg1 + "]\n");
			}
		    }
		    else {
			aux.getExpr().generateCode(fd, nextReg + 1, si, no);
		    }
		    
		    fd.write("mov [" + reg + "], " + reg1 + "\n");
		    AssemblerInfo.restoreReg(fd, nextReg + 1);
		}
		else if (aux.getExpr() instanceof ASTLiteralArreglo) {
		    aux_type = ((SymVar)((ASTIdentificador)((LinkedList)aux.getIds()).getFirst()).getTable().getSym(((ASTIdentificador)((LinkedList)aux.getIds()).getFirst()).getValue())).getState();
		    ((ASTLiteralArreglo)aux.getExpr()).generateCode(fd, nextReg, aux_type);
		}
		else if (aux.getExpr() instanceof ASTLiteralUR) {
		    aux_type = ((SymVar)((ASTIdentificador)((LinkedList)aux.getIds()).getFirst()).getTable().getSym(((ASTIdentificador)((LinkedList)aux.getIds()).getFirst()).getValue())).getState();
		    ((ASTLiteralUR)aux.getExpr()).generateCode(fd, nextReg, aux_type);
		}
	    }
	}
	catch (IOException e) {
	    System.out.println("Error escribiendo en archivo de salida\n");
	}
    }
}
