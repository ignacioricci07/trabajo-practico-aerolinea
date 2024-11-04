package tp;

public class Asiento {
	
	int numero;
	Pasajero pasajero;
	boolean disponible;
	int seccion;
	
	public Asiento(int numero, int seccion) {
		this.numero = numero;
		this.seccion = seccion;
		this.disponible = true;
		this.pasajero = null;
	}
	
	public boolean estaDisponible() {
		return disponible;
	}

}
