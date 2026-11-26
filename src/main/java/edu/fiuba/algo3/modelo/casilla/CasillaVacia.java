package edu.fiuba.algo3.modelo.casilla;

import edu.fiuba.algo3.modelo.tablero.Direccion;
import edu.fiuba.algo3.modelo.tablero.Posicion;
import edu.fiuba.algo3.modelo.tablero.Tablero;
import edu.fiuba.algo3.modelo.unidad.Unidad;
import edu.fiuba.algo3.modelo.arma.Disparo;
import edu.fiuba.algo3.modelo.unidad.Vek;

public class CasillaVacia implements EstadoCasilla {

    public void ocupar(Unidad unidad, CasillaDelTablero casilla) {
        casilla.consultarTerreno(unidad);
    }

    public void recibirEmpuje(Unidad unidad, CasillaDelTablero casilla) {
        casilla.empujarSobreTerreno(unidad);
    }

    public void empujarHacia(Casilla destino) {

    }

    public void recibirImpacto(int destino, CasillaDelTablero casilla) {
        casilla.impactarTerreno(destino);
    }

    @Override
    public Posicion avanzarDisparoEnLinea(CasillaDelTablero casilla, Posicion actual, Direccion direccion, Tablero tablero, Disparo disparo) {
        return casilla.getTerreno().avanzarDisparoEnLinea(actual, direccion, tablero, disparo);
    }


    public void recibirEmergencia(Vek vek, CasillaDelTablero casilla) {
        casilla.ocupar(vek);
    }
}
