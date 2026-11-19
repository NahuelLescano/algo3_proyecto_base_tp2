package edu.fiuba.algo3.modelo;

import edu.fiuba.algo3.modelo.terreno.Terreno;
import edu.fiuba.algo3.modelo.unidad.Unidad;

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
