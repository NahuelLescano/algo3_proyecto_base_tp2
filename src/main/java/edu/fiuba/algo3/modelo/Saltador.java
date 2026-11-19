package edu.fiuba.algo3.modelo;

public class Saltador extends VekTerrestre {

    private final AtaqueVek ataque;

    public Saltador(Posicion posicion) {
        super(2, posicion);
        this.ataque = new AtaqueSaltador();
    }

    public void iniciarAtaque(Tablero tablero, Posicion objetivo) {
        ataque.atacar(tablero, getPosicion(), objetivo);
    }
}
