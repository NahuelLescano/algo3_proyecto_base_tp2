package edu.fiuba.algo3.modelo;

public interface ArmaDePosicion extends Arma {

    void atacar(Tablero tablero, Posicion origen, Posicion objetivo);
}
