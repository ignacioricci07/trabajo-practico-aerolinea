package tp;

import java.util.Date;
import java.util.List;

public abstract class Vuelo {

		Date fecha;
		String codigo;
		String destino;
		int cantAsientos;
		String horallegada;
		String horaSalida;
		Aeropuerto aeropuertoSalida;
		Aeropuerto aeropuertoLlegada;
		List<Pasajero> pasajeros;
		
}
