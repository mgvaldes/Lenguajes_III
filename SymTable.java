import java.util.Hashtable;

public class SymTable {
	
    //@ invariant hash != null;
    private Hashtable hash;
    private SymTable parent;
	
    public SymTable(SymTable p) {
	hash = new Hashtable();
	parent = p;
    }

    public SymTable getParent() {
	return parent;
    }

    //@ requires elem != null;
    public boolean insert(Sym elem, int profundidad) {
	if(profundidad >= 0) {
		if(!existProfundidad(elem.getName(), profundidad)) {
		    hash.put(elem.getName(), elem);
		    return true;
		}
		else return false;
	}
	else {
	    if(!hash.containsKey(elem.getName())) {
		hash.put(elem.getName(), elem);
		return true;
	    }
	    else return false;
	}	
    }

    public boolean existProfundidad(String name, int profundidad) {
	Sym s = (Sym) hash.get(name);
	
	if(profundidad >= 0) {
	    if(s != null)
		return true;
	    else {
		if(parent != null)
		    return parent.existProfundidad(name, profundidad-1);
		else
		    return false;
	    }
	}
	else 
	    return false;
    }
    
    public Resultado exist(String name) {
	Sym s = (Sym) hash.get(name);

	if (s != null)
	    return new Resultado(s.getState(), this);
	else {
	    if (parent != null)
		return parent.exist(name);
	    else
		return new Resultado(null, null);
	}	
    }
    
    public Sym getSym(String name) {
	Sym s = (Sym) hash.get(name);

	if (s != null)
	    return s;
	else {
	    if (parent != null)
		return parent.getSym(name);
	    else
		return null;
	}
    }
    
}
