import java.util.Iterator;
import java.util.LinkedList;
import java.io.*;

public class Union extends Tipo {

    //@ invariant tipos != null;
    //@ invariant campos != null;
    private LinkedList tipos;
    private LinkedList campos;
	
    //@ requires t != null & c != null;	
    public Union(LinkedList t, LinkedList c) {
	super();
	tipos = t;
	campos = c;

        Iterator it = tipos.iterator();
        tam = 0;

        boolean first = true;
        int max = 0;
        int tamElem = 0;

        while(it.hasNext()){

            tamElem = ((Tipo)it.next()).getTam();
            if(first){
 	        max = tamElem;
                first = false;
            }
            else{
                if(tamElem > max){
                    max = tamElem;
                }
           }

       }
       tam = max +8;
    }

    //@ requires c != null;
    public void setCampos(LinkedList c) {
	campos = c;
    }

    //@ requires t != null;
    public void setTipos(LinkedList t) {
	tipos = t;
    }
	
    //@ ensures \result != null;
    public LinkedList getCampos() {
	return campos;
    }
	
    //@ ensures \result != null;
    public LinkedList getTipos() {
	return tipos;
    }

    public Tipo aritmetica() {
	return null;
    }
	
    public Tipo aritmetica(Tipo t) {
	return null;
    }

    public Tipo mod(Tipo t) {
	return null;
    }
	
    public Tipo compare() {
	return null;
    }
	
    public Tipo compare(Tipo t) {
	return null;
    }

    public Tipo oa(Tipo t) {
	return null;
    }

    public Tipo eq(Tipo t) {
	return null;
    }

    public Tipo asign(Tipo t) {
	if(!(t instanceof Union))
	    return null;

	Union u2 = (Union) t;
		
	if(u2.getCampos().size() != campos.size())
	    return null;
		
	Iterator ica1 = campos.iterator();
	Iterator iti1 = tipos.iterator();
		
	Iterator ica2 = u2.getCampos().iterator();
	Iterator iti2 = u2.getTipos().iterator();

	String s1,s2;
		
	while(ica1.hasNext()) {
	    s1 = (String)ica1.next();
	    s2 = (String)ica2.next();
	    
	    if(s1.compareTo(s2)!=0)
		return null;
	    
	    if(!((Tipo)iti1.next()).equals(((Tipo)iti2.next())))
		return null;
	}
	
	return this;	
    }

    public void generateCode(Writer fd, int nextReg, String region, int offset, int ani){

        try{
            fd.write("mov "+AssemblerInfo.getNombresRegAtPos(nextReg)+", "+tam+"\n");
        }
    	catch (Exception e) {
    	    System.out.println("Error escribiendo en archivo de salida\n");
    	}

    }

    public boolean equals(Tipo t) {
	if(!(t instanceof Union))
	    return false;
	
	Union r2 = (Union) t;
	
	if(r2.getCampos().size() != campos.size())
	    return false;

	Iterator ica1 = campos.iterator();
	Iterator iti1 = tipos.iterator();
	
	Iterator ica2 = r2.getCampos().iterator();
	Iterator iti2 = r2.getTipos().iterator();

	String s1,s2;
	
	while(ica1.hasNext()) {
	    s1 = (String)ica1.next();
	    s2 = (String)ica2.next();
      
	    if(s1.compareTo(s2)!=0)
		return false;
      
	    if(!((Tipo)iti1.next()).equals(((Tipo)iti2.next())))
		return false;
	}

	return true;
    }

    public String toString() {
	return "union";
    }		
}
