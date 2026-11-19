package edu.fiuba.algo3.modelo.tablero;

import edu.fiuba.algo3.modelo.terreno.Terreno;
import edu.fiuba.algo3.modelo.unidad.Unidad;

public class Casilla {
    private Terreno terreno;
    private Unidad unidad;
    private final Posicion posicion;

    public Casilla(Terreno terreno, Posicion posicion) {
        this.terreno = terreno;
        this.posicion = posicion;
        this.unidad = null;
    }

    void ocupar(Unidad unidad) {
        this.unidad = unidad;
    }

    public void desocupar() {
        this.unidad = null;
    }

    public Terreno getTerreno() {
        return terreno;
    }

    public Unidad getUnidad() {
        return unidad;
    }

    public Posicion getPosicion() {
        return posicion;
    }

    public boolean estaOcupada() {
        return unidad != null;
    }

    public void reemplazarTerreno(Terreno nuevoTerreno) {
        this.terreno = nuevoTerreno;
    }
}
