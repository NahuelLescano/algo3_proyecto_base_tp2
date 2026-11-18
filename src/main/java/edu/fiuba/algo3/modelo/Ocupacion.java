package edu.fiuba.algo3.modelo;

import java.util.Optional;

public interface Ocupacion {

    Ocupacion ocupar(Unidad unidad);

    Ocupacion desocupar();

    Ocupacion aplicarDanio(int danio, Terreno terreno);

    Ocupacion aplicarEfectoDelTerreno(Terreno terreno);

    Optional<Unidad> obtenerUnidad();

    boolean estaOcupada();

    boolean detieneDisparo();
}
