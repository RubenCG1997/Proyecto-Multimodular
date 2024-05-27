package proyecto;

public class Editoriales {
	
	private String cif;
	private String nombre;
	private String direccion;
	private String telefono;
	private String cp;
	private boolean estado;
	
	public Editoriales() {}
	
	public Editoriales(String cif, String nombre, String direccion, String telefono, String cp, boolean estado) {
		super();
		this.cif = cif;
		this.nombre = nombre;
		this.direccion = direccion;
		this.telefono = telefono;
		this.cp = cp;
		this.estado = estado;
	}

	public String getCif() {
		return cif;
	}

	public void setCif(String cif) {
		this.cif = cif;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getCp() {
		return cp;
	}

	public void setCp(String cp) {
		this.cp = cp;
	}

	public boolean isEstado() {
		return estado;
	}

	public void setEstado(boolean estado) {
		this.estado = estado;
	}

	@Override
	public String toString() {
		return "Editoriales [cif=" + cif + ", nombre=" + nombre + ", direccion=" + direccion + ", telefono=" + telefono
				+ ", cp=" + cp + ", estado=" + estado + "]";
	}
	
	
	
	

}
