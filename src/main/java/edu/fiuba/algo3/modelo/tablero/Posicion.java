package edu.fiuba.algo3.modelo.tablero;

public class Posicion {
    private int columna;
    private int fila;

    public Posicion(int fila, int columna) {
        this.fila = fila;
        this.columna = columna;
    }

    public Posicion desplazar(Direccion direccion) {
        return new Posicion(
                fila + direccion.getDesplazamientoEnFila(),
                columna + direccion.getDesplazamientoEnColumna());
    }

    public Direccion direccionHacia(Posicion objetivo) {

        int dFila = objetivo.getFila() - this.fila;
        int dColumna = objetivo.getColumna() - this.columna;

        if (dFila == 0 && dColumna > 0)
            return Direccion.ESTE;
        if (dFila == 0 && dColumna < 0)
            return Direccion.OESTE;
        if (dColumna == 0 && dFila > 0)
            return Direccion.SUR;
        if (dColumna == 0 && dFila < 0)
            return Direccion.NORTE;

        throw new IllegalArgumentException("Las posiciones no están alineadas");
    }

    public boolean esAdyacenteA(Posicion otra) {
        int distancia = Math.abs(this.fila - otra.fila)
                + Math.abs(this.columna - otra.columna);

        return distancia == 1;
    }

    @Override
    public boolean equals(Object objeto) {
        if (this == objeto) {
            return true;
        }

        if (!(objeto instanceof Posicion)) {
            return false;
        }

        Posicion otraPosicion = (Posicion) objeto;

        return this.fila == otraPosicion.fila &&
                this.columna == otraPosicion.columna;
    }

    public int getFila() {
        return fila;
    }

    public int getColumna() {
        return columna;
    }
}
