package edu.fiuba.algo3.modelo;

public abstract class Terreno {
    public abstract boolean esTransitable();
    public abstract boolean bloqueaDisparos();
    public abstract void aplicarEfecto(Unidad unidad);
    public abstract void recibirDanio(int danio);
}
