package edu.fiuba.algo3.modelo;

import edu.fiuba.algo3.modelo.exceptions.*;
public class CasillaOcupada implements EstadoCasilla {

    private Unidad unidad;

    public CasillaOcupada(Unidad unidad) {
        this.unidad = unidad;
    }

    @Override
    public void ocupar(Unidad unidad, CasillaDelTablero casilla) {
        throw new CasillaOcupadaException("La casilla ya está ocupada.");
    }

    @Override 
    public void recibirImpacto(int danio, CasillaDelTablero casilla){
        unidad.recibirDanio(danio);
    }

    public void recibirEmpuje(Unidad empujada, CasillaDelTablero casilla){
        empujada.recibirDanio(1);
        unidad.recibirDanio(1);
    }

    public void empujarHacia(Casilla destino){
        destino.recibirEmpuje(unidad);
    }

    @Override
    public Posicion avanzarDisparoEnLinea(CasillaDelTablero casilla, Posicion actual, Direccion direccion, Tablero tablero, int danio) {
        tablero.daniarEn(actual, danio);
        tablero.empujar(actual, direccion);
        return null;
    }
    }
