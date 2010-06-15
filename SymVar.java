
public class SymVar extends Sym {

    private boolean isConst;
    private int offset;
	
    public SymVar(String n, Tipo s, boolean i, int o) {
	super(n,s);
	isConst = i;
        offset = o;
    }

    public void setIsConst(boolean i) {
	isConst = i;
    }

    public void setOffset(int o) {
	offset = o;
    }

    public boolean getIsConst() {
	return isConst;
    }

    public int getOffset() {
	return offset;
    }
}
