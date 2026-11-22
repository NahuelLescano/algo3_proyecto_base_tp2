package edu.fiuba.algo3.entrega_2;

import edu.fiuba.algo3.modelo.tablero.Direccion;
import edu.fiuba.algo3.modelo.tablero.ParserTablero;
import edu.fiuba.algo3.modelo.tablero.Posicion;
import edu.fiuba.algo3.modelo.tablero.Tablero;
import edu.fiuba.algo3.modelo.unidad.Vek;
import edu.fiuba.algo3.modelo.unidad.VekFactory;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class test01 {
    @Test
    public void empujarUnidadContraOtraNoLaMueveYAmbasRecibenDanio() {
        Tablero tablero = new Tablero(new String[] { "--", "--" }, new ParserTablero());
        Vek bloqueo = new VekFactory().crearSaltador();
        Vek empujada = new VekFactory().crearSaltador();
        tablero.colocarUnidad(empujada, new Posicion(0, 0));
        tablero.colocarUnidad(bloqueo, new Posicion(0, 1));

        tablero.empujar(new Posicion(0, 0), Direccion.ESTE);

        assertEquals(new Posicion(0, 0), empujada.posicion());
        assertEquals(1, empujada.getVidaActual());
        assertEquals(1, bloqueo.getVidaActual());
    }

}
