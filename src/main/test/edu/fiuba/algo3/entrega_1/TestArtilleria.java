package edu.fiuba.algo3.entrega_1;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import edu.fiuba.algo3.modelo.unidad.Mech;
import edu.fiuba.algo3.modelo.unidad.MechFactory;
import edu.fiuba.algo3.modelo.tablero.Posicion;
import edu.fiuba.algo3.modelo.tablero.Tablero;
import edu.fiuba.algo3.modelo.unidad.Luciernaga;

public class TestArtilleria {

    @Test
    public void artilleriaAtacaPorEncimaDeObstaculos() {

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
        MechFactory fabrica = new MechFactory();
        Mech mech = fabrica.crearMechArtillero(new Posicion(3, 0));

        Luciernaga objetivo = new Luciernaga(new Posicion(3, 4));
        assertTrue(tablero.colocarUnidad(mech));
        assertTrue(tablero.colocarUnidad(objetivo));

        int vidaInicial = objetivo.getVidaActual();

        mech.iniciarAtaque(tablero, new Posicion(3, 4));

        assertEquals(vidaInicial - 1, objetivo.getVidaActual());
    }

    @Test
    public void artilleriaNoPuedeAtacarCasillasAdyacentes() {

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
        MechFactory fabrica = new MechFactory();
        Mech mech = fabrica.crearMechArtillero(new Posicion(3, 0));

        Luciernaga adyacente = new Luciernaga(new Posicion(3, 1));
        assertTrue(tablero.colocarUnidad(mech));
        assertTrue(tablero.colocarUnidad(adyacente));

        int vidaInicial = adyacente.getVidaActual();

        mech.iniciarAtaque(tablero, new Posicion(3, 1));

        assertEquals(vidaInicial, adyacente.getVidaActual());
        assertEquals(new Posicion(3, 1), adyacente.getPosicion());
    }

    @Test
    public void artilleriaDaniaElObjetivoYEmpujaALasCuatroCasillasAdyacentes() {

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
        MechFactory fabrica = new MechFactory();
        Mech mech = fabrica.crearMechArtillero(new Posicion(3, 0));

        Luciernaga centro = new Luciernaga(new Posicion(3, 3));
        Luciernaga norte = new Luciernaga(new Posicion(2, 3));
        Luciernaga sur = new Luciernaga(new Posicion(4, 3));
        Luciernaga este = new Luciernaga(new Posicion(3, 4));
        Luciernaga oeste = new Luciernaga(new Posicion(3, 2));

        assertTrue(tablero.colocarUnidad(mech));
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

        mech.iniciarAtaque(tablero, new Posicion(3, 3));

        assertEquals(vidaCentro - 1, centro.getVidaActual());
        assertEquals(new Posicion(3, 3), centro.getPosicion());

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
