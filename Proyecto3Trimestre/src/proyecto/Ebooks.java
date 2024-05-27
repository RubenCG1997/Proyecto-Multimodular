package proyecto;

public class Ebooks extends Publicaciones {

	private String formato;

	public Ebooks() {
		super();
		}

	public Ebooks(String isbn, String titulo, String fecha_de_lanzamiento, boolean estado, String fkAutorNombre,
			String fkAutorApellidos, String fkEditorial, String formato) {
		
		super(isbn, titulo, fecha_de_lanzamiento, estado, fkAutorNombre, fkAutorApellidos, fkEditorial);
		
		this.formato = formato;
	}

	public String getFormato() {
		return formato;
	}

	public void setFormato(String formato) {
		this.formato = formato;
	}

	@Override
	public String toString() {
		return super.toString() + " Ebooks [formato=" + formato + "]";
	}
	
	
	
}
