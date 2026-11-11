package edu.fiuba.algo3.modelo;

public class MechCanion extends Mech {

    private static final int VIDA_INICIAL = 3;

    private final ArmaDireccional arma;

    public MechCanion(Posicion posicion) {
        super(VIDA_INICIAL, posicion);
        this.arma = new Canon();
    }

    public void iniciarAtaque(Tablero tablero, Direccion direccion) {
        arma.atacar(tablero, getPosicion(), direccion);
    }
}
