package edu.fiuba.algo3.entrega_2;

import edu.fiuba.algo3.modelo.Tablero;
import edu.fiuba.algo3.modelo.Posicion;
import edu.fiuba.algo3.modelo.Suelo;
import edu.fiuba.algo3.modelo.Edificio;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class EdificioTest {

    @Test
    public void unEdificioSeConvierteEnSueloAlRecibirDanio() {
        String[] mapa = {
            "E"
        };

        Tablero tablero = new Tablero(mapa);
        Posicion posicion = new Posicion(0, 0);

        tablero.daniarEn(posicion, 1);

        assertTrue(tablero.obtenerTerrenoEn(posicion) instanceof Suelo);
    }
    
    @Test
    public void unEdificioNoSeDestruyeSiRecibeCeroDanio() {
        String[] mapa = {
            "E"
        };

        Tablero tablero = new Tablero(mapa);
        Posicion posicion = new Posicion(0, 0);

        tablero.daniarEn(posicion, 0);

        assertTrue(tablero.obtenerTerrenoEn(posicion) instanceof Edificio);
    }
}
