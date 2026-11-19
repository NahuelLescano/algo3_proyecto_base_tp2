package edu.fiuba.algo3.entrega_1;

import edu.fiuba.algo3.modelo.*;
import edu.fiuba.algo3.modelo.exceptions.AtaqueInvalidoException;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TestArtilleria {

    private Tablero tableroVacio() {
        String[] mapa = {
                "-------",
                "-------",
                "-------",
                "-------",
                "-------",
                "-------",
                "-------"
        };
        return new Tablero(mapa, new ParserTablero());
    }

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
        Tablero tablero = new Tablero(mapa, new ParserTablero());
        Mech mech = new MechFactory().crearMechArtillero();
        Vek objetivo = new Vek(3);

        tablero.colocarUnidad(mech, new Posicion(3, 0));
        tablero.colocarUnidad(objetivo, new Posicion(3, 4));
        int vidaInicial = objetivo.getVidaActual();

        mech.atacar(tablero, new Posicion(3, 4));

        assertEquals(vidaInicial - 1, objetivo.getVidaActual());
    }

    @Test
    void artilleriaNoPuedeAtacarCasillasAdyacentes() {
        Tablero tablero = tableroVacio();
        Mech mech = new MechFactory().crearMechArtillero();
        Unidad adyacente = new Saltador();

        tablero.colocarUnidad(mech, new Posicion(3, 0));
        tablero.colocarUnidad(adyacente, new Posicion(3, 1));
        int vidaInicial = adyacente.getVidaActual();

        assertThrows(AtaqueInvalidoException.class,
                () -> mech.atacar(tablero, new Posicion(3, 1)));

        assertEquals(vidaInicial, adyacente.getVidaActual());
        assertEquals(new Posicion(3, 1), adyacente.posicion());
    }

    @Test
    void artilleriaDaniaElObjetivoYEmpujaALasCuatroCasillasAdyacentes() {
        Tablero tablero = tableroVacio();
        Mech mech = new MechFactory().crearMechArtillero();

        Luciernaga centro = new Luciernaga();
        Luciernaga norte  = new Luciernaga();
        Luciernaga sur    = new Luciernaga();
        Luciernaga este   = new Luciernaga();
        Luciernaga oeste  = new Luciernaga();

        tablero.colocarUnidad(mech,   new Posicion(3, 0));
        tablero.colocarUnidad(centro, new Posicion(3, 3));
        tablero.colocarUnidad(norte,  new Posicion(2, 3));
        tablero.colocarUnidad(sur,    new Posicion(4, 3));
        tablero.colocarUnidad(este,   new Posicion(3, 4));
        tablero.colocarUnidad(oeste,  new Posicion(3, 2));

        int vidaCentro = centro.vida();
        int vidaNorte = norte.vida();
        int vidaSur = sur.vida();
        int vidaEste = este.vida();
        int vidaOeste = oeste.vida();

        mech.atacar(tablero, new Posicion(3, 3));

        assertEquals(vidaCentro - 1, centro.vida());
        assertEquals(new Posicion(3, 3), centro.posicion());

        assertEquals(new Posicion(1, 3), norte.posicion());
        assertEquals(new Posicion(5, 3), sur.posicion());
        assertEquals(new Posicion(3, 5), este.posicion());
        assertEquals(new Posicion(3, 1), oeste.posicion());

        assertEquals(vidaNorte, norte.vida());
        assertEquals(vidaSur, sur.vida());
        assertEquals(vidaEste, este.vida());
        assertEquals(vidaOeste, oeste.vida());
    }
}