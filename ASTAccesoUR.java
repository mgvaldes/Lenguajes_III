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

}
