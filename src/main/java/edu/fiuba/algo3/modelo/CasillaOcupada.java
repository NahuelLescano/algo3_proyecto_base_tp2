package edu.fiuba.algo3.modelo;

public class CasillaOcupada implements EstadoCasilla {

    private final Unidad unidad;

    public CasillaOcupada(Unidad unidad) {
        this.unidad = unidad;
    }

    @Override
    public EstadoCasilla ocupar(Unidad unidad) {
        return null;
    }

    @Override
    public EstadoCasilla desocupar() {
        return new CasillaVacia();
    }

    @Override
    public EstadoCasilla aplicarDanio(int danio, Terreno terreno) {
        unidad.recibirDanio(danio);

        if (!unidad.estaViva()) {
            return new CasillaVacia();
        }

        return this;
    }

    @Override
    public EstadoCasilla aplicarEfectoDelTerreno(Terreno terreno) {
        terreno.aplicarEfecto(unidad);

        if (!unidad.estaViva()) {
            return new CasillaVacia();
        }

        return this;
    }

    @Override
    public Unidad obtenerUnidad() {
        return unidad;
    }

    @Override
    public boolean estaOcupada() {
        return true;
    }

    @Override
    public boolean detieneDisparo() {
        return true;
    }
}
