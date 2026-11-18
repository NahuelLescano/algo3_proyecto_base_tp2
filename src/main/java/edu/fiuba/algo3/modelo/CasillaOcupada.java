package edu.fiuba.algo3.modelo;

public class CasillaOcupada implements EstadoCasilla {

    private final Unidad unidad;

    public CasillaOcupada(Unidad unidad) {
        this.unidad = unidad;
    }

    @Override
    public EstadoCasilla ocupar(Unidad unidad) {
        throw new IllegalStateException(
            "No se puede ocupar una casilla que ya está ocupada"
        );
    }

    @Override
    public EstadoCasilla desocupar() {
        return new CasillaVacia();
    }

    @Override
    public boolean estaOcupada() {
        return true;
    }

    @Override
    public boolean estaOcupadaPor(Unidad unidad) {
        return this.unidad == unidad;
    }

    @Override
    public Unidad obtenerUnidad() {
        return unidad;
    }

    @Override
    public EstadoCasilla aplicarDanio(int danio) {
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
    public boolean detieneDisparo() {
        return true;
    }
}
