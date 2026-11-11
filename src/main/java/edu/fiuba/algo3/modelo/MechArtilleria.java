package edu.fiuba.algo3.modelo;

public class MechDeArtilleria extends Mech {

    private static final int VIDA_INICIAL = 3;

    private final ArmaDeObjetivo arma;

    public MechDeArtilleria(Posicion posicion) {
        super(VIDA_INICIAL, posicion);
        this.arma = new Artilleria();
    }

    public void iniciarAtaque(Tablero tablero, Posicion objetivo) {
        arma.atacar(tablero, getPosicion(), objetivo);
    }
}
