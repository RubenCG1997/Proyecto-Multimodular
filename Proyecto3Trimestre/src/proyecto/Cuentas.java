package proyecto;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Cuentas {

	private String username;
	private LocalDate fecha_de_creacion;
	private String fkUsuario;
	private int fkSuscripcion;
	
	public Cuentas() {}

	public Cuentas(String username, String fkUsuario, int fkSuscripcion) {
		
		this.username = username;
		this.fecha_de_creacion=LocalDate.now();
		this.fkUsuario = fkUsuario;
		this.fkSuscripcion = fkSuscripcion;
	}
	
	public Cuentas(String username, String fecha_de_creacion, String fkUsuario, int fkSuscripcion) {
		
		DateTimeFormatter f = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		
		this.username = username;
		this.fecha_de_creacion = LocalDate.parse(fecha_de_creacion,f);
		this.fkUsuario = fkUsuario;
		this.fkSuscripcion = fkSuscripcion;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public LocalDate getFecha_de_creacion() {
		return fecha_de_creacion;
	}

	public void setFecha_de_creacion(String fecha_de_creacion) {
		DateTimeFormatter f = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		this.fecha_de_creacion = LocalDate.parse(fecha_de_creacion,f);
	}
	

	public String getFkUsuario() {
		return fkUsuario;
	}

	public void setFkUsuario(String fkUsuario) {
		this.fkUsuario = fkUsuario;
	}

	public int getFkSuscripcion() {
		return fkSuscripcion;
	}

	public void setFkSuscripcion(int fkSuscripcion) {
		this.fkSuscripcion = fkSuscripcion;
	}

	@Override
	public String toString() {
		DateTimeFormatter f = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		return "Cuentas [username=" + username + ", fecha_de_creacion=" + f.format(fecha_de_creacion) + ","
				+ ", fkUsuario=" + fkUsuario + ", fkSuscripcion=" + fkSuscripcion + "]";
	}

}
