package tp;

public class Pasaje {
    
    private static int codigoPasajeActual = 1; // Variable estática para el código incremental
    private int dni;
    private int codPasaje;
    private Vuelo vuelo;
    private int asiento;

    public Pasaje(int dni, Vuelo vuelo, int asiento) {
        this.dni = dni;
        this.codPasaje = generarCodigo(); // Llamada al método estático para asignar el código único
        this.vuelo = vuelo;
        this.asiento = asiento;
    }

    public static int generarCodigo() {
        return codigoPasajeActual++;
    }

    // Getters y setters
    public int getDni() {
        return dni;
    }

    public int getCodPasaje() {
        return codPasaje;
    }

    public Vuelo getVuelo() {
        return vuelo;
    }

    public int getAsiento() {
        return asiento;
    }
}
