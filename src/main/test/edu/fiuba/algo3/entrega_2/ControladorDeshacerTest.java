package edu.fiuba.algo3.entrega_2;

import edu.fiuba.algo3.modelo.excepciones.AccionInvalidaException;
import edu.fiuba.algo3.modelo.tablero.ParserTablero;
import edu.fiuba.algo3.modelo.tablero.Posicion;
import edu.fiuba.algo3.modelo.tablero.Tablero;
import edu.fiuba.algo3.modelo.unidad.Mech;
import edu.fiuba.algo3.modelo.unidad.MechFactory;
import edu.fiuba.algo3.modelo.unidad.turno.ControladorDeshacer;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ControladorDeshacerTest {
    @Test
    public void deshacerUltimoMovimiento_MechMovidoSobreAcido_ReviertePosicionYContaminacion() {
        // Arrange: Mapa 8x8 con una casilla de Ácido ('X') en (2, 3)
        ParserTablero parser = new ParserTablero();

        String[] mapa = {
                "--------",
                "--------",
                "---XX---",
                "--------",
                "--------",
                "--------",
                "--------",
                "--------"
        };
        Tablero tablero = new Tablero(mapa, parser);
        ControladorDeshacer controlador = new ControladorDeshacer();
        Mech mech = new MechFactory().crearMechCanon();
        Posicion origen = new Posicion(2, 2);
        Posicion destinoAcido = new Posicion(2, 3);

        tablero.colocarUnidad(mech, origen);

        // Act: El Mech se mueve al ácido (queda Contaminado) y luego se deshace
        mech.mover(destinoAcido, tablero, controlador);
        controlador.deshacerUltimoMovimiento(tablero);

        // Assert: Volvió a (2,2), no recibe doble daño (no está Contaminada) y puede
        // volver a moverse
        assertEquals(origen, mech.posicion());
        mech.recibirDanio(1);
        assertEquals(2, mech.getVidaActual()); // Vida base 3 - 1 = 2 (si siguiera contaminada sería 1)
        assertDoesNotThrow(() -> mech.mover(new Posicion(2, 1), tablero, controlador));
    }

    @Test
    public void deshacerUltimoMovimiento_DosVecesEnMismoTurno_LanzaExcepcion() {
        ParserTablero parser = new ParserTablero();

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
        Tablero tablero = new Tablero(mapa, parser);
        ControladorDeshacer controlador = new ControladorDeshacer();
        Mech mech1 = new MechFactory().crearMechDeCombate();
        Mech mech2 = new MechFactory().crearMechCanon();

        tablero.colocarUnidad(mech1, new Posicion(1, 1));
        tablero.colocarUnidad(mech2, new Posicion(4, 4));

        mech1.mover(new Posicion(1, 2), tablero, controlador);
        controlador.deshacerUltimoMovimiento(tablero);

        mech2.mover(new Posicion(4, 5), tablero, controlador);

        assertThrows(AccionInvalidaException.class, () -> {
            controlador.deshacerUltimoMovimiento(tablero);
        });
    }

}
