package edu.fiuba.algo3.modelo.terreno;

import edu.fiuba.algo3.modelo.unidad.Unidad;

public abstract class Terreno {
    public abstract boolean esTransitable();
    public abstract boolean bloqueaDisparos();
    public abstract void aplicarEfecto(Unidad unidad);
    public abstract void recibirDanio(int danio);
}
