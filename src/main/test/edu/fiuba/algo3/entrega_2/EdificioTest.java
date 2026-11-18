package edu.fiuba.algo3.entrega_2;

import edu.fiuba.algo3.modelo.Tablero;
import edu.fiuba.algo3.modelo.Posicion;
import edu.fiuba.algo3.modelo.Suelo;
import edu.fiuba.algo3.modelo.Edificio;
import edu.fiuba.algo3.modelo.Juego;
import edu.fiuba.algo3.modelo.RedElectrica;
import edu.fiuba.algo3.modelo.Mech;
import edu.fiuba.algo3.modelo.Artilleria;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

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
    
    @Test
    public void daniarEdificioReduceEnergiaDeLaRedElectrica() {
        String[] mapa = {"--E--"};
        Tablero tablero = new Tablero(mapa);
        RedElectrica redElectrica = new RedElectrica(5);
        Juego juego = new Juego(tablero, redElectrica);

        Mech mech = new Mech(3, new Posicion(0, 0), new Artilleria());
        tablero.colocarUnidad(mech);

        Posicion objetivo = new Posicion(0, 2);

        assertTrue(juego.iniciarAtaque(mech, objetivo));
        assertEquals(4, redElectrica.getEnergia());
    }

}
