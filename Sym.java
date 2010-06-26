public class Sym {

    protected String name;
    protected Tipo state;

    public Sym(String n, Tipo s) {
	name = n;
	state = s;
    }

    public void setName(String n) {
	name = n;
    }

    public void setState(Tipo s) {
	state = s;
    }

    public String getName() {
	return name;
    }

    public Tipo getState() {
	return state;
    }
}
