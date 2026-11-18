package edu.fiuba.algo3.modelo;

import edu.fiuba.algo3.modelo.exceptions.*;
public class CasillaOcupada implements EstadoCasilla {

    private final Unidad unidad;

    public CasillaOcupada(Unidad unidad) {
        this.unidad = unidad;
    }

    @Override
    public void ocupar(Unidad unidad, Casilla casilla) {
        throw new CasillaOcupadaException("La casilla ya está ocupada.");
    }

    @Override 
    public void recibirImpacto(int danio, Casilla casilla){
        unidad.recibirDanio(danio);
    }
/*
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
 */
    }
