package tp;

import java.text.SimpleDateFormat;
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
		private List<Cliente> pasajeros;
		private Map<Integer, Asiento> asientos;
		private double valorRefrigerio;
    	private double[] precios;
		

		
		
		
	public Vuelo (String codigo, Date fecha, Aeropuerto origen, Aeropuerto destino,  int tripulantes, double valorRefrigerio, double[] precios, Map<Integer, Asiento> asientos) {
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

	public void agregarPasajero(Cliente cliente) {
        if (!pasajeros.contains(cliente)) {
            pasajeros.add(cliente);
        }
    }

    public List<Cliente> getPasajeros() {
        return pasajeros;
    }
	
	public  String getCodigo() {
		return codigo;
	}

	public Map<Integer, Asiento> getAsientos() {
        return asientos;
    }

	@Override
    public String toString() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        return codigo + " - " + origen.getNombre() + " - " + destino.getNombre() + " - " + sdf.format(fecha);
    }
	
}
