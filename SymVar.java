
public class SymVar extends Sym {

    private boolean isConst;
    private boolean isIn;
    private int offset;
	
    public SymVar(String n, Tipo s, boolean i, int o) {
	super(n,s);
	isConst = i;
        offset = o;
        isIn = false;
    }

    public void setIsConst(boolean i) {
	isConst = i;
    }

    public void setIsIn(boolean i) {
	isIn = i;
    }

    public void setOffset(int o) {
	offset = o;
    }

    public boolean getIsConst() {
	return isConst;
    }

    public boolean getIsIn() {
	return isIn;
    }

    public int getOffset() {
	return offset;
    }
}
