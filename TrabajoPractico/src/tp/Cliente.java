package tp;

public class Cliente {
    int dni;
    String nombre;
    String telefono;

    public Cliente(int dni, String nombre, String telefono){
        this.dni = dni;
        this.telefono = telefono;
        this.nombre = nombre;
    }

	public int getDni() {
	        return dni;
	}

	public String getNombre() {
	        return nombre;
	}
}
