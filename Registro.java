import java.util.LinkedList;
import java.util.Iterator;
import java.io.*;

public class Registro extends Tipo {

    //@ invariant tipos != null;
    //@ invariant campos != null;
    private LinkedList tipos;
    private LinkedList campos;
    private boolean pUnion;

    //@ requires t != null & c != null;
    public Registro(LinkedList t, LinkedList c) {
        super();
        tipos = t;
        campos = c;

        Iterator it = tipos.iterator();
        tam = 0;

        while(it.hasNext())
	    tam += ((Tipo)it.next()).getTam();

        pUnion = false;
    }

    public void setPUnion(boolean b){
        pUnion = b;
    }


    public boolean getPUnion(){
        return pUnion;
    }

    public int getOffset(String campo) {
	Iterator itc = campos.iterator();
	Iterator itt = tipos.iterator();
	int offset = 0;

	while (itc.hasNext()) {
	    
	    if (((String)itc.next()).compareTo(campo) == 0) {
		break;
	    }
	    else {
		offset += ((Tipo)itt.next()).getTam();
	    }
	}

	return offset;
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

	if(!(t instanceof Registro))
	    return null;
	
	Registro r2 = (Registro) t;
	
	if(r2.getCampos().size() != campos.size())
	    return null;

	Iterator ica1 = campos.iterator();
	Iterator iti1 = tipos.iterator();
	
	Iterator ica2 = r2.getCampos().iterator();
	Iterator iti2 = r2.getTipos().iterator();

	String s1,s2;
	
	while(ica1.hasNext()) {
	    s1 = (String)ica1.next();
	    s2 = (String)ica2.next();

	    if(s1.compareTo(s2)!=0)
		return null;

	    if(((Tipo)iti1.next()).asign(((Tipo)iti2.next())) == null)
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
	if(!(t instanceof Registro))
	    return false;
	
	Registro r2 = (Registro) t;
	
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

	Iterator ica = campos.iterator();
	Iterator iti = tipos.iterator();

        String result = "struct {";
	
	while(ica.hasNext()) {

            result += (Tipo) iti.next()+" ";
            result += (String) ica.next()+"; ";
      
	}

        result += "}";

        return result;

    }	

}
