package edu.fiuba.algo3.modelo.partida;

import edu.fiuba.algo3.modelo.casilla.Casilla;
import edu.fiuba.algo3.modelo.tablero.Posicion;
import edu.fiuba.algo3.modelo.tablero.Tablero;
import edu.fiuba.algo3.modelo.unidad.Vek;

public class PuntoEmergencia {
    private final Posicion posicion;
    private final Vek vek;

    public PuntoEmergencia(Posicion posicion, Vek vek) {
        this.posicion = posicion;
        this.vek = vek;
    }

    public void resolverEmergencia(Tablero tablero) {
        Casilla casilla = tablero.buscarCasilla(posicion);
        try {
            tablero.colocarUnidad(vek, posicion);
        } catch (Exception e) {
            // La casilla está ocupada: bloquea la emergencia y recibe 1 de daño
            casilla.recibirImpacto(1);
        }
    }

    public Posicion getPosicion() {
        return posicion;
    }

    public Vek getVek() {
        return vek;
    }
}
