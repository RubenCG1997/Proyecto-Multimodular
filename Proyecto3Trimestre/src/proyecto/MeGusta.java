package proyecto;

public class MeGusta {
	
	private int pkfkCuenta;
	private String pkfkNombreLista;
	private int pkfkCuentaLista;
	
	public MeGusta() {}

	public MeGusta(int pkfkCuenta, String pkfkNombreLista, int pkfkCuentaLista) {
		this.pkfkCuenta = pkfkCuenta;
		this.pkfkNombreLista = pkfkNombreLista;
		this.pkfkCuentaLista = pkfkCuentaLista;
	}

	public int getPkfkCuenta() {
		return pkfkCuenta;
	}

	public void setPkfkCuenta(int pkfkCuenta) {
		this.pkfkCuenta = pkfkCuenta;
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
		return "MeGusta [pkfkCuenta=" + pkfkCuenta + ", pkfkNombreLista=" + pkfkNombreLista + ", pkfkCuentaLista="
				+ pkfkCuentaLista + "]";
	}
	
	
	

}
