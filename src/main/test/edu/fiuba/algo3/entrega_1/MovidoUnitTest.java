package edu.fiuba.algo3.entrega_1;

import edu.fiuba.algo3.modelo.casilla.Casilla;
import edu.fiuba.algo3.modelo.excepciones.MovimientoInvalidoException;
import edu.fiuba.algo3.modelo.tablero.Tablero;
import edu.fiuba.algo3.modelo.unidad.Mech;
import edu.fiuba.algo3.modelo.unidad.turno.Agotado;
import edu.fiuba.algo3.modelo.unidad.turno.ControladorDeshacer;
import edu.fiuba.algo3.modelo.unidad.turno.MementoMech;
import edu.fiuba.algo3.modelo.unidad.turno.Movido;
import edu.fiuba.algo3.modelo.unidad.turno.Disponible;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class MovidoUnitTest {

    @Test
    public void test01MechMovidoNoPuedeVolverAMoverse() {
        Movido estado = new Movido(mock(MementoMech.class));

        Mech mech = mock(Mech.class);
        Casilla destino = mock(Casilla.class);
        Tablero tablero = mock(Tablero.class);
        ControladorDeshacer controlador = mock(ControladorDeshacer.class);

        assertThrows(MovimientoInvalidoException.class,
            () -> estado.mover(mech, destino, tablero, controlador));

        verifyNoInteractions(mech, destino, tablero, controlador);
    }

    @Test
    public void test02MechMovidoPuedeAtacarYQuedaAgotado() {
        Movido estado = new Movido(mock(MementoMech.class));

        Mech mech = mock(Mech.class);
        Casilla objetivo = mock(Casilla.class);
        Tablero tablero = mock(Tablero.class);
        ControladorDeshacer controlador = mock(ControladorDeshacer.class);

        estado.atacar(mech, objetivo, tablero, controlador);

        verify(mech).iniciarAtaque(objetivo, tablero);
        verify(controlador).invalidarSiCorresponde(mech);
        verify(mech).cambiarEstadoTurno(any(Agotado.class));
    }

    @Test
    public void test03MechMovidoPuedeDeshacerYQuedaDisponible() {
        MementoMech memento = mock(MementoMech.class);
        Movido estado = new Movido(memento);

        Mech mech = mock(Mech.class);
        Tablero tablero = mock(Tablero.class);

        estado.deshacerMovimiento(mech, tablero);

        verify(mech).restaurarDesdeMemento(memento, tablero);
        verify(mech).cambiarEstadoTurno(any(Disponible.class));
    }
}
