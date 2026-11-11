package edu.fiuba.algo3.modelo;

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

    void desocupar() {
        this.unidad = null;
    }

    Terreno getTerreno() {
        return terreno;
    } 

    Unidad getUnidad() {
        return unidad;
    }

    public Posicion getPosicion() {
        return posicion;
    }
    
    boolean estaOcupada() {
        return unidad != null;
    }
    
    void reemplazarTerreno(Terreno nuevoTerreno) {
        this.terreno = nuevoTerreno;
    }
}
