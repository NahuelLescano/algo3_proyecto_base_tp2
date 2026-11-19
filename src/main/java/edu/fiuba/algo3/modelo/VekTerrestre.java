package edu.fiuba.algo3.modelo;

public abstract class VekTerrestre extends Vek {

    public VekTerrestre(int vida, Posicion posicion) {
        super(vida, posicion);
    }

    @Override
    public void aplicarEfectoDelAgua() {
        recibirDanio(getVidaActual());
    }
}
