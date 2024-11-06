package tp;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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

    // Método estático para buscar un cliente por DNI en una lista de clientes
    public static Cliente obtenerClientePorDNI(int dni, Map<Integer, Cliente> clientes) {
    return clientes.get(dni); // Devuelve el cliente o null si no se encuentra
}

}
