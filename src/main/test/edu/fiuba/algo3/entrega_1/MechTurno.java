package edu.fiuba.algo3.entrega_1;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class MechTurno {
    @Test
    public void mover_MechQueYaSeMovioEnElTurno_LanzaExcepcion() {
        // Arrange: Crear tablero y un Mech en una posición inicial
        Tablero tablero = new Tablero();
        Posicion origen = new Posicion(1, 1);
        Posicion destino1 = new Posicion(1, 2);
        Posicion destino2 = new Posicion(1, 3);
        Mech mech = new MechCombate(origen);
        tablero.colocarUnidad(mech, origen);

        // Act
        mech.mover(destino1, tablero);

        // Assert: Intentar un segundo movimiento en el mismo turno debe fallar
        assertThrows(MovimientoInvalidoException.class, () -> {
            mech.mover(destino2, tablero);
        });
    }

    @Test
    public void atacar_MechQueYaAtacoEnElTurno_LanzaExcepcion() {
        // Arrange
        Tablero tablero = new Tablero();
        Posicion origen = new Posicion(1, 1);
        Mech mech = new MechCombate(origen);
        tablero.colocarUnidad(mech, origen);

        mech.iniciarAtaque(Direccion.ESTE, tablero);

        // Act & Assert: Un segundo ataque en el mismo turno debe lanzar excepción
        assertThrows(AtaqueInvalidoException.class, () -> {
            mech.iniciarAtaque(Direccion.ESTE, tablero);
        });
    }
    @Test
    public void deshacerMovimiento_MechQueSoloSeMovio_VuelveAPosicionAnteriorYPermiteMoverse() {
        // Arrange
        Tablero tablero = new Tablero();
        Posicion inicial = new Posicion(2, 2);
        Posicion destino = new Posicion(2, 4);
        Mech mech = new MechCombate(inicial);
        tablero.colocarUnidad(mech, inicial);

        mech.mover(destino, tablero);

        // Act
        mech.deshacerMovimiento(tablero);

        // Assert: Debe haber vuelto a la casilla inicial y poder moverse a un nuevo destino
        assertEquals(inicial, mech.obtenerPosicion());
        assertDoesNotThrow(() -> mech.mover(destino, tablero));
    }
    @Test
    public void deshacerMovimiento_MechQueYaAtaco_LanzaExcepcion() {
        // Arrange
        Tablero tablero = new Tablero();
        Posicion inicial = new Posicion(2, 2);
        Posicion destino = new Posicion(2, 3);
        Mech mech = new MechCombate(inicial);
        tablero.colocarUnidad(mech, inicial);

        mech.mover(destino, tablero);
        mech.iniciarAtaque(Direccion.NORTE, tablero);

        // Act & Assert
        assertThrows(AccionInvalidaException.class, () -> {
            mech.deshacerMovimiento(tablero);
        });
    }

    @Test
    public void deshacerMovimiento_MasDeUnaVezEnElMismoTurno_LanzaExcepcion() {
        // Arrange
        Tablero tablero = new Tablero();
        Posicion inicial = new Posicion(2, 2);
        Posicion paso1 = new Posicion(2, 3);
        Posicion paso2 = new Posicion(2, 4);
        Mech mech = new MechCombate(inicial);
        tablero.colocarUnidad(mech, inicial);

        // Primer movimiento y primer deshacer (válido)
        mech.mover(paso1, tablero);
        mech.deshacerMovimiento(tablero);

        // Segundo movimiento en el mismo turno
        mech.mover(paso2, tablero);

        // Act & Assert: Intentar un segundo deshacer en el mismo turno debe fallar
        assertThrows(AccionInvalidaException.class, () -> {
            mech.deshacerMovimiento(tablero);
        });
    }
}
