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
	    String reg = AssemblerInfo.getNombresRegAtPos(nextReg); 
	    String reg1 = AssemblerInfo.getNombresRegAtPos(nextReg + 1);
	    String reg2 = AssemblerInfo.getNombresRegAtPos(nextReg + 2);
	    String reg3 = AssemblerInfo.getNombresRegAtPos(nextReg + 3);
	    String error = AssemblerInfo.newLabel();
	    String bien = AssemblerInfo.newLabel();

	    AssemblerInfo.saveSpecificReg(fd, "rdi");
	    AssemblerInfo.saveReg(fd, nextReg + 3);

	    AssemblerInfo.saveReg(fd, nextReg + 1);
	    fd.write("mov " + reg1 + ", " + ((Arreglo)type).calcSubTam() + "\n");
	    AssemblerInfo.saveReg(fd, nextReg + 2);
	    expr.generateCode(fd, nextReg + 2, "", "");

            if(expr instanceof ASTIdentificador)
                fd.write("mov "+reg2+", ["+reg2+"]\n");

	    // Chequeo dinamico de indice fuera de rango
	    fd.write("cmp " + reg2 + ", 0\n");
	    fd.write("mov " + reg3 + ", " + reg2 + "\n");
	    fd.write("jl " + error + "\n");
	    
	    fd.write("mov " + reg3 + ", " + ((Arreglo)type).getSize() + "\n");
	    fd.write("cmp " + reg2 + ", " + reg3 + "\n");
	    fd.write("mov " + reg3 + ", " + reg2 + "\n");
	    fd.write("jge " + error + "\n");

	    fd.write("imul " + reg1 + ", " + reg2 + "\n");
	    AssemblerInfo.restoreReg(fd, nextReg + 2);
	    fd.write("sub " + reg + ", " + reg1 + "\n");
	    AssemblerInfo.restoreReg(fd, nextReg + 1);	    
	    
	    if (this.getHijo() != null)
		this.getHijo().generateCode(fd, nextReg, ((Arreglo)type).getSub());
	    fd.write("jmp " + bien + "\n");	    
	    
	    fd.write(error + ":\n");
	    fd.write("call print_error_indice\n");
	    fd.write("mov rdi, " + reg3 + "\n");
	    fd.write("call print_int\n");
	    fd.write("call print_nl\n");
	    fd.write("mov eax, 1\n");
	    fd.write("int 80h\n");

	    fd.write(bien + ":\n");
	    AssemblerInfo.restoreSpecificReg(fd, "rdi");
	    AssemblerInfo.restoreReg(fd, nextReg + 3);	    	    
    }
}
