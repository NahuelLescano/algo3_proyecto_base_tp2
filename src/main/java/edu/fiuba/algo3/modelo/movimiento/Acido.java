package edu.fiuba.algo3.modelo.movimiento;

public class Acido extends Terreno {

    @Override
    public boolean esTransitable() {
        return true;
    }

    @Override
    public boolean transitablePara(Locomocion l) {
        return true;
    }

    @Override
    public void aplicarEfecto(Unidad u) {
        u.aplicarEfectoAcido();
    }

    @Override
    public boolean bloqueaDisparos() {
        return false;
    }

    @Override
    public void recibirDanio(int puntos, Casilla casilla) {}

    @Override
    public void validarPuedeAlojar() {}
}
