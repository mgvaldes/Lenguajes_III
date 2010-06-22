import java.io.*;

public class Basico extends Tipo {

    //@ invariant tcoercion != null;
    //@ invariant nBasico >= 0 & nBasico <= 5;
    private int nBasico;
    static private int[][] tcoercion =  {{1,1,-1,-1},{2,2,-1,-1},{-1,-1,3,-1},{4,-1,-1,4}};
  
    public Basico(int n) {
	super();
	nBasico = n;
        if(n != 0)
            tam = 8;
        else
            tam = 0;
    }

    public void setNBasico(int n) {
	nBasico = n;
    }

    public int getNBasico() {
	return nBasico;
    }

    public Tipo aritmetica() {
	if(nBasico == 1 || nBasico == 2)
	    return this;
	else
	    return null;
    }

    public Tipo aritmetica(Tipo t) {
	if(!(t instanceof Basico))
	    return null;
	
	Basico b2 = (Basico) t;

	if(nBasico != 1 && nBasico != 2)
	    return null;

	if(b2.getNBasico() != 1 && b2.getNBasico() != 2)
	    return null;

	if(nBasico == 1 && b2.getNBasico() == 1)
	    return new Basico(1);
	else
	    return new Basico(2);
    }

    public Tipo mod(Tipo t) {
	if(!(t instanceof Basico))
	    return null;
	
	Basico b2 = (Basico) t;

	if(nBasico != 1 && b2.getNBasico() != 1 )
	    return null;

        return new Basico(1);
    }

    public Tipo compare() {
	if(nBasico != 3)
	    return null;
	else 
	    return new Basico(3);
    }

    public Tipo compare(Tipo t) {
	if(!(t instanceof Basico))
	    return null;
	
	Basico b2 = (Basico) t;

	if(nBasico == 3 || nBasico == 0)
	    return null;

	if(b2.getNBasico() == 3 || b2.getNBasico() == 0)
	    return null;
	
	return new Basico(3);

    }

    public Tipo oa(Tipo t) {
	if(!(t instanceof Basico))
	    return null;
	
	Basico b2 = (Basico) t;

	if(nBasico != 3)
	    return null;
	
	if(b2.getNBasico() != 3)
	    return null;
	
	return new Basico(3);

    }

    public Tipo eq(Tipo t) {
	if(!(t instanceof Basico))
	    return null;
	
	Basico b2 = (Basico) t;

	if(nBasico == 0 || b2.getNBasico() == 0)
	    return null;
	
	if(nBasico == 1 && b2.getNBasico() == 4)
	    return new Basico(3);
	
	if(tcoercion[nBasico-1][b2.getNBasico()-1] == -1)
	    return null;

	return new Basico(3);

    }
  
    public Tipo asign(Tipo t) {

	if(!(t instanceof Basico))
	    return null;
	
	Basico b2 = (Basico) t;

	if(nBasico == 0 || b2.getNBasico() == 0)
	    return null;
	
	if(tcoercion[nBasico-1][b2.getNBasico()-1] == -1)
	    return null;

	return new Basico(tcoercion[nBasico-1][b2.getNBasico()-1]);
    }

    public void generateCode(Writer fd, int nextReg, String region, int offset, int ani) {
        try {
            fd.write("mov " + AssemblerInfo.getNombresRegAtPos(nextReg) + ", " + tam + "\n");
        }
    	catch (Exception e) {
    	    System.out.println("Error escribiendo en archivo de salida\n");
    	}
    }

    public boolean equals(Tipo t) {
	if(!(t instanceof Basico))
	    return false;
	
	Basico b2 = (Basico) t;
	
	return nBasico == b2.getNBasico();
    }

    public String toString() {
	switch(nBasico){
	case 0:
	    return "void";
	case 1:
	    return "int";
	case 2:
	    return "float";
	case 3:
	    return "bool";
	case 4:
	    return "char";
	default:
	    return "desconocido";
	}
    }
}
