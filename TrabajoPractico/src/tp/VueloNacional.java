package tp;

import java.util.Map;

public class VueloNacional extends Vuelo {

    private double valorRefrigerio;
    private double[] precios;
    private Map<Integer, String> asientos;

    public VueloNacional(String codigo, String fecha, Aeropuerto origen, Aeropuerto destino,
                         int tripulantes, double valorRefrigerio, double[] precios, Map<Integer, String> asientos) {
        super(codigo, null, origen, destino, tripulantes);
        this.valorRefrigerio = valorRefrigerio;
        this.precios = precios;
        this.asientos = asientos;
    }

}
