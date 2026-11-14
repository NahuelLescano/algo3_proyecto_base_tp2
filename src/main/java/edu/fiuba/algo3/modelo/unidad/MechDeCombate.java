package edu.fiuba.algo3.modelo.unidad;

import edu.fiuba.algo3.modelo.Tablero;
import edu.fiuba.algo3.modelo.arma.ArmaDireccional;
import edu.fiuba.algo3.modelo.arma.Punio;
import edu.fiuba.algo3.modelo.tablero.Direccion;
import edu.fiuba.algo3.modelo.tablero.Posicion;

public class MechDeCombate extends Mech {
    private static final int VIDA_INICIAL = 3;

    private ArmaDireccional arma;

    public MechDeCombate(Posicion posicion) {
        this(posicion, new Punio());
    }

    private MechDeCombate(Posicion posicion, ArmaDireccional arma) {
        super(VIDA_INICIAL, posicion, arma);
        this.arma = arma;
    }

    // TODO: por que necesita `getPosicion`
    public void iniciarAtaque(Tablero tablero, Direccion direccion) {
        arma.atacar(tablero, getPosicion(), direccion);
    }
}
