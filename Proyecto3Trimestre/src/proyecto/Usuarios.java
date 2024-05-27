package proyecto;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Usuarios {
	
	private String dni;
	private String nombre;
	private String apellidos;
	private String email;
	private String contrasenna;
	private LocalDate fecha_de_nacimiento;
	private boolean rol;
	private boolean estado;
	
	//Constructores
	public Usuarios() {
		
	}
	
	public Usuarios(String dni, String nombre, String apellidos, String email, String contrasenna,
			String fecha_de_nacimiento, boolean rol, boolean estado) {
		
		DateTimeFormatter f = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		
		this.dni = dni;
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.email = email;
		this.contrasenna = contrasenna;
		this.fecha_de_nacimiento = LocalDate.parse(fecha_de_nacimiento,f);
		this.rol = rol;
		this.estado = estado;
	}
	
	//Metodos Getters y Setters

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getContrasenna() {
		return contrasenna;
	}

	public void setContrasenna(String contrasenna) {
		this.contrasenna = contrasenna;
	}

	public LocalDate getFecha_de_nacimiento() {
		return fecha_de_nacimiento;
	}

	public void setFecha_de_nacimiento(String fecha_de_nacimiento) {
		DateTimeFormatter f = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		this.fecha_de_nacimiento = LocalDate.parse(fecha_de_nacimiento,f);
	}

	public boolean isRol() {
		return rol;
	}

	public void setRol(boolean rol) {
		this.rol = rol;
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
		return "Usuarios [dni=" + dni + ", nombre=" + nombre + ", apellidos=" + apellidos + ", email=" + email
				+ ", contrasenna=" + contrasenna + ", fecha_de_nacimiento=" + f.format(fecha_de_nacimiento) + ", rol=" + rol
				+ ", estado=" + estado + "]";
	}
	
	
	
	
	
	
	
	
	
	
}
