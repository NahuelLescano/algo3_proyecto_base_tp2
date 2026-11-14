package edu.fiuba.algo3.modelo;

public interface ArmaDireccional extends Arma {

    void atacar(Tablero tablero, Posicion origen, Direccion direccion);
}
