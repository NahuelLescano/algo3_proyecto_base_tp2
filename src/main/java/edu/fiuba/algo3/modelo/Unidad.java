package edu.fiuba.algo3.modelo;

public abstract class Unidad {
    private int vidaActual;
    private Posicion posicion;

    public Unidad(int vida, Posicion posicion){
        this.vidaActual = vida;
        this.posicion = posicion;
    }

    public void recibirDanio(int danio) {
        if (danio < 0) {
            throw new IllegalArgumentException("El daño no puede ser negativo");
        }

        vidaActual -= danio;

        if (vidaActual < 0) {
            vidaActual = 0;
        }
    }

    public int getVidaActual() {
        return vidaActual;
    }

    public Posicion getPosicion() {
        return posicion;
    }

    void setPosicion(Posicion posicion) {
        this.posicion = posicion;
    }
    
    public boolean estaViva() {
        return vidaActual > 0;
    }
    
    public boolean puedeMoverse() {
        return true;
    }

    public void registrarMovimiento() {
    }

}
