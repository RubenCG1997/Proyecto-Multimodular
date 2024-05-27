package proyecto;

public class Listas {

	private String nombre;
	private int pkfkCuenta;
	private boolean estado;
	
	public Listas() {}

	public Listas(String nombre, int pkfkCuenta, boolean estado) {
		this.nombre = nombre;
		this.pkfkCuenta = pkfkCuenta;
		this.estado = estado;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getPkfkCuenta() {
		return pkfkCuenta;
	}

	public void setPkfkCuenta(int pkfkCuenta) {
		this.pkfkCuenta = pkfkCuenta;
	}

	public boolean isEstado() {
		return estado;
	}

	public void setEstado(boolean estado) {
		this.estado = estado;
	}

	@Override
	public String toString() {
		return "Listas [nombre=" + nombre + ", pkfkCuenta=" + pkfkCuenta + ", estado=" + estado + "]";
	}
	
	
	
	
}
