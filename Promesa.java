import java.util.LinkedList;

public class Promesa {

    protected Tipo state;
    protected LinkedList argumentos;
    protected LinkedList referencias;
    protected boolean hecha;

    public Promesa(Tipo t, LinkedList l, LinkedList r, boolean h) {
      state = t;
      argumentos = l;
      referencias = r;
      hecha = h;
    }

    public void setState(Tipo t) {
	state = t;
    }

    public void setArgumentos(LinkedList l) {
      argumentos = l;
    }

    public void setReferencias(LinkedList r) {
      referencias = r;
    }

    public void setHecha(boolean h) {
      hecha = h;
    }

    public Tipo getState() {
	return state;
    }

    public LinkedList getArgumentos() {
	return argumentos;
    }

    public LinkedList getReferencias() {
	return referencias;
    }

    public boolean getHecha() {
	return hecha;
    }

}
