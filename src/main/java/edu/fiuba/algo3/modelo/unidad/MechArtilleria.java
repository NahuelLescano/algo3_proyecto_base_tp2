package edu.fiuba.algo3.modelo.unidad;

import edu.fiuba.algo3.modelo.arma.Artilleria;
import edu.fiuba.algo3.modelo.tablero.Posicion;
import edu.fiuba.algo3.modelo.tablero.Tablero;

public class MechArtilleria extends Mech {
    private static final int VIDA_INICIAL = 2;

    private Artilleria arma;

    public MechArtilleria(Posicion posicion) {
        this(posicion, new Artilleria());
    }

    public MechArtilleria(Posicion posicion, Artilleria arma) {
        super(VIDA_INICIAL, posicion, arma);
        this.arma = arma;
    }

    @Override
    public boolean iniciarAtaque(Tablero tablero, Posicion objetivo) {
        if (!consumirAtaque()) {
            return false;
        }

        arma.atacar(tablero, getPosicion(), objetivo);
        return true;
    }
}
