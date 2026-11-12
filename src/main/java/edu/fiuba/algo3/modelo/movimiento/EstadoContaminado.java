package edu.fiuba.algo3.modelo.movimiento;

public class EstadoContaminado implements EstadoSalud {

    @Override
    public int calcularDanioEfectivo(int danioBase) {
        return danioBase * 2;
    }

    @Override
    public EstadoSalud contaminar() {
        return this;
    }
}
