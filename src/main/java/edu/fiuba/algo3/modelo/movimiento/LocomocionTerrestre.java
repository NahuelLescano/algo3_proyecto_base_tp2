package edu.fiuba.algo3.modelo.movimiento;

public class LocomocionTerrestre implements Locomocion {

    @Override
    public void aplicarEfectoAgua(Unidad u) {
        u.reaccionarAguaTerrestre();
    }

    @Override
    public void aplicarEfectoAcido(Unidad u) {
        u.contaminar();
    }
}
