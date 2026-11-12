package edu.fiuba.algo3.modelo.movimiento;

public abstract class Terreno {
    public abstract boolean esTransitable();
    public abstract boolean transitablePara(Locomocion l);
    public abstract void aplicarEfecto(Unidad u);
    public abstract void recibirDanio(int puntos, Casilla casilla);
    public abstract void validarPuedeAlojar();
    public abstract boolean bloqueaDisparos();

    public void recibirImpactoEmpuje(Casilla destino, Casilla origen, Unidad unidadEmpujada) {
        destino.alojarEmpujada(origen, unidadEmpujada);
    }
}
