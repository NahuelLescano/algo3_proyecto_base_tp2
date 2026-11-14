package edu.fiuba.algo3.entrega_1;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import edu.fiuba.algo3.modelo.arma.Artilleria;
import edu.fiuba.algo3.modelo.tablero.Posicion;
import edu.fiuba.algo3.modelo.tablero.Tablero;
import edu.fiuba.algo3.modelo.unidad.Luciernaga;

public class TestArtilleria {

    @Test
    void artilleriaAtacaPorEncimaDeObstaculos() {

        String[] mapa = {
                "-------",
                "-------",
                "-------",
                "--M----",
                "-------",
                "-------",
                "-------"
        };

        Tablero tablero = new Tablero(mapa);
        Artilleria artilleria = new Artilleria();

        // Hay una montaña entre el origen y el objetivo
        Luciernaga objetivo = new Luciernaga(new Posicion(3, 4));
        assertTrue(tablero.colocarUnidad(objetivo));

        int vidaInicial = objetivo.getVidaActual();

        artilleria.atacar(tablero, new Posicion(3, 0), new Posicion(3, 4));

        // La montaña no bloquea el disparo: el objetivo recibe daño
        assertEquals(vidaInicial - 1, objetivo.getVidaActual());
    }

    @Test
    void artilleriaNoPuedeAtacarCasillasAdyacentes() {

        String[] mapa = {
                "-------",
                "-------",
                "-------",
                "-------",
                "-------",
                "-------",
                "-------"
        };

        Tablero tablero = new Tablero(mapa);
        Artilleria artilleria = new Artilleria();

        Luciernaga adyacente = new Luciernaga(new Posicion(3, 1));
        assertTrue(tablero.colocarUnidad(adyacente));

        int vidaInicial = adyacente.getVidaActual();

        // Intento atacar la casilla pegada al origen
        artilleria.atacar(tablero, new Posicion(3, 0), new Posicion(3, 1));

        // No pasa nada: ni daño ni empuje
        assertEquals(vidaInicial, adyacente.getVidaActual());
        assertEquals(new Posicion(3, 1), adyacente.getPosicion());
    }

    @Test
    void artilleriaDaniaElObjetivoYEmpujaALasCuatroCasillasAdyacentes() {

        String[] mapa = {
                "-------",
                "-------",
                "-------",
                "-------",
                "-------",
                "-------",
                "-------"
        };

        Tablero tablero = new Tablero(mapa);
        Artilleria artilleria = new Artilleria();

        Luciernaga centro = new Luciernaga(new Posicion(3, 3));
        Luciernaga norte = new Luciernaga(new Posicion(2, 3));
        Luciernaga sur = new Luciernaga(new Posicion(4, 3));
        Luciernaga este = new Luciernaga(new Posicion(3, 4));
        Luciernaga oeste = new Luciernaga(new Posicion(3, 2));

        assertTrue(tablero.colocarUnidad(centro));
        assertTrue(tablero.colocarUnidad(norte));
        assertTrue(tablero.colocarUnidad(sur));
        assertTrue(tablero.colocarUnidad(este));
        assertTrue(tablero.colocarUnidad(oeste));

        int vidaCentro = centro.getVidaActual();
        int vidaNorte = norte.getVidaActual();
        int vidaSur = sur.getVidaActual();
        int vidaEste = este.getVidaActual();
        int vidaOeste = oeste.getVidaActual();

        artilleria.atacar(tablero, new Posicion(3, 0), new Posicion(3, 3));

        // El objetivo recibe daño y no se mueve
        assertEquals(vidaCentro - 1, centro.getVidaActual());
        assertEquals(new Posicion(3, 3), centro.getPosicion());

        // Las unidades adyacentes son empujadas hacia afuera, sin daño
        assertEquals(new Posicion(1, 3), norte.getPosicion());
        assertEquals(new Posicion(5, 3), sur.getPosicion());
        assertEquals(new Posicion(3, 5), este.getPosicion());
        assertEquals(new Posicion(3, 1), oeste.getPosicion());

        assertEquals(vidaNorte, norte.getVidaActual());
        assertEquals(vidaSur, sur.getVidaActual());
        assertEquals(vidaEste, este.getVidaActual());
        assertEquals(vidaOeste, oeste.getVidaActual());
    }
}
