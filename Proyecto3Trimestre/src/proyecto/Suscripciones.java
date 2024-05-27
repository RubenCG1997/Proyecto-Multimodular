package proyecto;

public class Suscripciones {
	
	enum TIPO {
		INDIVIDUAL, DUO, FAMILIAR;
	}
	private TIPO tipo;
	enum CUOTA {
		MENSUAL, ANUAL;
	}
	private CUOTA cuota;
	private double precio;

	public Suscripciones() {
	}

	public Suscripciones(TIPO tipo, CUOTA cuota) {
		this.tipo = tipo;
		this.cuota = cuota;
		this.precio = CalculoPrecio();

	}

	public TIPO getTipo() {
		return tipo;
	}

	public void setTipo(TIPO tipo) {
		this.tipo = tipo;
	}

	public CUOTA getCuota() {
		return cuota;
	}

	public void setCuota(CUOTA cuota) {
		this.cuota = cuota;
	}

	public double getPrecio() {
		return CalculoPrecio();
	}
	

	@Override
	public String toString() {
		return "Suscripciones [tipo=" + tipo + ", cuota=" + cuota + ", precio=" + precio + "]";
	}

	private double CalculoPrecio() {

		if (this.tipo == TIPO.INDIVIDUAL && this.cuota == CUOTA.MENSUAL) {
			this.precio = 8.99;
		} else if (this.tipo == TIPO.INDIVIDUAL && this.cuota == CUOTA.ANUAL) {
			this.precio = 89.99;
		} else if (this.tipo == TIPO.DUO && this.cuota == CUOTA.MENSUAL) {
			this.precio = 14.99;
		} else if (this.tipo == TIPO.DUO && this.cuota == CUOTA.ANUAL) {
			this.precio = 169.99;
		} else if (this.tipo == TIPO.FAMILIAR && this.cuota == CUOTA.MENSUAL) {
			this.precio = 17.99;
		} else {
			this.precio = 199.99;
		}

		return this.precio;
	}

}
