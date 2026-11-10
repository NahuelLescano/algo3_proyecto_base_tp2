package edu.fiuba.algo3.modelo;

public class Suelo extends Terreno {
    @Override
    public boolean esTransitable() {
        return true;
    }

    @Override
    public boolean bloqueaDisparos() {
        return false;
    }

    @Override
    public void aplicarEfecto(Unidad unidad) {
    }
}
