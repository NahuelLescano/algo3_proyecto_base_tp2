package edu.fiuba.algo3.entrega_1;

import edu.fiuba.algo3.modelo.*;
import edu.fiuba.algo3.modelo.unidad.Mech;
import edu.fiuba.algo3.modelo.unidad.MechFactory;
import edu.fiuba.algo3.modelo.unidad.Vek;
import edu.fiuba.algo3.modelo.unidad.VekFactory;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TestArtilleria {

    private Tablero tableroVacio() {
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
        return new Tablero(mapa, new ParserTablero());
    }

    @Test
    void artilleriaAtacaPorEncimaDeObstaculos() {
        String[] mapa = {
                "--------",
                "--------",
                "--------",
                "--M-----",
                "--------",
                "--------",
                "--------",
                "--------"
        };
        Tablero tablero = new Tablero(mapa, new ParserTablero());
        MechFactory mechFactory = new MechFactory();
        VekFactory vekFactory = new VekFactory();

        Mech mech = mechFactory.crearMechArtillero();
        Vek objetivo = vekFactory.crearLuciernaga();

        tablero.colocarUnidad(mech, new Posicion(3, 0));
        tablero.colocarUnidad(objetivo, new Posicion(3, 4));
        int vidaInicial = objetivo.getVidaActual();

        mech.iniciarAtaque(tablero.buscarCasilla(new Posicion(3, 4)), tablero);

        assertEquals(vidaInicial - 1, objetivo.getVidaActual());
    }

    @Test
    void artilleriaDaniaElObjetivoYEmpujaALasCuatroCasillasAdyacentes() {
        Tablero tablero = tableroVacio();
        MechFactory mechFactory = new MechFactory();
        VekFactory vekFactory = new VekFactory();

        Mech mech = mechFactory.crearMechArtillero();

        Vek centro = vekFactory.crearLuciernaga();
        Vek norte  = vekFactory.crearLuciernaga();
        Vek sur    = vekFactory.crearLuciernaga();
        Vek este   = vekFactory.crearLuciernaga();
        Vek oeste  = vekFactory.crearLuciernaga();

        tablero.colocarUnidad(mech,   new Posicion(3, 0));
        tablero.colocarUnidad(centro, new Posicion(3, 3));
        tablero.colocarUnidad(norte,  new Posicion(2, 3));
        tablero.colocarUnidad(sur,    new Posicion(4, 3));
        tablero.colocarUnidad(este,   new Posicion(3, 4));
        tablero.colocarUnidad(oeste,  new Posicion(3, 2));

        int vidaCentro = centro.getVidaActual();
        int vidaNorte  = norte.getVidaActual();
        int vidaSur    = sur.getVidaActual();
        int vidaEste   = este.getVidaActual();
        int vidaOeste  = oeste.getVidaActual();

        mech.iniciarAtaque(tablero.buscarCasilla(new Posicion(3, 3)), tablero);

        assertEquals(vidaCentro - 1, centro.getVidaActual());
        assertEquals(new Posicion(3, 3), centro.posicion());

        assertEquals(new Posicion(1, 3), norte.posicion());
        assertEquals(new Posicion(5, 3), sur.posicion());
        assertEquals(new Posicion(3, 5), este.posicion());
        assertEquals(new Posicion(3, 1), oeste.posicion());

        assertEquals(vidaNorte, norte.getVidaActual());
        assertEquals(vidaSur, sur.getVidaActual());
        assertEquals(vidaEste, este.getVidaActual());
        assertEquals(vidaOeste, oeste.getVidaActual());
    }
}