package proyecto;

public class Resennas {
	
	private int pkfkCuenta;
	private String pkfkPublicacion;
	private String opinion;
	private int puntuacion;
	private boolean estado;
	
	public Resennas() {}
	
	public Resennas(int pkfkCuenta, String pkfkPublicacion, String opinion, int puntuacion, boolean estado) {
		this.pkfkCuenta = pkfkCuenta;
		this.pkfkPublicacion = pkfkPublicacion;
		this.opinion = opinion;
		this.puntuacion = puntuacion;
		this.estado = estado;
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

	public String getOpinion() {
		return opinion;
	}

	public void setOpinion(String opinion) {
		this.opinion = opinion;
	}

	public int getPuntuacion() {
		return puntuacion;
	}

	public void setPuntuacion(int puntuacion) {
		this.puntuacion = puntuacion;
	}

	public boolean isEstado() {
		return estado;
	}

	public void setEstado(boolean estado) {
		this.estado = estado;
	}

	@Override
	public String toString() {
		return "Resennas [pkfkCuenta=" + pkfkCuenta + ", pkfkPublicacion=" + pkfkPublicacion + ", opinion=" + opinion
				+ ", puntuacion=" + puntuacion + ", estado=" + estado + "]";
	}
	
	
	
	

}
