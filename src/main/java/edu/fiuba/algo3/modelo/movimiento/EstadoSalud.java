package edu.fiuba.algo3.modelo.movimiento;

public interface EstadoSalud {
    int calcularDanioEfectivo(int danioBase);
    EstadoSalud contaminar();
}
