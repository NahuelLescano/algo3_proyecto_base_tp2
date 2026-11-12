package edu.fiuba.algo3.modelo.movimiento;

public class EstadoNormal implements EstadoSalud {

    @Override
    public int calcularDanioEfectivo(int danioBase) {
        return danioBase;
    }

    @Override
    public EstadoSalud contaminar() {
        return new EstadoContaminado();
    }

}
