package edu.fiuba.algo3.modelo;

public class CasillaVacia implements EstadoCasilla {

    @Override
    public EstadoCasilla ocupar(Unidad unidad) {
        return new CasillaOcupada(unidad);
    }

    @Override
    public EstadoCasilla desocupar() {
        return this;
    }

    @Override
    public EstadoCasilla aplicarDanio(int danio, Terreno terreno) {
        terreno.recibirDanio(danio);
        return this;
    }

    @Override
    public EstadoCasilla aplicarEfectoDelTerreno(Terreno terreno) {
        return this;
    }

    @Override
    public Unidad obtenerUnidad() {
        return null;
    }

    @Override
    public boolean estaOcupada() {
        return false;
    }

    @Override
    public boolean detieneDisparo() {
        return false;
    }
}
