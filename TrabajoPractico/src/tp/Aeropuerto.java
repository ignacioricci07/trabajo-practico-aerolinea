package tp;


import java.util.Map;

public class Aeropuerto {

    String nombre;
    String pais;
    String provincia;
    String direccion;

    public Aeropuerto(String nombre, String pais, String provincia, String direccion){
        this.nombre = nombre;
        this.pais = pais;
        this.provincia = provincia;
        this.direccion = direccion;
    }

    public static Aeropuerto obtenerAeropuertoPorNombre(String nombre, Map<String, Aeropuerto> aeropuertos) {
    return aeropuertos.get(nombre); // Devuelve el aeropuerto o null si no se encuentra
}

    public String getNombre() {
    	return nombre;
    }
    
}
