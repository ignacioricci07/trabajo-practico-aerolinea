package tp;

import java.util.ArrayList;
import java.util.List;

public class Cliente {

    int dni;
    String nombre;
    String telefono;
    private List<Pasaje> pasajes; // Pasajes del cliente

    public Cliente(int dni, String nombre, String telefono){
        this.dni = dni;
        this.telefono = telefono;
        this.nombre = nombre;
        this.pasajes = new ArrayList<>();
    }

    public void agregarPasaje(Pasaje pasaje) {
        this.pasajes.add(pasaje);
    }

}
