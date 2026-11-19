package edu.fiuba.algo3.modelo;

public class Luciernaga extends VekTerrestre {

    private final AtaqueVek ataque;

    public Luciernaga(Posicion posicion) {
        super(3, posicion);
        this.ataque = new AtaqueAcido();
    }

    public void iniciarAtaque(Tablero tablero, Posicion objetivo) {
        ataque.atacar(tablero, getPosicion(), objetivo);
    }
}
