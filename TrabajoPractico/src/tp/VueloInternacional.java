package tp;

import java.util.Map;
import java.util.Date;

public class VueloInternacional extends Vuelo {

    private int cantRefrigerios;
    private String[] escalas;

    public VueloInternacional(String codigo, Date fecha, Aeropuerto origen, Aeropuerto destino,
                         int tripulantes, double valorRefrigerio, double[] precios, Map<Integer, Asiento> asientos, int cantRefrigerios, String[] escalas){
                            super(codigo, fecha, origen, destino, tripulantes, valorRefrigerio, precios, asientos);
                            this.cantRefrigerios = cantRefrigerios;
                            this.escalas = escalas != null? escalas : new String[0];
                         }

}
