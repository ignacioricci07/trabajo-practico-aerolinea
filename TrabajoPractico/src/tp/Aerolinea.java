package tp;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
public class Aerolinea implements IAerolinea {

	String  CUIT;
	String nombre;
	List<Aeropuerto> aeropuertos;
	List<Cliente> clientes;
	List<Vuelo> vuelos;
	
	public Aerolinea (String nombre, int CUIT) {
		this.nombre = nombre;
		this.CUIT = CUIT;
		
	}
	
	public void registrarCliente(String nombreCliente, int telefono, int DNI) {
		Cliente cliente = new Cliente(nombreCliente, telefono, DNI);
		this.clientes.add(cliente);
	}
	
	public void registrarAeropuerto(String nombreAeropuerto, String lugar) {
		Aeropuerto aeropuerto = new Aeropuerto(nombreAeropuerto, lugar, direccion);
		this.aeropuertos.add(aeropuerto); //AAAAAAAAAAA
	}
	
	public void crearVueloNacional(int cantidadAsientos, int tripulantes, String horaSalida, Aeropuerto aeropuertoSalida, String horaLlegada, Aeropuerto aeropuertoLlegada, int maxPasajerosSeccion1, int maxPasajerosSeccion2, Destino destinosNac) {
	    Vuelo vuelo = new Vuelo(cantidadAsientos, tripulantes, horaSalida, aeropuertoSalida, horaLlegada, aeropuertoLlegada, maxPasajerosSeccion1, maxPasajerosSeccion2, destinosNac);
	    this.vuelos.add(vuelo);
	}
	
	public void crearVueloInternacional(int cantidadAsientos, int tripulantes, String horaSalida, Aeropuerto aeropuertoSalida, String horaLlegada, Aeropuerto aeropuertoLlegada, int maxPasajerosSeccion1, int maxPasajerosSeccion2, int maxPasajerosSeccion3, List<String> escalas, int cantidadRefrigerios, Destino destinosInter) {
	    Vuelo vuelo = new Vuelo(cantidadAsientos, tripulantes, horaSalida, aeropuertoSalida, horaLlegada, aeropuertoLlegada, maxPasajerosSeccion1, maxPasajerosSeccion2, maxPasajerosSeccion3, escalas, cantidadRefrigerios, destinosInter);
	    this.vuelos.add(vuelo);
	}
	
	public void crearVueloPrivado(Cliente comprador, List<Cliente> pasajeros, String destino, int tripulantes, String horaSalida, Aeropuerto aeropuertoSalida, String horaLlegada, Aeropuerto aeropuertoLlegada, double precioPorJet, Destino destinosNac) {
	    Vuelo vuelo = new Vuelo(comprador, pasajeros, destino, tripulantes, horaSalida, aeropuertoSalida, horaLlegada, aeropuertoLlegada, precioPorJet, destinosNac);
	    this.vuelos.add(vuelo);
	}
	
	public Map<Integer, String> consultarAsientosDisponibles(String codigoVuelo){
		Map<Integer, String> asientosDisponibles= new HashMap<>();
		if(codigoVuelo==null) {
			return asientosDisponibles;
		}
		for(Vuelo vuelo:this.vuelos) {
			if(vuelo.getCodigo().equals(codigoVuelo)) {
				return vuelo.consultarAsientosDisponibles();
			}
		}
	}
	
	
	
}
