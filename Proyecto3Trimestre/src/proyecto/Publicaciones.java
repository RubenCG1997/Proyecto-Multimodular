package proyecto;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public abstract class Publicaciones {
	
	private String isbn;
	private String titulo;
	private LocalDate fecha_de_lanzamiento;
	private boolean estado;
	private String fkAutorNombre;
	private String fkAutorApellidos;
	private String fkEditorial;
	
	
	
	public Publicaciones() {}

	public Publicaciones(String isbn, String titulo, String fecha_de_lanzamiento, boolean estado,
			String fkAutorNombre, String fkAutorApellidos, String fkEditorial) {
		
		DateTimeFormatter f = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		
		this.isbn = isbn;
		this.titulo = titulo;
		this.fecha_de_lanzamiento = LocalDate.parse(fecha_de_lanzamiento,f);
		this.estado = estado;
		this.fkAutorNombre = fkAutorNombre;
		this.fkAutorApellidos = fkAutorApellidos;
		this.fkEditorial = fkEditorial;
	}

	public Publicaciones(String isbn) {
		this.isbn= isbn;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public LocalDate getFecha_de_lanzamiento() {
		return fecha_de_lanzamiento;
	}

	public void setFecha_de_lanzamiento(String fecha_de_lanzamiento) {
		DateTimeFormatter f = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		this.fecha_de_lanzamiento =LocalDate.parse(fecha_de_lanzamiento,f);
	}

	public boolean isEstado() {
		return estado;
	}

	public void setEstado(boolean estado) {
		this.estado = estado;
	}

	public String getFkAutorNombre() {
		return fkAutorNombre;
	}

	public void setFkAutorNombre(String fkAutorNombre) {
		this.fkAutorNombre = fkAutorNombre;
	}

	public String getFkAutorApellidos() {
		return fkAutorApellidos;
	}

	public void setFkAutorApellidos(String fkAutorApellidos) {
		this.fkAutorApellidos = fkAutorApellidos;
	}

	public String getFkEditorial() {
		return fkEditorial;
	}

	public void setFkEditorial(String fkEditorial) {
		this.fkEditorial = fkEditorial;
	}

	@Override
	public String toString() {
		DateTimeFormatter f = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		return "Publicaciones [isbn=" + isbn + ", titulo=" + titulo + ", fecha_de_lanzamiento=" + f.format(fecha_de_lanzamiento)
				+ ", estado=" + estado + ", fkAutorNombre=" + fkAutorNombre + ", fkAutorApellidos=" + fkAutorApellidos
				+ ", fkEditorial=" + fkEditorial + "]";
	}
	
	
	

}
