package edu.fiuba.algo3.modelo;

public interface EstadoCasilla {

    EstadoCasilla ocupar(Unidad unidad);

    EstadoCasilla desocupar();

    boolean estaOcupada();

    boolean estaOcupadaPor(Unidad unidad);

    Unidad obtenerUnidad();

    EstadoCasilla aplicarDanio(int danio);

    EstadoCasilla aplicarEfectoDelTerreno(Terreno terreno);

    boolean detieneDisparo();
}
