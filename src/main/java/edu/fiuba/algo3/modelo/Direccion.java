package edu.fiuba.algo3.modelo;

public enum Direccion {
    NORTE(1, 0),
    SUR(-1, 0),
    ESTE(0, 1),
    OESTE(0, -1);

    private final int desplazamientoEnFila;
    private final int desplazamientoEnColumna;

    Direccion(int desplazamientoEnFila, int desplazamientoEnColumna) {
        this.desplazamientoEnFila = desplazamientoEnFila;
        this.desplazamientoEnColumna = desplazamientoEnColumna;
    }

    public int getDesplazamientoEnFila() {
        return desplazamientoEnFila;
    }

    public int getDesplazamientoEnColumna() {
        return desplazamientoEnColumna;
    }
}