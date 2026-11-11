package edu.fiuba.algo3.modelo;

public class MechDeCombate extends Mech {

    private static final int VIDA_INICIAL = 3;

    private final ArmaDireccional arma;

    public MechDeCombate(Posicion posicion) {
        super(VIDA_INICIAL, posicion);
        this.arma = new Punio();
    }

    public void iniciarAtaque(Tablero tablero, Direccion direccion) {
        arma.atacar(tablero, getPosicion(), direccion);
    }
}
