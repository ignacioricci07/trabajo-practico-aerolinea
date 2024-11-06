package tp;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Date;

public class Aerolinea implements IAerolinea {

	private String  cuit;
	private String nombre;
	private Map<String, Aeropuerto> aeropuertos;
	private Map<Integer, Cliente> clientes;
	private Map<String, Vuelo> vuelos;
	private int numeroVueloPublico;
	private int numeroVueloInternacional;

	
	public Aerolinea (String nombre, String cuit) {
		this.nombre = nombre;
		this.cuit = cuit;
		this.aeropuertos = new HashMap<>();
		this.clientes = new HashMap<>();
		this.vuelos = new HashMap<>();
		this.numeroVueloPublico = 100;
		this.numeroVueloInternacional = 100;
		
	}
	
	@Override
	public void registrarCliente(int dni, String nombre, String telefono) {
        if (!clientes.containsKey(dni)) { 
            Cliente cliente = new Cliente(dni, nombre, telefono);
            clientes.put(dni, cliente); 
        } else {
            System.out.println("El cliente ya está registrado.");
        }
    }
	

	@Override
	public void registrarAeropuerto(String nombre, String pais, String provincia, String direccion) {
        if (!aeropuertos.containsKey(nombre)) {
            Aeropuerto aeropuerto = new Aeropuerto(nombre, pais, provincia, direccion);
            aeropuertos.put(nombre, aeropuerto);
            System.out.println("Aeropuerto registrado: " + nombre);
        } else {
            System.out.println("El aeropuerto con el nombre '" + nombre + "' ya está registrado.");
        }
    }

	@Override
	public String registrarVueloPublicoNacional(String origen, String destino, String fecha, int tripulantes,
                                                double valorRefrigerio, double[] precios, int[] cantAsientos) {
        // Validación de aeropuertos
        Aeropuerto aeropuertoOrigen = aeropuertos.get(origen);
        Aeropuerto aeropuertoDestino = aeropuertos.get(destino);

        if (aeropuertoOrigen == null || aeropuertoDestino == null) {
            throw new IllegalArgumentException("Origen o destino no registrado en la aerolínea.");
        }
        if (!"Argentina".equalsIgnoreCase(aeropuertoOrigen.pais) || !"Argentina".equalsIgnoreCase(aeropuertoDestino.pais)) {
            throw new IllegalArgumentException("El origen y destino deben estar en Argentina.");
        }

			// Validación de la fecha de salida
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); // Cambia el formato según el formato de `fecha`
    Date fechaSalida;
    try {
        fechaSalida = sdf.parse(fecha);
    } catch (ParseException e) {
        throw new IllegalArgumentException("Formato de fecha inválido. Use el formato yyyy-MM-dd.");
    }
    if (fechaSalida.before(new Date())) {
        throw new IllegalArgumentException("La fecha de salida debe ser posterior a la fecha actual.");
    }

        // Validación de los arrays de asientos y precios
        if (precios.length != 2 || cantAsientos.length != 2) {
            throw new IllegalArgumentException("La longitud de precios y cantAsientos debe ser 2.");
        }

        // Crear asiento
        Map<Integer, String> asientos = new HashMap<>();
        int numeroAsiento = 1;
        
        // Asientos de clase Turista
        for (int i = 0; i < cantAsientos[0]; i++) {
            asientos.put(numeroAsiento++, "Turista");
        }
        
        // Asientos de clase Ejecutivo
        for (int i = 0; i < cantAsientos[1]; i++) {
            asientos.put(numeroAsiento++, "Ejecutivo");
        }

        // Crear el vuelo
        String codigoVuelo = numeroVueloPublico + "-PUB";
        VueloNacional vuelo = new VueloNacional(codigoVuelo, fechaSalida, aeropuertoOrigen, aeropuertoDestino, tripulantes,
		valorRefrigerio, precios, asientos);

        // Registrar el vuelo y actualizar el contador
        vuelos.put(codigoVuelo, vuelo);
        numeroVueloPublico++; // Incrementar el número para el próximo vuelo

