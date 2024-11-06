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
	private int numeroVueloPrivado;
	private Map<Integer, Pasaje> pasajes = new HashMap<>();
	

	
	public Aerolinea (String nombre, String cuit) {
		this.nombre = nombre;
		this.cuit = cuit;
		this.aeropuertos = new HashMap<>();
		this.clientes = new HashMap<>();
		this.vuelos = new HashMap<>();
		this.numeroVueloPublico = 100;
		this.numeroVueloInternacional = 100;
		this.numeroVueloPrivado = 100;
		this.pasajes = new HashMap<>();
		
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

        // Crear los asientos, estructura: Map<Integer, Asiento> para cada clase
    Map<Integer, Asiento> asientos = new HashMap<>();
    int numeroAsiento = 1;

    // Asientos de clase Turista
    for (int i = 0; i < cantAsientos[0]; i++) {
        Asiento asiento = new Asiento(numeroAsiento++, "Turista", true); // Asiento disponible por defecto
        asientos.put(asiento.getNumero(), asiento);
    }

    // Asientos de clase Ejecutivo
    for (int i = 0; i < cantAsientos[1]; i++) {
        Asiento asiento = new Asiento(numeroAsiento++, "Ejecutivo", true); // Asiento disponible por defecto
        asientos.put(asiento.getNumero(), asiento);
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
    Map<Integer, Asiento> asientos = new HashMap<>();
    int numeroAsiento = 1;

    // Asientos de clase Turista
    for (int i = 0; i < cantAsientos[0]; i++) {
        Asiento asiento = new Asiento(numeroAsiento++, "Turista", true); // Asiento disponible por defecto
        asientos.put(asiento.getNumero(), asiento);
    }

    // Asientos de clase Ejecutiva
    for (int i = 0; i < cantAsientos[1]; i++) {
        Asiento asiento = new Asiento(numeroAsiento++, "Ejecutiva", true); // Asiento disponible por defecto
        asientos.put(asiento.getNumero(), asiento);
    }

    // Asientos de Primera clase
    for (int i = 0; i < cantAsientos[2]; i++) {
        Asiento asiento = new Asiento(numeroAsiento++, "Primera", true); // Asiento disponible por defecto
        asientos.put(asiento.getNumero(), asiento);
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
    // Convertir la fecha de tipo String a Date
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    Date fechaSalida;
    try {
        fechaSalida = sdf.parse(fecha);
    } catch (ParseException e) {
        throw new IllegalArgumentException("Formato de fecha inválido. Use el formato yyyy-MM-dd.");
    }

    // Verificar que la fecha de salida sea posterior a la fecha actual
    Date fechaActual = new Date();
    if (fechaSalida.before(fechaActual)) {
        throw new IllegalArgumentException("La fecha de salida debe ser posterior a la fecha actual.");
    }

    // Validación de origen y destino
    if (origen == null || destino == null || origen.isEmpty() || destino.isEmpty()) {
        throw new IllegalArgumentException("El aeropuerto de origen y destino no pueden estar vacíos.");
    }

    // Calcular la cantidad de jets necesarios
    int totalPasajeros = 1 + acompaniantes.length;
    int capacidadJet = 15;
    int cantidadJets = (int) Math.ceil((double) totalPasajeros / capacidadJet);

    // Calcular el costo total
    double costoTotal = cantidadJets * precio;

    // Generar el código de vuelo y actualizar el contador
    String codigoVuelo = numeroVueloPrivado + "-PRI";
    numeroVueloPrivado++;

    return codigoVuelo;
}


	@Override
	public Map<Integer, String> asientosDisponibles(String codVuelo) {
		// Obtener el vuelo correspondiente al código de vuelo
		Vuelo vuelo = vuelos.get(codVuelo);
		if (vuelo == null) {
			throw new IllegalArgumentException("Vuelo no encontrado con el código: " + codVuelo);
		}
	
		// Obtener el mapa de asientos del vuelo
		Map<Integer, Asiento> asientos = vuelo.getAsientos();
		
		// Crear el mapa para los asientos disponibles
		Map<Integer, String> asientosDisponibles = new HashMap<>();
	
		// Recorrer los asientos y agregar los disponibles al mapa
		for (Map.Entry<Integer, Asiento> entry : asientos.entrySet()) {
			Asiento asiento = entry.getValue();
			if (asiento.isDisponible()) {
				// Agregar al mapa solo los asientos disponibles
				asientosDisponibles.put(asiento.getNumero(), asiento.getSeccion());
			}
		}
	
		return asientosDisponibles;
	}
	

	@Override
	public int venderPasaje(int dni, String codVuelo, int nroAsiento, boolean aOcupar) {
		// Verificar que el vuelo exista
		Vuelo vuelo = vuelos.get(codVuelo);
		if (vuelo == null) {
			throw new IllegalArgumentException("El vuelo no existe.");
		}
	
		// Verificar que el pasajero esté registrado como cliente
		Cliente cliente = clientes.get(dni);
		if (cliente == null) {
			throw new IllegalArgumentException("El pasajero debe estar registrado como cliente antes de realizar la compra.");
		}
	
		// Verificar que el cliente no tenga ya un pasaje en el vuelo y asiento especificados
		for (Pasaje pasaje : pasajes.values()) {
			if (pasaje.getDni() == dni && pasaje.getVuelo().getCodigo().equals(codVuelo) && pasaje.getAsiento() == nroAsiento) {
				throw new IllegalArgumentException("El cliente ya tiene un pasaje para este vuelo y asiento.");
			}
		}

		// Verificar que el asiento esté disponible
		Map<Integer, Asiento> asientos = vuelo.getAsientos(); // Obtener el mapa de asientos
		Asiento asiento = asientos.get(nroAsiento); // Obtener el asiento con el número proporcionado
		if (asiento == null || !asiento.isDisponible()) {
			throw new IllegalArgumentException("El asiento no está disponible.");
		}
	
		// Ocupar el asiento si es necesario
		if (aOcupar) {
			asiento.ocupar();
		}
	
		// Generar un nuevo código de pasaje y crear el pasaje
		int codigoPasaje = Pasaje.generarCodigo(); // Se genera un nuevo código usando el método estático
		Pasaje pasaje = new Pasaje(dni, vuelo, nroAsiento);

		// Registrar el pasaje en el cliente
		cliente.agregarPasaje(pasaje);
	
		// Registrar el pasaje en el sistema
		pasajes.put(codigoPasaje, pasaje);

		// Agregar el pasajero al vuelo
		vuelo.agregarPasajero(cliente); // Aquí agregas al cliente (pasajero) al vuelo
	
		return codigoPasaje; // Devolver el código del pasaje comprado
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
		// Buscar el pasaje por el código de pasaje
		Pasaje pasaje = pasajes.get(codPasaje);
	
		// Verificar si el pasaje no existe o si el DNI no coincide
		if (pasaje == null || pasaje.getDni() != dni) {
			throw new IllegalArgumentException("Pasaje no encontrado o el DNI no coincide.");
		}
	
		// Eliminar el pasaje de la lista de pasajes
		pasajes.remove(codPasaje);
	
		// Obtener el vuelo y el número de asiento del pasaje
		Vuelo vuelo = pasaje.getVuelo();
		int numeroAsiento = pasaje.getAsiento(); // Asumimos que getAsiento() devuelve el número de asiento
	
		// Liberar el asiento en el vuelo
		Map<Integer, Asiento> asientos = vuelo.getAsientos(); // Obtenemos el mapa de asientos del vuelo
		Asiento asiento = asientos.get(numeroAsiento); // Buscamos el asiento en el mapa de asientos
	
		if (asiento != null) {
			// Liberamos el asiento (lo marcamos como disponible)
			asiento.setDisponible(true);
		} else {
			throw new IllegalArgumentException("El asiento no existe en el vuelo.");
		}
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
