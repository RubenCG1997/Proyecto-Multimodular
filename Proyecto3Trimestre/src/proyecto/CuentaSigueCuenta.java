package proyecto;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class CuentaSigueCuenta {
	
	private int idSeguido;
	private int idSeguidor;
	private LocalDate fecha;
	
	public CuentaSigueCuenta() {}

	public CuentaSigueCuenta(int idSeguido, int idSeguidor) {
	
		this.idSeguido = idSeguido;
		this.idSeguidor = idSeguidor;
		this.fecha = LocalDate.now();
	}

	public int getIdSeguido() {
		return idSeguido;
	}

	public void setIdSeguido(int idSeguido) {
		this.idSeguido = idSeguido;
	}

	public int getIdSeguidor() {
		return idSeguidor;
	}

	public void setIdSeguidor(int idSeguidor) {
		this.idSeguidor = idSeguidor;
	}

	public LocalDate getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		DateTimeFormatter f = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		this.fecha = LocalDate.parse(fecha,f);
	}

	@Override
	public String toString() {
		DateTimeFormatter f = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		return "CuentaSigueCuenta [idSeguido=" + idSeguido + ", idSeguidor=" + idSeguidor + ", fecha=" + f.format(fecha) + "]";
	}
	
	
	
	
	
	

}
