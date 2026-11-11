package edu.fiuba.algo3.entrega_1;

import edu.fiuba.algo3.modelo.Tablero;
import edu.fiuba.algo3.modelo.Posicion;
import edu.fiuba.algo3.modelo.Luciernaga;
import edu.fiuba.algo3.modelo.Canon;
import edu.fiuba.algo3.modelo.Direccion;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CanionTest {

    @Test
    void canionImpactaPrimeraUnidadYNoAlcanzaALaSegunda() {

        String[] mapa = {
            "-------",
            "-------",
            "-------",
            "-------",
            "-------"
        };

        Tablero tablero = new Tablero(mapa);
        Canion canon = new Canion();

        // Ubicamos dos unidades en la misma línea de disparo.
        Luciernaga primeraUnidad = new Luciernaga(new Posicion(2, 2));
        Luciernaga segundaUnidad = new Luciernaga(new Posicion(2, 5));

        assertTrue(tablero.colocarUnidad(primeraUnidad));
        assertTrue(tablero.colocarUnidad(segundaUnidad));

        int vidaInicialPrimera = primeraUnidad.getVidaActual();
        int vidaInicialSegunda = segundaUnidad.getVidaActual();

        // El Cañón dispara hacia el este.
        canon.atacar(tablero, new Posicion(2, 0), Direccion.ESTE);

        // La primera unidad recibe 1 de daño.
        assertEquals(vidaInicialPrimera - 1, primeraUnidad.getVidaActual());

        // La primera unidad es empujada una casilla hacia el este.
        assertEquals(new Posicion(2, 3), primeraUnidad.getPosicion());

        // La segunda unidad no recibe daño.
        assertEquals(vidaInicialSegunda, segundaUnidad.getVidaActual());

        // La segunda unidad permanece en su posición.
        assertEquals(new Posicion(2, 5), segundaUnidad.getPosicion());
    }


    @Test
    void canionSeDetieneAlEncontrarUnaMontania() {

        String[] mapa = {
            "-------",
            "-------",
            "--M----",
            "-------",
            "-------"
        };

        Tablero tablero = new Tablero(mapa);
        Canion canon = new Canion();

        // Ubicamos una unidad detrás de la montaña.
        Luciernaga unidad = new Luciernaga(new Posicion(2, 4));

        assertTrue(tablero.colocarUnidad(unidad));

        int vidaInicial = unidad.getVidaActual();

        // El Cañón dispara hacia el este.
        canon.atacar(tablero, new Posicion(2, 0), Direccion.ESTE);

        // La montaña bloquea los disparos.
        assertTrue(tablero.bloqueaDisparos(new Posicion(2, 2)));

        // La unidad detrás de la montaña no recibe daño.
        assertEquals(vidaInicial, unidad.getVidaActual());

        // La unidad permanece en su posición.
        assertEquals(new Posicion(2, 4), unidad.getPosicion());
    }
}
