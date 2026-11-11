package edu.fiuba.algo3.modelo;

public class MechCanion extends Mech {

    private static final int VIDA_INICIAL = 3;

    private final ArmaDireccional arma;

    public MechCanion(Posicion posicion) {
        this(posicion, new Canion());
    }

    private MechCanion(Posicion posicion, ArmaDireccional arma) {
        super(VIDA_INICIAL, posicion, arma);
        this.arma = arma;
    }

    public void iniciarAtaque(Tablero tablero, Direccion direccion) {
        arma.atacar(tablero, getPosicion(), direccion);
    }
}