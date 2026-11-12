package edu.fiuba.algo3.modelo.movimiento;

public enum Direccion {
    NORTE(-1, 0),
    SUR(1, 0),
    ESTE(0, 1),
    OESTE(0, -1);

    private final int deltaFila;
    private final int deltaColumna;

    private Direccion(int deltaFila, int deltaColumna) {
        this.deltaFila    = deltaFila;
        this.deltaColumna = deltaColumna;
    }

    public Posicion desplazar(Posicion pos) {
        return pos.sumar(this.deltaFila, this.deltaColumna);
    }
}
