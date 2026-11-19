package edu.fiuba.algo3.entrega_1;

import edu.fiuba.algo3.modelo.excepciones.AccionInvalidaException;
import edu.fiuba.algo3.modelo.excepciones.AtaqueInvalidoException;
import edu.fiuba.algo3.modelo.excepciones.MovimientoInvalidoException;
import edu.fiuba.algo3.modelo.tablero.ParserTablero;
import edu.fiuba.algo3.modelo.tablero.Posicion;
import edu.fiuba.algo3.modelo.tablero.Tablero;
import edu.fiuba.algo3.modelo.unidad.Mech;
import edu.fiuba.algo3.modelo.unidad.MechFactory;
import edu.fiuba.algo3.modelo.unidad.Vek;
import edu.fiuba.algo3.modelo.unidad.VekFactory;
import edu.fiuba.algo3.modelo.unidad.turno.ControladorDeshacer;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


public class MechTurnoTest {

    private static final Posicion INICIAL = new Posicion(2, 2);
    private static final String[] MAPA = {
            "--------",
            "--------",
            "--------",
            "--------",
            "--------",
            "--------",
            "--------",
            "--------"
    };

    private Tablero tablero;
    private ControladorDeshacer controlador;
    private Mech mech;

    @BeforeEach
    public void setUp() {
        tablero = new Tablero(MAPA, new ParserTablero());
        controlador = new ControladorDeshacer();
        mech = new MechFactory().crearMechDeCombate();
        tablero.colocarUnidad(mech, INICIAL);
    }

    @Test
    public void test01MechNoPuedeMoverseDosVecesEnElMismoTurno() {
        Posicion intermedio = new Posicion(2, 3);
        Posicion destinoFinal = new Posicion(2, 4);

        mech.mover(intermedio, tablero, controlador);
        assertThrows(MovimientoInvalidoException.class,
                () -> mech.mover(destinoFinal, tablero, controlador));

        assertEquals(intermedio, mech.posicion());
    }

    @Test
    public void test02MechNoPuedeAtacarDosVecesEnElMismoTurno() {
        Posicion objetivo = new Posicion(2, 3);
        Vek vek = new VekFactory().crearLuciernaga();
        tablero.colocarUnidad(vek, objetivo);

        mech.atacar(objetivo, tablero, controlador);
        assertThrows(AtaqueInvalidoException.class,
                () -> mech.atacar(objetivo, tablero, controlador));
    }

    @Test
    public void test03SePuedeDeshacerElMovimientoSiElMechTodaviaNoAtaco() {
        Posicion destino = new Posicion(2, 4);

        mech.mover(destino, tablero, controlador);
        mech.deshacerMovimiento(tablero);

        assertEquals(INICIAL, mech.posicion());
    }

    @Test
    public void test04NoSePuedeDeshacerElMovimientoDespuesDeAtacar() {
        Posicion destino = new Posicion(2, 3);

        mech.mover(destino, tablero, controlador);
        mech.atacar(new Posicion(2, 4), tablero, controlador);

        assertThrows(AccionInvalidaException.class,
                () -> mech.deshacerMovimiento(tablero));

        assertEquals(destino, mech.posicion());
    }

    @Test
    public void test05NoSePuedeDeshacerElMovimientoMasDeUnaVezEnElMismoTurno() {
        Posicion destino = new Posicion(2, 4);

        mech.mover(destino, tablero, controlador);
        mech.deshacerMovimiento(tablero);

        assertThrows(AccionInvalidaException.class,
                () -> mech.deshacerMovimiento(tablero));

        assertEquals(INICIAL, mech.posicion());
    }

    @Test
    public void test06DespuesDeDeshacerElMovimientoElMechPuedeVolveAMoverse() {
        Posicion destino = new Posicion(2, 4);
        Posicion nuevoDestino = new Posicion(2, 3);

        mech.mover(destino, tablero, controlador);
        mech.deshacerMovimiento(tablero);

        mech.mover(nuevoDestino, tablero, controlador);
        assertEquals(nuevoDestino, mech.posicion());
    }
}
