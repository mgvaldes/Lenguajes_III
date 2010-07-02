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
        else
            refreshState(real);
    }

    public void refreshState(Tipo real){

        Iterator ita = asignaciones.iterator();
        ASTAsignacion a;

        state = real;

        if(real instanceof Union){

            a = (ASTAsignacion) ita.next();

            if(a.getExpr() instanceof ASTLiteralUR){
  
                int pos = ((Union) real).getCampos().indexOf(((ASTIdentificador) a.getIds().getFirst()).getValue());

                if(pos != -1){
                    Tipo ureal = (Tipo) ((Union) real).getTipos().get(pos);
                    ((ASTLiteralUR) a.getExpr()).refreshState(ureal);
                }
            }

        }
        else if(real instanceof Registro){

            Iterator itt = ((Registro) real).getTipos().iterator();

            while(ita.hasNext()){

                Tipo rreal = (Tipo) itt.next();

                a = (ASTAsignacion) ita.next();

                if(a.getExpr() instanceof ASTLiteralUR)
                    ((ASTLiteralUR) a.getExpr()).refreshState(rreal);

            }

        }

    }


    private Tipo inferType(){

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

    public void generatePushCastCode(Writer fd, int nextReg, Tipo dest) throws IOException{

        if(state instanceof Registro){

            Iterator itt = ((Registro) dest).getTipos().iterator();
            Iterator ita = asignaciones.iterator();

            while(ita.hasNext()){

                ASTAsignacion asig = (ASTAsignacion) ita.next();
                Tipo edest = (Tipo) itt.next();
                ASTExpresion expr = asig.getExpr();
                String reg = AssemblerInfo.getNombresRegAtPos(nextReg); 
                String nreg = AssemblerInfo.getNombresRegAtPos(nextReg+1); 

                if(expr.getState() instanceof Basico && ((Basico) expr.getState()).getNBasico() == 3){

                    String si = AssemblerInfo.newLabel();
                    String no = AssemblerInfo.newLabel();
                    String end = AssemblerInfo.newLabel();
 
                    expr.generateCode(fd, nextReg, si, no);
 
                    fd.write(si + ":\n");
                    fd.write("push 1\n");    
                    fd.write("jmp " + end + "\n");		    
                    fd.write(no + ":\n");
                    fd.write("push 0\n");    
                    fd.write(end + ":\n");

                }
                else if(expr instanceof ASTIdentificador){
                    expr.generateCode(fd, nextReg, "", "");

                    if(expr.getState() instanceof Basico){
                        ASTCast cast = AssemblerInfo.checkCast(edest,expr.getState());
                        if(cast != null){
                            fd.write("mov "+nreg+", ["+reg+"]\n");
                            cast.generateCode(fd, nextReg+1, "", "");
                            fd.write("push qword "+nreg+"\n");
                        }
                        else
                            fd.write("push qword ["+reg+"]\n");
                    }
                    //else
		    //InvocarUtilities.generateIdenPushCastCode(fd, nextReg, edest, expr.getState(), ((ASTIdentificador) expr).getTable().getParent() == null);
                }
                else if(expr instanceof ASTLiteralArreglo)
                    ((ASTLiteralArreglo) expr).generatePushCastCode(fd,nextReg, edest, ((ASTLiteralArreglo) expr).getArreglos());
                else if(expr instanceof ASTLiteralUR)
                    ((ASTLiteralUR) expr).generatePushCastCode(fd, nextReg, edest);
                else if(!(expr instanceof ASTInvocarExpresion)){
                    expr.generateCode(fd, nextReg, "", "");
                    ASTCast cast = AssemblerInfo.checkCast(edest,expr.getState());
                    if(cast != null)
                        cast.generateCode(fd, nextReg, "", "");
                    fd.write("push "+reg+"\n");
                }

            }
        }
        else if(state instanceof Union){

            ASTAsignacion asig = (ASTAsignacion) asignaciones.getFirst();
            int pos = ((Union) dest).getCampos().indexOf((((ASTIdentificador) asig.getIds().getFirst()).getValue()));
            Tipo edest = (Tipo) ((Union) dest).getTipos().get(pos);
            ASTExpresion expr = asig.getExpr();

            String reg = AssemblerInfo.getNombresRegAtPos(nextReg); 
            String nreg = AssemblerInfo.getNombresRegAtPos(nextReg+1); 

            fd.write("push "+pos+"\n");

            if(expr.getState() instanceof Basico && ((Basico) expr.getState()).getNBasico() == 3){

                String si = AssemblerInfo.newLabel();
                String no = AssemblerInfo.newLabel();
                String end = AssemblerInfo.newLabel();

                expr.generateCode(fd, nextReg, si, no);

                fd.write(si + ":\n");
                fd.write("push 1\n");    
                fd.write("jmp " + end + "\n");		    
                fd.write(no + ":\n");
                fd.write("push 0\n");    
                fd.write(end + ":\n");

            }
            else if(expr instanceof ASTIdentificador){
                expr.generateCode(fd, nextReg, "", "");

                if(expr.getState() instanceof Basico){
                    ASTCast cast = AssemblerInfo.checkCast(edest,expr.getState());
                    if(cast != null){
                        fd.write("mov "+nreg+", ["+reg+"]\n");
                        cast.generateCode(fd, nextReg+1, "", "");
                        fd.write("push qword ["+nreg+"]\n");
                    }
                    else
                        fd.write("push qword ["+reg+"]\n");
                }
                //else
		//InvocarUtilities.generateIdenPushCastCode(fd, nextReg, edest, expr.getState(), ((ASTIdentificador) expr).getTable().getParent() == null);
            }
            else if(expr instanceof ASTLiteralArreglo)
                ((ASTLiteralArreglo) expr).generatePushCastCode(fd,nextReg, edest, ((ASTLiteralArreglo) expr).getArreglos());
            else if(expr instanceof ASTLiteralUR)
                ((ASTLiteralUR) expr).generatePushCastCode(fd, nextReg, edest);
            else if(!(expr instanceof ASTInvocarExpresion)){
                expr.generateCode(fd, nextReg, "", "");
                ASTCast cast = AssemblerInfo.checkCast(edest,expr.getState());
                if(cast != null)
                    cast.generateCode(fd, nextReg, "", "");
                fd.write("push "+reg+"\n");
            }

        }

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
	    String reg2 = AssemblerInfo.getNombresRegAtPos(nextReg + 2);
	    Iterator asigs = asignaciones.iterator();
	    ASTAsignacion aux;
	    ASTIdentificador aux_id;
	    ASTExpresion aux_expr;
	    Tipo id_type;
	    Tipo expr_type;
	    Iterator campos_types;
	    LinkedList aux_type;
	    ASTCast cast;

	    String si;
	    String no;
	    String end;

	    AssemblerInfo.saveReg(fd, nextReg + 1);		    
	    AssemblerInfo.saveReg(fd, nextReg + 2);

	    if (type instanceof Union) {
		aux = (ASTAsignacion)asigs.next();
		aux_id = (ASTIdentificador)((LinkedList)aux.getIds()).getFirst();
	     	int pos = ((LinkedList)((Union)type).getCampos()).indexOf((String)aux_id.getValue());

		//Seteando discriminante
		fd.write("mov " + reg1 + ", " + pos + "\n");
	     	fd.write("mov [" + reg + "], " + reg1 + "\n");
	     	fd.write("sub " + reg + ", 8\n");

		aux_type = new LinkedList();
		aux_type.add(((LinkedList)((Union)type).getTipos()).get(pos));
		campos_types = aux_type.iterator();
	    }
	    else {
		campos_types = ((Registro)type).getTipos().iterator();
	    }

	    asigs = asignaciones.iterator();

	    while (asigs.hasNext()) {
		aux = (ASTAsignacion)asigs.next();
		aux_id = (ASTIdentificador)((LinkedList)aux.getIds()).getFirst();
		aux_expr = aux.getExpr();
		id_type = (Tipo)campos_types.next();
		si = AssemblerInfo.newLabel();
		no = AssemblerInfo.newLabel();
		end = AssemblerInfo.newLabel();

		aux_expr.generateCode(fd, nextReg + 1, si, no);
		    
		if (id_type instanceof Basico) {
		    if (aux_expr instanceof ASTIdentificador) {
			fd.write("mov " + reg1 + ", [" + reg1 + "]\n");

			cast = AssemblerInfo.checkCast(id_type, aux_expr.getState());
			if (cast != null) {
			    cast.generateCode(fd, nextReg + 1, "", "");
			}

			fd.write("mov [" + reg + "], " + reg1 + "\n");
		    }
		    else if (aux_expr instanceof ASTConst) {
			if (((Basico)id_type).getNBasico() == 3) {
			    fd.write(si + ":\n");
			    fd.write("mov " + reg1 + ", 1\n");    
			    fd.write("jmp " + end + "\n");		    
			    fd.write(no + ":\n");
			    fd.write("mov " + reg1 + ", 0\n");    
			    fd.write(end + ":\n");
			}
			else {
			    cast = AssemblerInfo.checkCast(id_type, aux_expr.getState());
			    if (cast != null) {
				cast.generateCode(fd, nextReg + 1, "", "");
			    }
			}
			fd.write("mov [" + reg + "], " + reg1 + "\n");
		    }
		    else if (aux_expr instanceof ASTBool) {
			fd.write(si + ":\n");
			fd.write("mov " + reg1 + ", 1\n");    
			fd.write("jmp " + end + "\n");		    
			fd.write(no + ":\n");
			fd.write("mov " + reg1 + ", 0\n");    
			fd.write(end + ":\n");
			fd.write("mov [" + reg + "], " + reg1 + "\n");
		    }
		}
		else if ((id_type instanceof Arreglo)) {
		    if (aux_expr instanceof ASTIdentificador) {			   
			int offs = 0;
			expr_type = ((SymVar)((ASTIdentificador)aux_expr).getTable().getSym(aux_expr.getValue())).getState();			
			Tipo base_id_type = ((Arreglo)id_type).getTipoBase();
			Tipo base_expr_type = ((Arreglo)expr_type).getTipoBase();

			cast = AssemblerInfo.checkCast(base_id_type, base_expr_type);
			AssemblerInfo.saveReg(fd, nextReg + 2);
			while (offs < expr_type.getTam()) {
			    fd.write("mov " + reg2 + ", [" + reg1 + " - " + offs + "]\n");
			    if (cast != null) {
				cast.generateCode(fd, nextReg + 2, "", "");
			    }
			    fd.write("mov [" + reg + " - " + offs + "], " + reg2 + "\n");
			    offs += 8;
			}
			AssemblerInfo.restoreReg(fd, nextReg + 2);
		    }
		    else if (aux_expr instanceof ASTLiteralArreglo) {
			((ASTLiteralArreglo)aux_expr).generateCode(fd, nextReg, id_type);
		    }
		}
		else if ((id_type instanceof Registro) || (id_type instanceof Union)) {
		    if (aux_expr instanceof ASTIdentificador) {			   
			System.out.println("-------------UR " + id_type);
			int offs = 0;
			expr_type = ((SymVar)((ASTIdentificador)aux_expr).getTable().getSym(aux_expr.getValue())).getState();			
			    
			AssemblerInfo.saveReg(fd, nextReg + 2);
			while (offs < expr_type.getTam()) {
			    fd.write("mov " + reg2 + ", [" + reg1 + " - " + offs + "]\n");
			    fd.write("mov [" + reg + " - " + offs + "], " + reg2 + "\n");
			    offs += 8;
			}
			AssemblerInfo.restoreReg(fd, nextReg + 2);
		    }
		    if (aux_expr instanceof ASTLiteralUR) {
			((ASTLiteralUR)aux_expr).generateCode(fd, nextReg, id_type);
		    }		
		}	      
		    
		fd.write("mov " + reg2 + ", " + id_type.getTam() + "\n");
		fd.write("sub " + reg + ", " + reg2 + "\n");
	    }

	    AssemblerInfo.restoreReg(fd, nextReg + 2);		    
	    AssemblerInfo.restoreReg(fd, nextReg + 1);
	}
	catch (IOException e) {
	    System.out.println("Error escribiendo en archivo de salida\n");
	}
    }
}
