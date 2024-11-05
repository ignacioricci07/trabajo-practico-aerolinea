package tp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Aerolinea implements IAerolinea {

	private String  cuit;
	private String nombre;
	private Map<String, Aeropuerto> aeropuertos;
	private Map<Integer, Cliente> clientes;
	private Map<String, Vuelo> vuelos;
	private int numeroVueloPublico;

	
	public Aerolinea (String nombre, String cuit) {
		this.nombre = nombre;
		this.cuit = cuit;
		this.aeropuertos = new HashMap<>();
		this.clientes = new HashMap<>();
		this.vuelos = new HashMap<>();
		this.numeroVueloPublico = 100;
		
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

        // Validación de los arrays de asientos y precios
        if (precios.length != 2 || cantAsientos.length != 2) {
            throw new IllegalArgumentException("La longitud de precios y cantAsientos debe ser 2.");
        }

        // Crear asientos
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
        VueloNacional vuelo = new VueloNacional(codigoVuelo, fecha, aeropuertoOrigen, aeropuertoDestino, tripulantes,
                                                 valorRefrigerio, precios, asientos);

        // Registrar el vuelo y actualizar el contador
        vuelos.put(codigoVuelo, vuelo);
        numeroVueloPublico++; // Incrementar el número para el próximo vuelo

        return codigoVuelo;
    }

	@Override
	public String registrarVueloPublicoInternacional(String origen, String destino, String fecha, int tripulantes,
			double valorRefrigerio, int cantRefrigerios, double[] precios, int[] cantAsientos, String[] escalas) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'registrarVueloPublicoInternacional'");
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
