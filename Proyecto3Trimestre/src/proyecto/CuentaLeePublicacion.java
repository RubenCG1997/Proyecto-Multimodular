package proyecto;

public class CuentaLeePublicacion {
	
	private int pkfkCuenta;
	private String pkfkPublicacion;
	private int porcentaje;
	
	
	public CuentaLeePublicacion() {}


	public CuentaLeePublicacion(int pkfkCuenta, String pkfkPublicacion, int porcentaje) {
		super();
		this.pkfkCuenta = pkfkCuenta;
		this.pkfkPublicacion = pkfkPublicacion;
		this.porcentaje = porcentaje;
	}

	

	public int getPkfkCuenta() {
		return pkfkCuenta;
	}


	public void setPkfkCuenta(int pkfkCuenta) {
		this.pkfkCuenta = pkfkCuenta;
	}


	public String getPkfkPublicacion() {
		return pkfkPublicacion;
	}


	public void setPkfkPublicacion(String pkfkPublicacion) {
		this.pkfkPublicacion = pkfkPublicacion;
	}


	public int getPorcentaje() {
		return porcentaje;
	}


	public void setPorcentaje(int porcentaje) {
		this.porcentaje = porcentaje;
	}


	public String toString() {
		return "CuentaLeePublicacion [pkfkCuenta=" + pkfkCuenta + ", pkfkPublicacion=" + pkfkPublicacion
				+ ", porcentaje=" + porcentaje + "]";
	}
	
	

}
