public class Resultado {

    private Tipo tipo;
    private SymTable table;

    public Resultado(Tipo t,SymTable ta) {
        tipo = t;
        table= ta;
    }

    public void setTipo(Tipo t) {
	tipo = t;
    }

    public void setTable(SymTable t) {
	table = t;
    }

    public Tipo getTipo() {
	return tipo;
    }

    public SymTable getTable() {
	return table;
    }
}
