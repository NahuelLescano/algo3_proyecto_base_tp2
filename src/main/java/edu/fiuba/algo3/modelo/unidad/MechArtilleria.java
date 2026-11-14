package edu.fiuba.algo3.modelo.unidad;

import edu.fiuba.algo3.modelo.tablero.Posicion;

public class MechArtilleria extends Mech {
    private static final int VIDA_INICIAL = 2;

    private Artilleria arma;

    public MechArtilleria(Posicion posicion) {
        this(posicion, new Artilleria());
    }

    private MechArtilleria(Posicion posicion, Artilleria arma) {
        super(VIDA_INICIAL, posicion, arma);
        this.arma = arma;
    }

    // TODO: por que necesita `getPosicion`
    public void iniciarAtaque(Tablero tablero, Posicion objetivo) {
        arma.atacar(tablero, getPosicion(), objetivo);
    }
}
