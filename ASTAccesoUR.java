import java.io.*;
import java.util.LinkedList;

public class ASTAccesoUR extends ASTAcceso {

    protected String campo;

    public ASTAccesoUR() {
	hijo = null;
	campo = null;
    }

    public ASTAccesoUR(ASTAcceso h, String c) {
	hijo = h;
	campo = c;
    }

    public boolean isNull() {
	return false;
    }

    public Tipo check(Tipo t) {
	int pos;

	if(t instanceof Registro) {
	    Registro r = (Registro) t;
	    pos = r.getCampos().indexOf(campo);
	    if(pos != -1)
		return hijo.check((Tipo)r.getTipos().get(pos));
	    else 
		return null;
	}
	else if(t instanceof Union) {
	    Union u = (Union) t;
	    pos = u.getCampos().indexOf(campo);
	    if(pos != -1) {
		return hijo.check((Tipo)u.getTipos().get(pos));
	    }
	    else 
		return null;
	}
	else
	    return null;
    }
  
    public String printTree() {
	String m = new String(".");

	if (campo != null) {
	    m = m.concat(campo);
	}

	if (hijo != null)
	    m = m.concat(hijo.printTree());

	return m;
    }
    
    public void generateCode(Writer fd, int nextReg, Tipo type) throws IOException {
	String reg = AssemblerInfo.getNombresRegAtPos(nextReg); 
	
	if (type instanceof Union) {
	    String bien = AssemblerInfo.newLabel();
	    String reg1 = AssemblerInfo.getNombresRegAtPos(nextReg + 1); 
	    int pos = ((LinkedList)((Union)type).getCampos()).indexOf(campo);
	    
	    AssemblerInfo.saveReg(fd, nextReg + 1);	
	    fd.write("mov " + reg1 + ", " + pos + "\n");		
	    fd.write("cmp [" + reg + "], " + reg1 + "\n");
	    AssemblerInfo.restoreReg(fd, nextReg + 1);
	    fd.write("je " + bien + "\n");
	    fd.write("call print_error_discriminante\n");
	    fd.write("call print_nl\n");
	    fd.write("mov eax, 1\n");
	    fd.write("int 80h\n");	
	    
	    fd.write(bien + ":\n");
	    fd.write("sub " + reg + ", 8\n");		    
	    if (this.getHijo() != null) {
		int pos_campo = ((LinkedList)((Union)type).getCampos()).indexOf(campo);
		Tipo tipo_campo = (Tipo)((LinkedList)((Union)type).getTipos()).get(pos_campo);
		this.getHijo().generateCode(fd, nextReg, tipo_campo);
	    }
	}
	else {
	    fd.write("sub " + reg + ", " + ((Registro)type).getOffset(campo) + "\n");
	    if (this.getHijo() != null) {
		int pos_campo = ((LinkedList)((Registro)type).getCampos()).indexOf(campo);
		Tipo tipo_campo = (Tipo)((LinkedList)((Registro)type).getTipos()).get(pos_campo);
		this.getHijo().generateCode(fd, nextReg, tipo_campo);
	    }
	}
    }
}
