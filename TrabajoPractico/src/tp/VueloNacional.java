package tp;

import java.util.Map;
import java.util.Date;

public class VueloNacional extends Vuelo {


    public VueloNacional(String codigo, Date fecha, Aeropuerto origen, Aeropuerto destino,
                         int tripulantes, double valorRefrigerio, double[] precios, Map<Integer, Asiento> asientos) {
        super(codigo, fecha, origen, destino, tripulantes, valorRefrigerio, precios, asientos);
        
    }

}
