package edu.fiuba.algo3.modelo;

public abstract class Mech extends Unidad{
    private final Arma arma;

    public Mech(int vida, Posicion posicion, Arma arma){
        super(vida, posicion);
        this.arma = arma;
    }

    public void iniciarAtaque(Tablero tablero, Direccion direccion) {
        arma.atacar(tablero, getPosicion(), direccion);
    }
}
