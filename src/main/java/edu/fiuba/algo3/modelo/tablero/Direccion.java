package edu.fiuba.algo3.modelo.tablero;

public enum Direccion {
    NORTE(-1, 0), // se disminuye la fila
    SUR(1, 0), // aumenta la fila
    ESTE(0, 1), // aumenta la columna
    OESTE(0, -1); // disminuye la columna

    private final int desplazamientoEnFila;
    private final int desplazamientoEnColumna;

    private Direccion(int desplazamientoEnFila, int desplazamientoEnColumna) {
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
