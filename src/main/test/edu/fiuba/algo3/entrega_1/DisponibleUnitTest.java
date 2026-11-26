package edu.fiuba.algo3.entrega_1;

import edu.fiuba.algo3.modelo.casilla.Casilla;
import edu.fiuba.algo3.modelo.excepciones.AccionInvalidaException;
import edu.fiuba.algo3.modelo.tablero.Tablero;
import edu.fiuba.algo3.modelo.unidad.Mech;
import edu.fiuba.algo3.modelo.unidad.turno.Agotado;
import edu.fiuba.algo3.modelo.unidad.turno.ControladorDeshacer;
import edu.fiuba.algo3.modelo.unidad.turno.Disponible;
import edu.fiuba.algo3.modelo.unidad.turno.MementoMech;
import edu.fiuba.algo3.modelo.unidad.turno.Movido;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class DisponibleUnitTest {

    @Test
    public void test01MechDisponiblePuedeMoverseYQuedaMovido() {
        Disponible estado = new Disponible();

        Mech mech = mock(Mech.class);
        Casilla destino = mock(Casilla.class);
        Tablero tablero = mock(Tablero.class);
        ControladorDeshacer controlador = mock(ControladorDeshacer.class);
        MementoMech memento = mock(MementoMech.class);

        when(mech.crearMemento()).thenReturn(memento);

        estado.mover(mech, destino, tablero, controlador);

        verify(mech).crearMemento();
        verify(mech).ejecutarDesplazamiento(destino, tablero);
        verify(controlador).registrarMovimiento(mech, memento);
        verify(mech).cambiarEstadoTurno(any(Movido.class));
    }

    @Test
    public void test02MechDisponiblePuedeAtacarYQuedaAgotado() {
        Disponible estado = new Disponible();

        Mech mech = mock(Mech.class);
        Casilla objetivo = mock(Casilla.class);
        Tablero tablero = mock(Tablero.class);
        ControladorDeshacer controlador = mock(ControladorDeshacer.class);

        estado.atacar(mech, objetivo, tablero, controlador);

        verify(mech).iniciarAtaque(objetivo, tablero);
        verify(mech).cambiarEstadoTurno(any(Agotado.class));
        verifyNoInteractions(controlador);
    }

    @Test
    public void test03MechDisponibleNoPuedeDeshacerSiNoSeMovio() {
        Disponible estado = new Disponible();

        Mech mech = mock(Mech.class);
        Tablero tablero = mock(Tablero.class);

        assertThrows(AccionInvalidaException.class,
            () -> estado.deshacerMovimiento(mech, tablero));

        verifyNoInteractions(mech, tablero);
    }
}
