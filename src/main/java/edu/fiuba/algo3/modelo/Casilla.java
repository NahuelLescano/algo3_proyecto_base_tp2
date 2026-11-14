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
    
    public void recibirDanio(int danio) {
        if (unidad == null) {
            terreno.recibirDanio(danio);
            return;
        }

        unidad.recibirDanio(danio);

        if (!unidad.estaViva()) {
            desocupar();
        }
    }
    
    public void aplicarEfectoDelTerreno() {
        if (unidad == null) {
            return;
        }

        terreno.aplicarEfecto(unidad);

        if (!unidad.estaViva()) {
            desocupar();
        }
    }
    
    public boolean bloqueaDisparos() {
        return terreno.bloqueaDisparos();
    }
    
    public boolean puedeRecibirUnidad(Unidad unidad) {
        return !estaOcupada() && terreno.esTransitable();
    }
}
