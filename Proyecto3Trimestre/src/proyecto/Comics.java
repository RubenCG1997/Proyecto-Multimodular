package proyecto;

public class Comics extends Publicaciones {

	private boolean color;

	public Comics() {
		super();
	}

	public Comics(String isbn, String titulo, String fecha_de_lanzamiento, boolean estado, String fkAutorNombre,
			String fkAutorApellidos, String fkEditorial,boolean color) {
		
		super(isbn, titulo, fecha_de_lanzamiento, estado, fkAutorNombre, fkAutorApellidos, fkEditorial);
		
		this.color = color;
	}


	public boolean isColor() {
		return color;
	}

	public void setColor(boolean color) {
		this.color = color;
	}

	@Override
	public String toString() {
		return super.toString()+" Comic [color=" + color + "]";
	}
	
	
	
	
	
	
}
