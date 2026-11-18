package edu.fiuba.algo3.modelo;

public abstract class Vek extends Unidad {

    public Vek(int vida, Posicion posicion) {
        super(vida, posicion);
    }
    
    @Override
    public void aplicarEfectoDelAgua() {
        recibirDanio(getVidaActual());
    }
}
