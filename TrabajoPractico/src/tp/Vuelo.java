package tp;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class Vuelo {
		
		private String codigo;
		private Date fecha;
		private Aeropuerto origen;
		private Aeropuerto destino;
		private int tripulantes;
		private List<Pasajero> pasajeros;
		private Map<Integer, String> asientos;
		private double valorRefrigerio;
    	private double[] precios;
		
		
		
	public Vuelo (String codigo, Date fecha, Aeropuerto origen, Aeropuerto destino,  int tripulantes, double valorRefrigerio, double[] precios, Map<Integer, String> asientos) {
		this.codigo = codigo;
		this.fecha = fecha;
		this.origen = origen;
        this.destino = destino;
		this.tripulantes = tripulantes;
		this.valorRefrigerio = valorRefrigerio;
        this.precios = precios;
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
