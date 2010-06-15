import java.io.*;

public abstract class Tipo {
    
    protected int tam;

    public Tipo() {}
    
    public void setTam(int t) {
	tam = t;
    }
    
    public int getTam() {
	return tam;
    }
    
    public abstract Tipo aritmetica();
    public abstract Tipo aritmetica(Tipo t);
    public abstract Tipo mod(Tipo t);
    public abstract Tipo compare();
    public abstract Tipo compare(Tipo t);
    public abstract Tipo oa(Tipo t);
    public abstract Tipo eq(Tipo t);
    public abstract Tipo asign(Tipo t);
    public abstract void generateCode(Writer fd, int nextReg, String region, int offset, int ani);
    public abstract boolean equals(Tipo t);
    public abstract String toString();
}
