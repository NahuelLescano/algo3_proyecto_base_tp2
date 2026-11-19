package edu.fiuba.algo3.entrega_1;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import edu.fiuba.algo3.modelo.excepciones.AccionYaRealizadaException;
import edu.fiuba.algo3.modelo.tablero.Posicion;
import edu.fiuba.algo3.modelo.tablero.Tablero;
import edu.fiuba.algo3.modelo.unidad.MechDeCombate;

public class MechTurnoTest {

    private static final Posicion INICIAL = new Posicion(2, 2);
    private static final String[] MAPA = {
            "-------",
            "-------",
            "-------",
            "-------",
            "-------"
    };

    private Tablero tablero;
    private MechDeCombate mech;

    @BeforeEach
    public void setUp() {
        tablero = new Tablero(MAPA);
        mech = new MechDeCombate(INICIAL);
        assertTrue(tablero.colocarUnidad(mech));
    }

    @Test
    public void test01MechNoPuedeMoverseDosVecesEnElMismoTurno() {
        Posicion intermedio = new Posicion(2, 3);
        Posicion destinoFinal = new Posicion(2, 4);

        assertTrue(tablero.moverUnidad(mech, intermedio));
        assertFalse(tablero.moverUnidad(mech, destinoFinal));

        assertSame(mech, tablero.obtenerUnidad(intermedio));
    }

    @Test
    public void test02MechNoPuedeAtacarDosVecesEnElMismoTurno() {
        Posicion objetivo = new Posicion(2, 3);

        assertTrue(mech.iniciarAtaque(tablero, objetivo));
        assertFalse(mech.iniciarAtaque(tablero, objetivo));
    }

    @Test
    public void test03SePuedeDeshacerElMovimientoSiElMechTodaviaNoAtaco() {
        Posicion destino = new Posicion(2, 4);

        assertTrue(tablero.moverUnidad(mech, destino));
        mech.deshacerMovimiento(tablero);

        assertSame(mech, tablero.obtenerUnidad(INICIAL));
        assertNull(tablero.obtenerUnidad(destino));
    }

    @Test
    public void test04NoSePuedeDeshacerElMovimientoDespuesDeAtacar() {
        Posicion destino = new Posicion(2, 3);

        assertTrue(tablero.moverUnidad(mech, destino));
        assertTrue(mech.iniciarAtaque(tablero, new Posicion(2, 4)));

        assertThrows(AccionYaRealizadaException.class, () -> {
            mech.deshacerMovimiento(tablero);
        });

        assertSame(mech, tablero.obtenerUnidad(destino));
    }

    @Test
    public void test05NoSePuedeDeshacerElMovimientoMasDeUnaVezEnElMismoTurno() {
        Posicion destino = new Posicion(2, 4);

        assertTrue(tablero.moverUnidad(mech, destino));
        mech.deshacerMovimiento(tablero);

        assertThrows(AccionYaRealizadaException.class, () -> {
            mech.deshacerMovimiento(tablero);
        });

        assertSame(mech, tablero.obtenerUnidad(INICIAL));
    }

    @Test
    public void test06DespuesDeDeshacerElMovimientoElMechPuedeVolveAMoverse() {
        Posicion destino = new Posicion(2, 4);
        Posicion nuevoDestino = new Posicion(2, 3);

        assertTrue(tablero.moverUnidad(mech, destino));
        mech.deshacerMovimiento(tablero);

        assertTrue(tablero.moverUnidad(mech, nuevoDestino));
        assertSame(mech, tablero.obtenerUnidad(nuevoDestino));
    }
}
