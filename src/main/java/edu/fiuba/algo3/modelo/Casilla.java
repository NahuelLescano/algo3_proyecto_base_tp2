package edu.fiuba.algo3.modelo;

public class Casilla {
    private Terreno terreno;
    private Unidad unidad;
    private Posicion posicion;


    public Casilla(Terreno terreno, Posicion posicion) {
        this.terreno = terreno;
        this.posicion = posicion;
        this.unidad = null;
    }

    public void ocupar(Unidad unidad) {
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

}
