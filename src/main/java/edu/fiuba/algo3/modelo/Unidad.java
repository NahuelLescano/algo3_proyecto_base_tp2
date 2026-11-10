package edu.fiuba.algo3.modelo;

public abstract class Unidad {
    private int vidaActual;
    private Posicion posicion;

    public Unidad(int vida, Posicion posicion){
        this.vidaActual = vida;
        this.posicion = posicion;
    }

    public void recibirDanio(int danio){
        this.vidaActual -= danio;
    }

    public int getVidaActual() {
        return vidaActual;
    }

    public Posicion getPosicion() {
        return posicion;
    }

    public void setPosicion(Posicion posicion) {
        this.posicion = posicion;
    }

}
