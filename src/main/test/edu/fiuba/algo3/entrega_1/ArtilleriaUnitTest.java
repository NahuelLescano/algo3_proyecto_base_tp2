package edu.fiuba.algo3.entrega_1;

import edu.fiuba.algo3.modelo.arma.Artilleria;
import edu.fiuba.algo3.modelo.tablero.Direccion;
import edu.fiuba.algo3.modelo.tablero.Posicion;
import edu.fiuba.algo3.modelo.tablero.Tablero;
import edu.fiuba.algo3.modelo.excepciones.AtaqueInvalidoException;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class ArtilleriaUnitTest {

    @Test
    void artilleriaDaniaLaPosicionObjetivo() {
        // Arrange
        Tablero tableroMock = mock(Tablero.class);
        Artilleria artilleria = new Artilleria();

        Posicion origen = new Posicion(3, 0);
        Posicion objetivo = new Posicion(3, 3);

        // Act
        artilleria.atacar(tableroMock, origen, objetivo);

        // Assert
        verify(tableroMock).daniarEn(objetivo, 1);
    }
    
    @Test
    void artilleriaEmpujaLasCuatroPosicionesAdyacentes() {
        // Arrange
        Tablero tableroMock = mock(Tablero.class);
        Artilleria artilleria = new Artilleria();

        Posicion origen = new Posicion(3, 0);
        Posicion objetivo = new Posicion(3, 3);

        // Act
        artilleria.atacar(tableroMock, origen, objetivo);

        // Assert
        for (Direccion direccion : Direccion.values()) {
            Posicion vecina = objetivo.desplazar(direccion);

            verify(tableroMock).empujar(vecina, direccion);
        }
    }
    
    @Test
    void artilleriaNoPuedeAtacarUnObjetivoDemasiadoCercano() {
        // Arrange
        Tablero tableroMock = mock(Tablero.class);
        Artilleria artilleria = new Artilleria();

        Posicion origen = new Posicion(3, 0);
        Posicion objetivo = new Posicion(3, 1);

        // Act + Assert
        assertThrows(
            AtaqueInvalidoException.class,
            () -> artilleria.atacar(tableroMock, origen, objetivo)
        );

        verifyNoInteractions(tableroMock);
    }
    
    @Test
    void artilleriaNoPuedeAtacarEnDiagonal() {
        // Arrange
        Tablero tableroMock = mock(Tablero.class);
        Artilleria artilleria = new Artilleria();

        Posicion origen = new Posicion(2, 2);
        Posicion objetivo = new Posicion(4, 3);

        // Act + Assert
        assertThrows(
            AtaqueInvalidoException.class,
            () -> artilleria.atacar(tableroMock, origen, objetivo)
        );

        verifyNoInteractions(tableroMock);
    }
}
