package edu.fiuba.algo3.modelo;

public class CasillaVacia implements EstadoCasilla {

    @Override
    public void ocupar(Unidad unidad, Casilla casilla) {
        casilla.recibirUnidad(unidad);
    }

    public void recibirImpacto(int danio, Casilla casilla){
        casilla.impactarTerreno(danio);
    }

    /*
    @Override
    public EstadoCasilla desocupar() {
        return this;
    }
    
    @Override
    public boolean estaOcupada() {
        return false;
    }
    
    @Override
    public boolean estaOcupadaPor(Unidad unidad) {
        return false;
    }
    
    @Override
    public Unidad obtenerUnidad() {
        return null;
    }

    @Override
    public EstadoCasilla aplicarDanio(int danio, Terreno terreno) {
        terreno.recibirDanio(danio);
        return this;
    }

    @Override
    public EstadoCasilla aplicarEfectoDelTerreno(Terreno terreno) {
        return this;
    }

    @Override
    public boolean detieneDisparo() {
        return false;
    }
 */
    }
