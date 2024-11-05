package tp;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class Vuelo {
		
		String origen;
		Date fecha;
		String codigo;
		String destino;
		List<Pasajero> pasajeros;
		Map<Integer, Asiento> asientos;
		
		
		
	public Vuelo (String origen, String destino, String fecha, int tripulantes, String horaSalida, Aeropuerto AeropuertoSalida,
			String horaLlegada, Aeropuerto aeropuertoLlegada) {
		this.codigo = codigo;
        this.destino = destino;
        this.horaLlegada = horaLlegada;
        this.pasajeros = new ArrayList<>();
        this.asientos = new HashMap<>();
	}
		
	public Map<Integer, String> consultarAsientosDisponibles(){
		Map<Integer, String> asientosDisponibles = new HashMap<>(); 
		this.asientos.forEach((numeroAsiento, asiento) -> {
			if(asiento.estaDisponible()) {
				asientosDisponibles.put(numeroAsiento, asiento.getClase());
			}
		});
		return asientosDisponibles;
	}
	
	public  String getCodigo() {
		return codigo;
	}

	
}
