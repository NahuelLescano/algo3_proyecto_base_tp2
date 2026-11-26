package edu.fiuba.algo3.entrega_1;

import edu.fiuba.algo3.modelo.excepciones.AccionInvalidaException;
import edu.fiuba.algo3.modelo.tablero.Direccion;
import edu.fiuba.algo3.modelo.tablero.ParserTablero;
import edu.fiuba.algo3.modelo.tablero.Posicion;
import edu.fiuba.algo3.modelo.tablero.Tablero;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TableroUnitTest {

    private Tablero crearTablero() {
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

        return new Tablero(mapa, new ParserTablero());
    }

    @Test
    void tableroNoPuedeDaniarUnaPosicionInexistente() {
        Tablero tablero = crearTablero();
        Posicion posicion = new Posicion(0, 8);

        assertThrows(
                AccionInvalidaException.class,
                () -> tablero.daniarEn(posicion, 2)
        );
    }

    @Test
    void tableroNoPuedeColocarUnaUnidadEnUnaPosicionInexistente() {
        Tablero tablero = crearTablero();
        Posicion posicion = new Posicion(-1, 0);

        assertThrows(
                AccionInvalidaException.class,
                () -> tablero.colocarUnidad(null, posicion)
        );
    }

    @Test
    void tableroNoPuedeEmpujarHaciaUnaCasillaInexistente() {
        Tablero tablero = crearTablero();
        Posicion origen = new Posicion(0, 7);

        assertThrows(
                AccionInvalidaException.class,
                () -> tablero.empujar(origen, Direccion.ESTE)
        );
    }
}
