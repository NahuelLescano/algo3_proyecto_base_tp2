package edu.fiuba.algo3.entrega_1;

import edu.fiuba.algo3.modelo.movimiento.MechCombate;
import edu.fiuba.algo3.modelo.movimiento.Posicion;
import edu.fiuba.algo3.modelo.movimiento.Tablero;
import edu.fiuba.algo3.modelo.excepciones.AccionInvalidaException;
import edu.fiuba.algo3.modelo.excepciones.AccionYaRealizadaException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class MechTurnoTest {

    private static final Posicion INICIAL = new Posicion(2, 2);

    private Tablero tablero;
    private MechCombate mech;

    @BeforeEach
    public void setUp() {
        tablero = new Tablero();
        mech = new MechCombate();
        tablero.colocarUnidad(INICIAL, mech);
    }

    @Test
    public void test01MechNoPuedeMoverseDosVecesEnElMismoTurno() {
        Posicion intermedio = new Posicion(2, 3);
        Posicion destinoFinal = new Posicion(2, 4);

        tablero.moverUnidad(INICIAL, intermedio);

        assertThrows(AccionYaRealizadaException.class, () -> {
            tablero.moverUnidad(intermedio, destinoFinal);
        });

        assertSame(mech, tablero.obtenerUnidad(intermedio));
    }

    @Test
    public void test02MechNoPuedeAtacarDosVecesEnElMismoTurno() {
        Posicion objetivo = new Posicion(2, 3);

        mech.atacar(INICIAL, objetivo, tablero);

        assertThrows(AccionInvalidaException.class, () -> {
            mech.atacar(INICIAL, objetivo, tablero);
        });
    }

    @Test
    public void test03SePuedeDeshacerElMovimientoSiElMechTodaviaNoAtaco() {
        Posicion destino = new Posicion(2, 4);

        tablero.moverUnidad(INICIAL, destino);
        mech.deshacerMovimiento(tablero);

        assertSame(mech, tablero.obtenerUnidad(INICIAL));
        assertNull(tablero.obtenerUnidad(destino));
    }

    @Test
    public void test04NoSePuedeDeshacerElMovimientoDespuesDeAtacar() {
        Posicion destino = new Posicion(2, 3);

        tablero.moverUnidad(INICIAL, destino);
        mech.atacar(destino, new Posicion(2, 4), tablero);

        assertThrows(AccionInvalidaException.class, () -> {
            mech.deshacerMovimiento(tablero);
        });

        assertSame(mech, tablero.obtenerUnidad(destino));
    }

    @Test
    public void test05NoSePuedeDeshacerElMovimientoMasDeUnaVezEnElMismoTurno() {
        Posicion destino = new Posicion(2, 4);

        tablero.moverUnidad(INICIAL, destino);
        mech.deshacerMovimiento(tablero);

        assertThrows(AccionInvalidaException.class, () -> {
            mech.deshacerMovimiento(tablero);
        });

        assertSame(mech, tablero.obtenerUnidad(INICIAL));
    }

    @Test
    public void test06DespuesDeDeshacerElMovimientoElMechPuedeVolveAMoverse() {
        Posicion destino = new Posicion(2, 4);
        Posicion nuevoDestino = new Posicion(2, 3);

        tablero.moverUnidad(INICIAL, destino);
        mech.deshacerMovimiento(tablero);

        assertDoesNotThrow(() -> tablero.moverUnidad(INICIAL, nuevoDestino));
        assertSame(mech, tablero.obtenerUnidad(nuevoDestino));
    }
}
