package edu.fiuba.algo3.modelo.tablero;

import java.util.Objects;

public class Posicion {
    private final int columna;
    private final int fila;

    public Posicion(int fila, int columna) {
        this.fila = fila;
        this.columna = columna;
    }

    public int getFila() {
        return fila;
    }

    public int getColumna() {
        return columna;
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

    @Override
    public int hashCode() {
        return Objects.hash(fila, columna);
    }

    public Posicion desplazar(Direccion direccion) {
        return new Posicion(
                fila + direccion.getDesplazamientoEnFila(),
                columna + direccion.getDesplazamientoEnColumna());
    }
}
