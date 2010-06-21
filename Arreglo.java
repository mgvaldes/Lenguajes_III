import java.io.*;
import java.util.LinkedList;

public class Arreglo extends Tipo {

    //@ invariant sub != null;
    private int size;
    private Tipo sub;
    private boolean done;

    //@ requires s != null;
    public Arreglo(int si, Tipo s) {
	super();
	size = si;
	sub = s;
        done = false;
    }

    public int calcSubTam() {
	int tam = 1;
	Tipo aux = sub;
	
	while (aux instanceof Arreglo) {
	    tam *= ((Arreglo)aux).getSize();
	    aux = ((Arreglo)aux).getSub();
	}

	tam *= aux.getTam();
	return tam;
    }

    public Tipo getTipoBase() {
	Tipo aux = sub;
	
	while (aux instanceof Arreglo)
	    aux = ((Arreglo)aux).getSub();
	
	return aux;
    }

    public void setSize(int s) {
  	size = s;
    }

    //@ requires s != null;
    public void setSub(Tipo s) {
	sub = s;
    }

    public int getSize() {
	return size;
    }

    public Tipo getSub() {
	return sub;
    }

    public int getTam() {
        if(!done){

            if(sub != null)
                tam = size*sub.getTam();
            else
                tam = 0;

            done = true;
            return tam;
        }
        else
            return tam;
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

	if(!(t instanceof Arreglo))
	    return null;
	
	Arreglo a2 = (Arreglo) t;

        if(size != a2.getSize())
            return null;
	
	if(sub.asign(a2.getSub()) == null)
	    return null;
	
	return this;
	
    }

    public boolean equals(Tipo t) {
	if(!(t instanceof Arreglo))
	    return false;
	
	Arreglo a2 = (Arreglo) t;
	
	return (size == a2.getSize()) && sub.equals(a2.getSub());
    }
    
    public String toString() {
	String m = new String(sub.toString());
	m = m.concat("[]");	
	return m;
    }

    public void generateCode(Writer fd, int nextReg, String region, int offset, int ani) {};
}
