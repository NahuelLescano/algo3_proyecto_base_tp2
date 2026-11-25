package edu.fiuba.algo3.modelo.unidad;

import edu.fiuba.algo3.modelo.arma.Disparo;
import edu.fiuba.algo3.modelo.arma.vek.EscupirAcido;
import edu.fiuba.algo3.modelo.arma.vek.Mordida;
import edu.fiuba.algo3.modelo.arma.vek.Picadura;
import edu.fiuba.algo3.modelo.unidad.efecto.*;

public class VekFactory {

    public Vek crearSaltador() {
        return new Vek(2, 3, new Terrestre(), new Mordida());
    }

    public Vek crearLuciernaga() {
        EscupirAcido acido = new EscupirAcido(new Disparo(1, new EfectoAcido()));
        return new Vek(3, 2, new Terrestre(), acido);
    }

    public Vek crearAvispon() {
        return new Vek(2, 4, new Volador(), new Picadura());
    }
}
