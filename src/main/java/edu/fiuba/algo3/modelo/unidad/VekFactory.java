package edu.fiuba.algo3.modelo.unidad;

import edu.fiuba.algo3.modelo.arma.vek.EscupirAcido;
import edu.fiuba.algo3.modelo.arma.vek.Mordida;
import edu.fiuba.algo3.modelo.arma.vek.Picadura;

public class VekFactory {

    public Vek crearSaltador() {
        return new Vek(2,3, new Terrestre(), new Mordida());
    }

    public Vek crearLuciernaga() {
        return new Vek(3, 2, new Terrestre(), new EscupirAcido());
    }

    public Vek crearAvispon() {
        return new Vek(2, 4, new Volador(), new Picadura());
    }
}
