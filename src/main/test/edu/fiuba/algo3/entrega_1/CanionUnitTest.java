package edu.fiuba.algo3.entrega_1;

import edu.fiuba.algo3.modelo.arma.Canion;
import edu.fiuba.algo3.modelo.arma.Disparo;
import edu.fiuba.algo3.modelo.casilla.Casilla;
import edu.fiuba.algo3.modelo.excepciones.AtaqueInvalidoException;
import edu.fiuba.algo3.modelo.tablero.Direccion;
import edu.fiuba.algo3.modelo.tablero.Posicion;
import edu.fiuba.algo3.modelo.tablero.Tablero;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class CanionUnitTest {

    @Test
    void canionNoPuedeAtacarSuMismaPosicion() {
        Disparo disparoMock = mock(Disparo.class);
        Canion canion = new Canion(disparoMock);

        Posicion posicion = new Posicion(2, 2);

        assertThrows(
            AtaqueInvalidoException.class,
            () -> canion.atacar(null, posicion, posicion)  // No le paso un tablero porque al ser las posiciones origen y objetivo iguales, no se llega a ejecutar tablero.buscarCasilla
        );
    }
    
    @Test
    void canionComienzaElDisparoEnLaCasillaSiguienteAlOrigen() {
        // Arrange
        Disparo disparoMock = mock(Disparo.class);
        Tablero tableroMock = mock(Tablero.class);
        Casilla casillaMock = mock(Casilla.class);

        Canion canion = new Canion(disparoMock);

        Posicion origen = new Posicion(2, 2);
        Posicion objetivo = new Posicion(2, 4);
        Posicion siguiente = new Posicion(2, 3);

        when(tableroMock.buscarCasilla(siguiente))
            .thenReturn(casillaMock);

        when(casillaMock.avanzarDisparoEnLinea(
                siguiente,
                Direccion.ESTE,
                tableroMock,
                disparoMock
        )).thenReturn(null); // Cuando se invoque el método con estos argumentos quiero que devuelva null porque quiero que el ciclo termine después de la primera iteración, no quiero probar todo el recorrido sino verificar que se este avanzando hacia la dirección correcta y que la primera posición a recorrer sea la correcta

        // Act
        canion.atacar(tableroMock, origen, objetivo);

        // Assert
        verify(tableroMock).buscarCasilla(siguiente);

        verify(casillaMock).avanzarDisparoEnLinea(
            siguiente,
            Direccion.ESTE,
            tableroMock,
            disparoMock
        );
    }
    
    @Test
    void canionNoPuedeAtacarUnaPosicionEnDiagonal() {
    
        // Arrange
        Disparo disparoMock = mock(Disparo.class);
        Tablero tableroMock = mock(Tablero.class);

        Canion canion = new Canion(disparoMock);

        Posicion origen = new Posicion(2, 2);
        Posicion objetivo = new Posicion(4, 3);

        // Act + Assert
        assertThrows(
            IllegalArgumentException.class,
            () -> canion.atacar(tableroMock, origen, objetivo)
        );

        verifyNoInteractions(tableroMock);  // este método de mockito sirve para verificar que no haya interactuado con el tablero
    }
}
