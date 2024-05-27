package proyecto;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Autores {
	
	private String nombre;
	private String apellidos;
	private LocalDate fecha_de_nacimiento;
	private String biografia;
	private boolean estado;
	
	public Autores() {}

	public Autores(String nombre, String apellidos, String fecha_de_nacimiento, String biografia, boolean estado) {
		
		DateTimeFormatter f = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.fecha_de_nacimiento =LocalDate.parse(fecha_de_nacimiento,f) ;
		this.biografia = biografia;
		this.estado = estado;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public LocalDate getFecha_de_nacimiento() {
		return fecha_de_nacimiento;
	}

	public void setFecha_de_nacimiento(String fecha_de_nacimiento) {
		DateTimeFormatter f = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		this.fecha_de_nacimiento = LocalDate.parse(fecha_de_nacimiento,f);
	}

	public String getBiografia() {
		return biografia;
	}

	public void setBiografia(String biografia) {
		this.biografia = biografia;
	}

	public boolean isEstado() {
		return estado;
	}

	public void setEstado(boolean estado) {
		this.estado = estado;
	}

	@Override
	public String toString() {
		DateTimeFormatter f = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		return "Autores [nombre=" + nombre + ", apellidos=" + apellidos + ", fecha_de_nacimiento=" + f.format(fecha_de_nacimiento)
				+ ", biografia=" + biografia + ", estado=" + estado + "]";
	}
	
	
	
	
	

}
