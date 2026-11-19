package edu.fiuba.algo3.modelo;

public class Saltador extends Vek {

    public Saltador(Posicion posicion) {
        super(2, posicion);
    }
    
    @Override
    public void aplicarEfectoDelAgua() {
        recibirDanio(getVidaActual());
    }

}
