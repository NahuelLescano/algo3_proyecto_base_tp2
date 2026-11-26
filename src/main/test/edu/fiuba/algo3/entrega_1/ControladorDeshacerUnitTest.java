package edu.fiuba.algo3.entrega_1;

import edu.fiuba.algo3.modelo.excepciones.AccionInvalidaException;
import edu.fiuba.algo3.modelo.tablero.Tablero;
import edu.fiuba.algo3.modelo.unidad.Mech;
import edu.fiuba.algo3.modelo.unidad.turno.ControladorDeshacer;
import edu.fiuba.algo3.modelo.unidad.turno.MementoMech;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class ControladorDeshacerUnitTest {

    @Test
    public void test01NoSePuedeDeshacerSiNoHayMovimientoRegistrado() {
        ControladorDeshacer controlador = new ControladorDeshacer();
        Tablero tablero = mock(Tablero.class);

        assertThrows(AccionInvalidaException.class,
            () -> controlador.deshacerUltimoMovimiento(tablero));

        verifyNoInteractions(tablero);
    }

    @Test
    public void test02SePuedeDeshacerUnMovimientoRegistrado() {
        ControladorDeshacer controlador = new ControladorDeshacer();

        Mech mech = mock(Mech.class);
        MementoMech memento = mock(MementoMech.class);
        Tablero tablero = mock(Tablero.class);

        controlador.registrarMovimiento(mech, memento);

        controlador.deshacerUltimoMovimiento(tablero);

        verify(mech).deshacerMovimiento(tablero);
    }

    @Test
    public void test03NoSePuedeDeshacerDosVecesEnElMismoTurno() {
        ControladorDeshacer controlador = new ControladorDeshacer();

        Mech mech = mock(Mech.class);
        MementoMech memento = mock(MementoMech.class);
        Tablero tablero = mock(Tablero.class);

        controlador.registrarMovimiento(mech, memento);

        controlador.deshacerUltimoMovimiento(tablero);

        assertThrows(AccionInvalidaException.class,
            () -> controlador.deshacerUltimoMovimiento(tablero));

        verify(mech, times(1)).deshacerMovimiento(tablero);
    }

    @Test
    public void test04AtacarConElMismoMechInvalidaElDeshacer() {
        ControladorDeshacer controlador = new ControladorDeshacer();

        Mech mech = mock(Mech.class);
        MementoMech memento = mock(MementoMech.class);
        Tablero tablero = mock(Tablero.class);

        controlador.registrarMovimiento(mech, memento);
        controlador.invalidarSiCorresponde(mech);

        assertThrows(AccionInvalidaException.class,
            () -> controlador.deshacerUltimoMovimiento(tablero));

        verifyNoInteractions(tablero);
        verifyNoInteractions(mech);
    }

    @Test
    public void test05AtacarConOtroMechNoInvalidaElDeshacer() {
        ControladorDeshacer controlador = new ControladorDeshacer();

        Mech mechQueSeMovio = mock(Mech.class);
        Mech mechQueAtaco = mock(Mech.class);
        MementoMech memento = mock(MementoMech.class);
        Tablero tablero = mock(Tablero.class);

        controlador.registrarMovimiento(mechQueSeMovio, memento);
        controlador.invalidarSiCorresponde(mechQueAtaco);

        controlador.deshacerUltimoMovimiento(tablero);

        verify(mechQueSeMovio).deshacerMovimiento(tablero);
        verifyNoInteractions(mechQueAtaco);
    }

    @Test
    public void test06ReiniciarTurnoPermiteDeshacerNuevamente() {
        ControladorDeshacer controlador = new ControladorDeshacer();

        Mech primerMech = mock(Mech.class);
        Mech segundoMech = mock(Mech.class);

        MementoMech primerMemento = mock(MementoMech.class);
        MementoMech segundoMemento = mock(MementoMech.class);

        Tablero tablero = mock(Tablero.class);

        controlador.registrarMovimiento(primerMech, primerMemento);
        controlador.deshacerUltimoMovimiento(tablero);

        controlador.reiniciarTurno();

        controlador.registrarMovimiento(segundoMech, segundoMemento);
        controlador.deshacerUltimoMovimiento(tablero);

        verify(primerMech).deshacerMovimiento(tablero);
        verify(segundoMech).deshacerMovimiento(tablero);
    }
}
