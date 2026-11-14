package edu.fiuba.algo3.modelo.unidad;

import edu.fiuba.algo3.modelo.arma.ArmaDireccional;
import edu.fiuba.algo3.modelo.arma.Canion;
import edu.fiuba.algo3.modelo.tablero.Direccion;
import edu.fiuba.algo3.modelo.tablero.Posicion;
import edu.fiuba.algo3.modelo.tablero.Tablero;

public class MechCanion extends Mech {
    private static final int VIDA_INICIAL = 3;

    public MechCanion(Posicion posicion, ArmaDireccional arma) {
        super(VIDA_INICIAL, posicion, arma);
    }

    // TODO: por que necesita `getPosicion`
    public void iniciarAtaque(Tablero tablero, Direccion direccion) {
        getArma().atacar(tablero, getPosicion(), direccion);
    }
}
