package tp;

public class Asiento {
	
	private int numero;
	private boolean disponible;
	private String seccion;
	
	public Asiento(int numero, String seccion, boolean disponible) {
		this.numero = numero;
		this.seccion = seccion;
		this.disponible = disponible;
	}
	
	// Getters y setters
    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getSeccion() {
        return seccion;
    }

    public void setSeccion(String seccion) {
        this.seccion = seccion;
    }

    public boolean isDisponible() {
        return disponible;
    }

    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }

    // Método para liberar el asiento
    public void liberar() {
        this.disponible = true;
    }

    // Método para ocupar el asiento
    public void ocupar() {
        this.disponible = false;
    }

    @Override
    public String toString() {
        return "Asiento " + numero + " (" + seccion + ") - " + (disponible ? "Disponible" : "Ocupado");
	}


}
