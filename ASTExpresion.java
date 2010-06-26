import java.io.*;

public abstract class ASTExpresion {

    //@ invariant value != null;
    protected String value;
    protected Tipo state;
    protected ASTExpresion left;
    protected ASTExpresion right;

    //@ requires v != null;
    public ASTExpresion(String v, ASTExpresion l, ASTExpresion r) {
	value = v;
	state = null;
	left = l;
	right = r;
    }

    public void setState(Tipo t) {
	state = t;
    }

    //@ requires v != null;
    public void setValue(String v) {
	value = v;
    }

    public void setLeft(ASTExpresion l) {
	left = l;
    }

    public void setRight(ASTExpresion r) {
	right = r;
    }

    public Tipo getState() {
	return state;
    }

    public String getValue() {
	return value;
    }

    public ASTExpresion getLeft() {
	return left;
    }

    public ASTExpresion getRight() {
	return right;
    }

    public boolean check() {
	return state != null;
    }
    
    public abstract void update();
    public abstract String printTree();	

    public void generateCode(Writer fd, int nextReg, String si, String no) throws IOException {};
}
