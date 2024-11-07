package tp;

import java.util.Date;
import java.util.Map;

public class VueloPrivado extends Vuelo {
    
	private Cliente clienteComprador;
	private double precioPorJet;
	private int cantidadJets;
    private double costoTotal;

    public VueloPrivado(String codigo, Date fecha, Aeropuerto origen, Aeropuerto destino,  int tripulantes,
    double valorRefrigerio, double[] precios, Map<Integer, Asiento> asientos, Cliente clienteComprador, double precioPorJet, int cantidadJets, double costoTotal) {
        super(codigo, fecha, origen, destino, tripulantes, valorRefrigerio, precios, asientos);
        this.clienteComprador = clienteComprador;
        this.precioPorJet = precioPorJet;
        this.cantidadJets = cantidadJets;
        this.costoTotal = costoTotal;
    }

	public int getCantidadJets() {
		// TODO Auto-generated method stub
		return cantidadJets;
	}



}
