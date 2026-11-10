package edu.fiuba.algo3.modelo;

public class Montania extends Terreno {
    @Override
    public boolean esTransitable() {
        return false;
    }

    @Override
    public boolean bloqueaDisparos() {
        return true;
    }

    @Override
    public void aplicarEfecto(Unidad unidad) {
    }    
}
