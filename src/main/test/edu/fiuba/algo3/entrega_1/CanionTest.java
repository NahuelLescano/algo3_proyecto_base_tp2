package edu.fiuba.algo3.entrega_1;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import edu.fiuba.algo3.modelo.Mech;
import edu.fiuba.algo3.modelo.MechFactory;
import edu.fiuba.algo3.modelo.tablero.Posicion;
import edu.fiuba.algo3.modelo.tablero.Tablero;
import edu.fiuba.algo3.modelo.unidad.Luciernaga;

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
        MechFactory fabrica = new MechFactory();

        // Creo un Mech equipado con Cañón
        Mech mech = fabrica.crearMechCanon(new Posicion(2, 0));

        // Ubico las unidades en la misma línea de disparo
        Luciernaga primeraUnidad = new Luciernaga(new Posicion(2, 2));
        Luciernaga segundaUnidad = new Luciernaga(new Posicion(2, 5));

        assertTrue(tablero.colocarUnidad(mech));
        assertTrue(tablero.colocarUnidad(primeraUnidad));
        assertTrue(tablero.colocarUnidad(segundaUnidad));

        int vidaInicialPrimera = primeraUnidad.getVidaActual();
        int vidaInicialSegunda = segundaUnidad.getVidaActual();

        // El Cañón dispara hacia el este, hasta la última columna
        mech.iniciarAtaque(tablero, new Posicion(2, 6));

        assertEquals(vidaInicialPrimera - 1, primeraUnidad.getVidaActual());
        assertEquals(new Posicion(2, 3), primeraUnidad.getPosicion());

        assertEquals(vidaInicialSegunda, segundaUnidad.getVidaActual());
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
        MechFactory fabrica = new MechFactory();

        Mech mech = fabrica.crearMechCanon(new Posicion(2, 0));

        Luciernaga unidad = new Luciernaga(new Posicion(2, 4));

        assertTrue(tablero.colocarUnidad(mech));
        assertTrue(tablero.colocarUnidad(unidad));

        int vidaInicial = unidad.getVidaActual();

        // El Cañón apunta hacia el este
        mech.iniciarAtaque(tablero, new Posicion(2, 6));

        assertTrue(tablero.detieneDisparoEn(new Posicion(2, 2)));

        assertEquals(vidaInicial, unidad.getVidaActual());
        assertEquals(new Posicion(2, 4), unidad.getPosicion());
    }
}
