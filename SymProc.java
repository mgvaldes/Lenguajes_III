import java.util.LinkedList;
import java.util.Iterator;

public class SymProc extends Sym {

    //@ invariant in != null;
    //@ invariant ref != null;
    //@ invariant bloque != null;    
    private LinkedList in;
    private LinkedList ref;
    private ASTBloque bloque;
    private int tamlocal;
  
    //@ requires i != null;
    //@ requires r != null;
    //@ requires b != null;    
    public SymProc(String n, Tipo s, LinkedList i, LinkedList r, ASTBloque b)     {
	super(n,s);
	in = i;
	ref = r;
	bloque = b;
	tamlocal = 0;
    }
  
    //@ requires i != null;
    public void setIn(LinkedList i) {
	in = i;
    }
  
    //@ requires b != null;    
    public void setBloque(ASTBloque b) {
	bloque = b;
    }

    public void setTamlocal(int t) {
  	tamlocal = t;
    }
  
    //@ ensures \result != null;    
    public SymTable getTable() {
	return bloque.getTable();
    }

    //@ ensures \result != null;
    public LinkedList getIn() {
	return in;
    }
  
    //@ ensures \result != null;
    public ASTBloque getBloque() {
	return bloque;
    }  

    public int getTamlocal() {
  	return tamlocal;
    }

    public boolean isRef(String i) {
	if(ref.contains(i))
	    return true;
	else
	    return false;
    }  

   public void calcOffsets(int tam){

       Iterator iti = in.iterator();
       Iterator itr = ref.iterator();

       ASTIdentificador argumento;

       while(iti.hasNext()){
           argumento = (ASTIdentificador) iti.next();
           ((SymVar) bloque.getTable().getSym(argumento.getValue())).setOffset(tam);
           tam += -argumento.getTable().exist(argumento.getValue()).getTipo().getTam();

          if(((Boolean) itr.next()).booleanValue())
             tam += -8;

        }

    }
}
