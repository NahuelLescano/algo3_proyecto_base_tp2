package edu.fiuba.algo3.modelo;

public interface Terreno {
    void recibir(Unidad unidad, Destino casilla);
    Terreno recibirImpacto(int danio);
    void recibirEmpuje(Unidad empujada, Destino destino);
    /*
    void afectarMovimiento(Unidad unidad);
    void recibirImpacto(int danio);
    */
    /*public abstract boolean esTransitable();
    public abstract boolean bloqueaDisparos();
    public abstract void aplicarEfecto(Unidad unidad);
    public abstract void recibirDanio(int danio);*/
}
