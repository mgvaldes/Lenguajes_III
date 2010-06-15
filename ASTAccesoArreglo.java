import java.io.*;

public class ASTAccesoArreglo extends ASTAcceso {

    private ASTExpresion expr;

    public ASTAccesoArreglo() {
	hijo = null;
	expr = null;
    }

    public ASTAccesoArreglo(ASTAcceso h, ASTExpresion e) {
	hijo = h;
	expr = e;
    }

    public boolean isNull() {
  	return false;
    }

    public Tipo check(Tipo t) {
	if(t instanceof Arreglo) {
	    Arreglo a = (Arreglo) t;
	    return hijo.check(a.getSub());
	} 	
	else
	    return null;
    }

    public String printTree() {
	String m = new String("[]");
	if (hijo != null)
	    m = m.concat(hijo.printTree());

	return m;
    }

    public void generateCode(Writer fd, int nextReg, Tipo type) throws IOException {
	try {	   
	    String reg = AssemblerInfo.getNombresRegAtPos(nextReg); 
	    String reg1 = AssemblerInfo.getNombresRegAtPos(nextReg + 1);
	    String reg2 = AssemblerInfo.getNombresRegAtPos(nextReg + 2);

	    AssemblerInfo.saveReg(fd, nextReg + 1);
	    fd.write("mov " + reg1 + ", " + ((Arreglo)type).calcSubTam() + "\n");
	    AssemblerInfo.saveReg(fd, nextReg + 2);
	    expr.generateCode(fd, nextReg + 2, "", "");
	    fd.write("imul " + reg1 + ", " + reg2 + "\n");
	    AssemblerInfo.restoreReg(fd, nextReg + 2);
	    fd.write("sub " + reg + ", " + reg1 + "\n");
	    AssemblerInfo.restoreReg(fd, nextReg + 1);	    
	    
	    if (this.getHijo() != null)
		this.getHijo().generateCode(fd, nextReg, ((Arreglo)type).getSub());
	}
	catch (IOException e) {
	    System.out.println("Error escribiendo en archivo de salida\n");
	}
    }
}
