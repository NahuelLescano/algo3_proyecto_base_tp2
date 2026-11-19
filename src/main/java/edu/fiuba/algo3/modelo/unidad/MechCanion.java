package edu.fiuba.algo3.modelo.unidad;

import edu.fiuba.algo3.modelo.arma.ArmaDireccional;
import edu.fiuba.algo3.modelo.arma.Canion;
import edu.fiuba.algo3.modelo.tablero.Direccion;
import edu.fiuba.algo3.modelo.tablero.Posicion;
import edu.fiuba.algo3.modelo.tablero.Tablero;

public class MechCanion extends Mech {
    private static final int VIDA_INICIAL = 3;

    private ArmaDireccional arma;

    public MechCanion(Posicion posicion) {
        this(posicion, new Canion());
    }

    public MechCanion(Posicion posicion, ArmaDireccional arma) {
        super(VIDA_INICIAL, posicion, arma);
        this.arma = arma;
    }

    public boolean iniciarAtaque(Tablero tablero, Direccion direccion) {
        if (!consumirAtaque()) {
            return false;
        }

        arma.atacar(tablero, getPosicion(), direccion);
        return true;
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
