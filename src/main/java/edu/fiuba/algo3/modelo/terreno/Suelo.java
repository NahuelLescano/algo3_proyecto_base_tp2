package edu.fiuba.algo3.modelo.terreno;

import edu.fiuba.algo3.modelo.unidad.Unidad;

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

    @Override
    public void recibirDanio(int danio) {
    }
}
