package edu.fiuba.algo3.modelo;

public class Posicion {
    private final int columna;
    private final int fila;

    public Posicion(int fila, int columna){
        this.fila = fila;
        this.columna = columna;
    }

    public int getFila() {
        return fila;
    }

    public int getColumna() {
        return columna;
    }
}
