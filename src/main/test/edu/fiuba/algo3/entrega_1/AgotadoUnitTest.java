package edu.fiuba.algo3.entrega_1;

import edu.fiuba.algo3.modelo.excepciones.AccionInvalidaException;
import edu.fiuba.algo3.modelo.excepciones.AtaqueInvalidoException;
import edu.fiuba.algo3.modelo.excepciones.MovimientoInvalidoException;
import edu.fiuba.algo3.modelo.tablero.Tablero;
import edu.fiuba.algo3.modelo.unidad.Mech;
import edu.fiuba.algo3.modelo.unidad.turno.Agotado;
import edu.fiuba.algo3.modelo.unidad.turno.ControladorDeshacer;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class AgotadoUnitTest {

    @Test
    void mechAgotadoNoPuedeMoverse() {
        Agotado estado = new Agotado();

        assertThrows(
                MovimientoInvalidoException.class,
                () -> estado.mover(
                        mock(Mech.class),
                        null,
                        mock(Tablero.class),
                        mock(ControladorDeshacer.class)
                )
        );
    }

    @Test
    void mechAgotadoNoPuedeAtacar() {
        Agotado estado = new Agotado();

        assertThrows(
                AtaqueInvalidoException.class,
                () -> estado.atacar(
                        mock(Mech.class),
                        null,
                        mock(Tablero.class),
                        mock(ControladorDeshacer.class)
                )
        );
    }

    @Test
    void mechAgotadoNoPuedeDeshacerMovimiento() {
        Agotado estado = new Agotado();

        assertThrows(
                AccionInvalidaException.class,
                () -> estado.deshacerMovimiento(
                        mock(Mech.class),
                        mock(Tablero.class)
                )
        );
    }
}
