package edu.fiuba.algo3.entrega_2;

import edu.fiuba.algo3.modelo.Tablero;
import edu.fiuba.algo3.modelo.Posicion;
import edu.fiuba.algo3.modelo.Suelo;
import edu.fiuba.algo3.modelo.Edificio;
import edu.fiuba.algo3.modelo.Juego;
import edu.fiuba.algo3.modelo.RedElectrica;
import edu.fiuba.algo3.modelo.Mech;
import edu.fiuba.algo3.modelo.Artilleria;
import edu.fiuba.algo3.modelo.Punio;

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
    
    // Daño directo a un Edificio
    @Test
    public void daniarEdificioReduceEnergiaDeLaRedElectrica() {
        String[] mapa = {"--E--"};
        Tablero tablero = new Tablero(mapa);
        RedElectrica redElectrica = new RedElectrica(5);
        Juego juego = new Juego(tablero, redElectrica);

        Mech mech = new Mech(3, new Posicion(0, 0), new Artilleria());
        assertTrue(tablero.colocarUnidad(mech));

        Posicion objetivo = new Posicion(0, 2);

        assertTrue(juego.iniciarAtaque(mech, objetivo));
        assertEquals(4, redElectrica.getEnergia());
    }
    
    // Compruebo que Artillería podría dañar 5 edificios en un mismo ataque 
    @Test
    public void ataqueDeArtilleriaReduceLaRedElectricaPorTodosLosEdificiosDanados() {

        String[] mapa = {
                "-------",
                "---E---",
                "-------",
                "-E-E-E-",
                "-------",
                "---E---",
                "-------"
        };

        Tablero tablero = new Tablero(mapa);

        RedElectrica redElectrica = new RedElectrica(5);
        Juego juego = new Juego(tablero, redElectrica);

        // Mech atacante equipado con Artillería
        Mech atacante = new Mech(3, new Posicion(3, 0), new Artilleria());

        // Creo las unidades que serán empujadas
        Mech unidadNorte = new Mech(
                3, new Posicion(4, 3), new Punio()
        );

        Mech unidadSur = new Mech(
                3, new Posicion(2, 3), new Punio()
        );

        Mech unidadEste = new Mech(
                3, new Posicion(3, 4), new Punio()
        );

        Mech unidadOeste = new Mech(
                3, new Posicion(3, 2), new Punio()
        );

        // Coloco dichas unidades
        assertTrue(tablero.colocarUnidad(atacante));
        assertTrue(tablero.colocarUnidad(unidadNorte));
        assertTrue(tablero.colocarUnidad(unidadSur));
        assertTrue(tablero.colocarUnidad(unidadEste));
        assertTrue(tablero.colocarUnidad(unidadOeste));

        // Ejecución del ataque
        Posicion objetivo = new Posicion(3, 3);

        assertTrue(juego.iniciarAtaque(atacante, objetivo));

        assertEquals(0, redElectrica.getEnergia());
    }
    
    // Daño a un edificio por colisión durante un empuje
    @Test
    public void empujarUnidadContraEdificioReduceLaRedElectrica() {

        String[] mapa = {
                "-----",
                "-----",
                "-----",
                "-----",
                "--E--"
        };

        Tablero tablero = new Tablero(mapa);
        RedElectrica redElectrica = new RedElectrica(5);
        Juego juego = new Juego(tablero, redElectrica);

        Mech atacante = new Mech(3, new Posicion(2, 0), new Artilleria());

        Mech unidadEmpujada = new Mech(
                3, new Posicion(3, 2), new Punio()
        );

        assertTrue(tablero.colocarUnidad(atacante));
        assertTrue(tablero.colocarUnidad(unidadEmpujada));

        Posicion objetivo = new Posicion(2, 2);

        assertTrue(juego.iniciarAtaque(atacante, objetivo));

        assertEquals(4, redElectrica.getEnergia());
    }

}
