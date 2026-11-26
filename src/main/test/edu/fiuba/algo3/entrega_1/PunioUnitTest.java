package edu.fiuba.algo3.entrega_1;

import edu.fiuba.algo3.modelo.arma.Punio;
import edu.fiuba.algo3.modelo.excepciones.AtaqueInvalidoException;
import edu.fiuba.algo3.modelo.tablero.Direccion;
import edu.fiuba.algo3.modelo.tablero.Posicion;
import edu.fiuba.algo3.modelo.tablero.Tablero;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class PunioUnitTest {

    @Test
    void punioDaniaDosPuntosDeVidaAlObjetivo() {
        Tablero tableroMock = mock(Tablero.class);
        Punio punio = new Punio();

        Posicion origen = new Posicion(2, 2);
        Posicion objetivo = new Posicion(2, 3);

        punio.atacar(tableroMock, origen, objetivo);

        verify(tableroMock).daniarEn(objetivo, 2);
    }

    @Test
    void punioEmpujaAlObjetivoEnLaDireccionDelAtaque() {
        Tablero tableroMock = mock(Tablero.class);
        Punio punio = new Punio();

        Posicion origen = new Posicion(2, 2);
        Posicion objetivo = new Posicion(2, 3);

        punio.atacar(tableroMock, origen, objetivo);

        verify(tableroMock).empujar(objetivo, Direccion.ESTE);
    }

    @Test
    void punioNoPuedeAtacarUnaPosicionNoAdyacente() {
        Tablero tableroMock = mock(Tablero.class);
        Punio punio = new Punio();

        Posicion origen = new Posicion(2, 2);
        Posicion objetivo = new Posicion(2, 4);

        assertThrows(
            AtaqueInvalidoException.class,
            () -> punio.atacar(tableroMock, origen, objetivo)
        );

    }

    @Test
    void punioNoPuedeAtacarEnDiagonal() {
        Tablero tableroMock = mock(Tablero.class);
        Punio punio = new Punio();

        Posicion origen = new Posicion(2, 2);
        Posicion objetivo = new Posicion(3, 3);

        assertThrows(
            AtaqueInvalidoException.class,
            () -> punio.atacar(tableroMock, origen, objetivo)
        );

    }
}
