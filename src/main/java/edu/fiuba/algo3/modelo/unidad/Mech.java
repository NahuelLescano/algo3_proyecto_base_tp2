package edu.fiuba.algo3.modelo.unidad;

import edu.fiuba.algo3.modelo.arma.Arma;
import edu.fiuba.algo3.modelo.tablero.Posicion;

public abstract class Mech extends Unidad {
    private Arma arma;

    public Mech(int vida, Posicion posicion, Arma arma) {
        super(vida, posicion);
        this.arma = arma;
    }

    // TODO: refactor
    protected Arma getArma() {
        return this.arma;
    }
}
