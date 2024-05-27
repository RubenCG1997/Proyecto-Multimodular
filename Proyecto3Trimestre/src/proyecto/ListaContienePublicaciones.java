package proyecto;

public class ListaContienePublicaciones {

	private String pkfkPublicacion;
	private String pkfkNombreLista;
	private int pkfkCuentaLista;
	
	public ListaContienePublicaciones() {}

	public ListaContienePublicaciones(String pkfkPublicacion, String pkfkNombreLista, int pkfkCuentaLista) {
		this.pkfkPublicacion = pkfkPublicacion;
		this.pkfkNombreLista = pkfkNombreLista;
		this.pkfkCuentaLista = pkfkCuentaLista;
	}

	public String getPkfkPublicacion() {
		return pkfkPublicacion;
	}

	public void setPkfkPublicacion(String pkfkPublicacion) {
		this.pkfkPublicacion = pkfkPublicacion;
	}

	public String getPkfkNombreLista() {
		return pkfkNombreLista;
	}

	public void setPkfkNombreLista(String pkfkNombreLista) {
		this.pkfkNombreLista = pkfkNombreLista;
	}

	public int getPkfkCuentaLista() {
		return pkfkCuentaLista;
	}

	public void setPkfkCuentaLista(int pkfkCuentaLista) {
		this.pkfkCuentaLista = pkfkCuentaLista;
	}

	@Override
	public String toString() {
		return "ListaContienePublicaciones [pkfkPublicacion=" + pkfkPublicacion + ", pkfkNombreLista=" + pkfkNombreLista
				+ ", pkfkCuentaLista=" + pkfkCuentaLista + "]";
	}
	
	
	
	
	
}
