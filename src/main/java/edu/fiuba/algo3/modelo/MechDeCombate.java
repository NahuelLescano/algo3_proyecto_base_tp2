package edu.fiuba.algo3.modelo;

public class MechDeCombate extends Mech {
    private static final int VIDA_INICIAL = 3;

    public MechDeCombate(Posicion posicion, Arma arma) {
        super(VIDA_INICIAL, posicion, arma);
    }
}