        return codigoVuelo;
    }

	@Override
	public String registrarVueloPublicoInternacional(String origen, String destino, String fecha, int tripulantes, 
                                                double valorRefrigerio, int cantRefrigerios, double[] precios,  
                                                int[] cantAsientos, String[] escalas) {
    // Verificar que el origen y el destino estén registrados en la aerolínea
    Aeropuerto aeropuertoOrigen = aeropuertos.get(origen);
    Aeropuerto aeropuertoDestino = aeropuertos.get(destino);

    if (aeropuertoOrigen == null || aeropuertoDestino == null) {
        throw new IllegalArgumentException("Origen o destino no registrado en la aerolínea.");
    }

    // Validación de la fecha de salida
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); // Cambia el formato según el formato de `fecha`
    Date fechaSalida;
    try {
        fechaSalida = sdf.parse(fecha);
    } catch (ParseException e) {
        throw new IllegalArgumentException("Formato de fecha inválido. Use el formato yyyy-MM-dd.");
    }
    if (fechaSalida.before(new Date())) {
        throw new IllegalArgumentException("La fecha de salida debe ser posterior a la fecha actual.");
    }

    // Verificar que los arrays de precios y cantAsientos tengan longitud 3
    if (precios.length != 3 || cantAsientos.length != 3) {
        throw new IllegalArgumentException("Los arreglos precios y cantAsientos deben tener una longitud de 3.");
    }

    // Crear los asientos en el orden: Turista, Ejecutiva, Primera clase
    Map<Integer, String> asientos = new HashMap<>();
    int numeroAsiento = 1;

    // Asientos de clase Turista
    for (int i = 0; i < cantAsientos[0]; i++) {
        asientos.put(numeroAsiento++, "Turista");
    }

    // Asientos de clase Ejecutiva
    for (int i = 0; i < cantAsientos[1]; i++) {
        asientos.put(numeroAsiento++, "Ejecutiva");
    }

    // Asientos de Primera clase
    for (int i = 0; i < cantAsientos[2]; i++) {
        asientos.put(numeroAsiento++, "Primera");
    }


    // Generar el código de vuelo único
    String codigoVuelo = numeroVueloInternacional + "-PUB";
    // Crear el vuelo internacional y registrarlo en la lista de vuelos
    VueloInternacional vuelo = new VueloInternacional(codigoVuelo, fechaSalida, aeropuertoOrigen, aeropuertoDestino,
                                                  tripulantes, valorRefrigerio, precios, asientos, cantRefrigerios, escalas);


    vuelos.put(codigoVuelo, vuelo);
	numeroVueloPublico++; // Incrementar el número para el próximo vuelo

    return codigoVuelo;
}

	@Override
	public String VenderVueloPrivado(String origen, String destino, String fecha, int tripulantes, double precio,
			int dniComprador, int[] acompaniantes) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'VenderVueloPrivado'");
	}

	@Override
	public Map<Integer, String> asientosDisponibles(String codVuelo) {
		Map<Integer, String> losasientosDisponibles= new HashMap<>();
		if(codVuelo==null) {
			return losasientosDisponibles;
		}
		for(Vuelo vuelo:this.vuelos) {
			if(vuelo.getCodigo().equals(codVuelo)) {
				return vuelo.consultarAsientosDisponibles();
			}
		}
	}

	@Override
	public int venderPasaje(int dni, String codVuelo, int nroAsiento, boolean aOcupar) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'venderPasaje'");
	}

	@Override
	public List<String> consultarVuelosSimilares(String origen, String destino, String Fecha) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'consultarVuelosSimilares'");
	}

	@Override
	public void cancelarPasaje(int dni, String codVuelo, int nroAsiento) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'cancelarPasaje'");
	}

	@Override
	public void cancelarPasaje(int dni, int codPasaje) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'cancelarPasaje'");
	}

	@Override
	public List<String> cancelarVuelo(String codVuelo) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'cancelarVuelo'");
	}

	@Override
	public double totalRecaudado(String destino) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'totalRecaudado'");
	}

	@Override
	public String detalleDeVuelo(String codVuelo) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'detalleDeVuelo'");
	}
	
	
	
}
