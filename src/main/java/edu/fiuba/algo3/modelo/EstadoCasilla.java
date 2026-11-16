package edu.fiuba.algo3.modelo;

public interface EstadoCasilla {

    EstadoCasilla ocupar(Unidad unidad);

    EstadoCasilla desocupar();

    EstadoCasilla aplicarDanio(int danio, Terreno terreno);

    EstadoCasilla aplicarEfectoDelTerreno(Terreno terreno);

    Unidad obtenerUnidad();

    boolean estaOcupada();

    boolean detieneDisparo();
}
