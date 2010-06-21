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

    public void finalCheck(Tipo real){


	boolean flag = false;
        Registro reg = (Registro) state;

        if(real instanceof Union){

            if(!reg.getPUnion())
                state = null;
            else{
                 if(!checkUnion((Union) real, reg))
                     state = null;
                 else
                     state = real;
            }

       }
       else if(real instanceof Registro){
           if(reg.getCampos().size() == ((Registro) real).getCampos().size()) {

               Iterator ica1 = ((Registro) real).getCampos().iterator();
               Iterator iti1 = ((Registro) real).getTipos().iterator();

               Iterator ica2 = reg.getCampos().iterator();
               Iterator iti2 = reg.getTipos().iterator();	

               String s1,s2;
               Tipo t1, t2;
	
               while(ica1.hasNext()) {
                   s1 = (String)ica1.next();
                   s2 = (String)ica2.next();
      
                    if(s1.compareTo(s2)!=0){
                        flag = true;
                        break;
                     }

                    t1 = (Tipo) iti1.next();      
                    t2 = (Tipo) iti2.next();

                    if(t1 instanceof Union && t2 instanceof Registro){

                        if(!checkUnion((Union) t1, (Registro) t2)){
                            flag = true;
                            break;
                         }

                    }
                    else if( !t1.equals(t2) ){
                        flag = true;
                        break;
                    }
               }

               if(flag)
                   state = null;
               else
                   state = real;

	   }

		    
       }
       else
           state = null;

    } 

    private boolean checkUnion(Union u, Registro r){

        Tipo t = (Tipo) r.getTipos().getFirst();
        String id = (String) r.getCampos().getFirst();

        int k = u.getCampos().indexOf(id);

        if(k == -1 || ((Tipo) u.getTipos().get(k)).asign(t) == null)
            return false;
        else
            return true;

    }

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

	    if (type instanceof Registro) {
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
		    
			if (aux.getExpr() instanceof ASTIdentificador) {
			    aux.getExpr().generateCode(fd, nextReg + 1, si, no);	
			    if ((((ASTIdentificador)aux.getExpr()).getAcceso().getHijo() != null) || (expr_state instanceof Basico)) {
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
	    else {
		aux = (ASTAsignacion)asigs.next();
		int pos = ((LinkedList)((Union)type).getCampos()).indexOf((String)((ASTIdentificador)((LinkedList)aux.getIds()).getFirst()).getValue());

		AssemblerInfo.saveReg(fd, nextReg + 1);	
		fd.write("mov " + reg1 + ", " + pos + "\n");
		fd.write("mov [" + reg + "], " + reg1 + "\n");
		fd.write("add " + reg + ", 8\n");

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
		    
		    if (aux.getExpr() instanceof ASTIdentificador) {
			aux.getExpr().generateCode(fd, nextReg + 1, si, no);	
			if ((((ASTIdentificador)aux.getExpr()).getAcceso().getHijo() != null) || (expr_state instanceof Basico)) {
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
