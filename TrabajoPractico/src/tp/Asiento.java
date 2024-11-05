package tp;

public class Asiento {
	
	int numero;
	Pasajero pasajero;
	boolean disponible;
	String seccion;
	
	public Asiento(int numero, String seccion) {
		this.numero = numero;
		this.seccion = seccion;
		this.disponible = true;
		this.pasajero = null;
	}
	
	public boolean estaDisponible() {
		return disponible;
	}

	public String getClase(){
		return seccion;
	}

}
