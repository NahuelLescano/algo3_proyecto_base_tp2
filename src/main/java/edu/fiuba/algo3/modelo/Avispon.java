package edu.fiuba.algo3.modelo;

public class Avispon extends Vek {

    private final AtaqueVek ataque;

    public Avispon(Posicion posicion) {
        super(2, posicion);
        this.ataque = new AtaqueAvispon();
    }

    public void iniciarAtaque(Tablero tablero, Posicion objetivo) {
        ataque.atacar(tablero, getPosicion(), objetivo);
    }

    @Override
    public void aplicarEfectoDelAcido() {
        // El Avispon no se ve afectado por el ácido.
    }
}
