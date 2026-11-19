package edu.fiuba.algo3.entrega_1;

import edu.fiuba.algo3.modelo.*;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CanionTest {

    @Test
    void canionImpactaPrimeraUnidadYNoAlcanzaALaSegunda() {
        String[] mapa = {
            "--------",
            "--------",
            "--------",
            "--------",
            "--------",
            "--------",
            "--------",
            "--------"
        };

        Tablero tablero = new Tablero(mapa, new ParserTablero());
        MechFactory mechFactory = new MechFactory();
        VekFactory vekFactory = new VekFactory();

        Mech mech = mechFactory.crearMechCanon();
        Vek primeraUnidad = vekFactory.crearLuciernaga();
        Vek segundaUnidad = vekFactory.crearLuciernaga();

        tablero.colocarUnidad(mech, new Posicion(2, 0));
        tablero.colocarUnidad(primeraUnidad, new Posicion(2, 2));
        tablero.colocarUnidad(segundaUnidad, new Posicion(2, 5));

        int vidaInicialPrimera = primeraUnidad.getVidaActual();
        int vidaInicialSegunda = segundaUnidad.getVidaActual();

        mech.iniciarAtaque(tablero.buscarCasilla(new Posicion(2, 6)), tablero);

        assertEquals(vidaInicialPrimera - 1, primeraUnidad.getVidaActual());
        assertEquals(new Posicion(2, 3), primeraUnidad.posicion());

        assertEquals(vidaInicialSegunda, segundaUnidad.getVidaActual());
        assertEquals(new Posicion(2, 5), segundaUnidad.posicion());
    }

    @Test
    void canionSeDetieneAlEncontrarUnaMontania() {
        String[] mapa = {
            "--------",
            "--------",
            "--M-----",
            "--------",
            "--------",
            "--------",
            "--------",
            "--------"
        };

        Tablero tablero = new Tablero(mapa, new ParserTablero());
        MechFactory mechFactory = new MechFactory();
        VekFactory vekFactory = new VekFactory();

        Mech mech = mechFactory.crearMechCanon();
        Vek unidad = vekFactory.crearLuciernaga();

        tablero.colocarUnidad(mech, new Posicion(2, 0));
        tablero.colocarUnidad(unidad, new Posicion(2, 4));

        int vidaInicial = unidad.getVidaActual();

        mech.iniciarAtaque(tablero.buscarCasilla(new Posicion(2, 6)), tablero);

        assertEquals(vidaInicial, unidad.getVidaActual());
        assertEquals(new Posicion(2, 4), unidad.posicion());
    }
}