package edu.fiuba.algo3.entrega_2;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import edu.fiuba.algo3.modelo.tablero.Direccion;
import edu.fiuba.algo3.modelo.tablero.ParserTablero;
import edu.fiuba.algo3.modelo.tablero.Posicion;
import edu.fiuba.algo3.modelo.tablero.Tablero;
import edu.fiuba.algo3.modelo.unidad.Unidad;
import edu.fiuba.algo3.modelo.unidad.Vek;

public class test01 {
    private final String[] mapaValido = { "--", "--" };

    @Test
    public void empujarUnidadContraOtraUnidadNoLaMueveYAmbasRecibenDanio() {
        Tablero tablero = new Tablero(mapaValido, new ParserTablero());

        Unidad empujada = new Vek(2, new Posicion(0, 0));
        Unidad bloqueo = new Vek(2, new Posicion(0, 1));

        assertTrue(tablero.colocarUnidad(empujada, new Posicion(0, 0)));
        assertTrue(tablero.colocarUnidad(bloqueo, new Posicion(0, 1)));

        tablero.empujar(empujada, Direccion.ESTE);

        assertEquals(new Posicion(0, 0), empujada.getPosicion());
        assertEquals(1, empujada.getVidaActual());
        assertEquals(1, bloqueo.getVidaActual());
    }
}
