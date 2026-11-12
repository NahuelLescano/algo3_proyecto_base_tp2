package edu.fiuba.algo3.modelo.movimiento;

import edu.fiuba.algo3.modelo.excepciones.MovimientoInvalidoException;

import java.util.Objects;

public class Posicion {
    private final int fila;
    private final int columna;

    public Posicion(int fila, int columna) {
        this.fila    = fila;
        this.columna = columna;
    }

    public boolean esAdyacenteOrtogonalA(Posicion otra) {
        return this.distanciaManhattan(otra) == 1;
    }

    public Posicion siguienteEnLineaDesde(Posicion origen) {
        if (!origen.esAdyacenteOrtogonalA(this)) {
            throw new MovimientoInvalidoException("El empuje solo puede realizarse hacia una casilla adyacente.");
        }
        int deltaFila = this.fila - origen.fila;
        int deltaColumna = this.columna - origen.columna;
        return new Posicion(this.fila + deltaFila, this.columna + deltaColumna);
    }

    public int distanciaManhattan(Posicion otra) {
        return Math.abs(this.fila - otra.fila) + Math.abs(this.columna - otra.columna);
    }

    public Posicion sumar(Direccion dir) {
        return dir.desplazar(this);
    }

    public boolean estaEnCoordenadas(int fila, int columna) {
        return this.fila == fila && this.columna == columna;
    }

    public Posicion sumar(int dF, int dC) {
        return new Posicion(this.fila + dF, this.columna + dC);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Posicion)) return false;
        Posicion pos = (Posicion)o;
        return this.fila == pos.fila && this.columna == pos.columna;
    }

    @Override
    public int hashCode() {
        return Objects.hash(fila, columna);
    }
}
