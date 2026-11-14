package edu.fiuba.algo3.entrega_1;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import edu.fiuba.algo3.modelo.arma.Punio;
import edu.fiuba.algo3.modelo.tablero.Direccion;
import edu.fiuba.algo3.modelo.tablero.Posicion;
import edu.fiuba.algo3.modelo.tablero.Tablero;
import edu.fiuba.algo3.modelo.unidad.Luciernaga;

public class TestPunio {

    @Test
    void punioCausaDosDanioYEmpujaUnaCasilla() {

        String[] mapa = {
                "-------",
                "-------",
                "-------",
                "-------",
                "-------"
        };

        Tablero tablero = new Tablero(mapa);
        Punio punio = new Punio();

        // Ubico una unidad adyacente al atacante, hacia el este
        Luciernaga unidad = new Luciernaga(new Posicion(2, 3));

        assertTrue(tablero.colocarUnidad(unidad));

        int vidaInicial = unidad.getVidaActual();

        punio.atacar(tablero, new Posicion(2, 2), Direccion.ESTE);

        assertEquals(vidaInicial - 2, unidad.getVidaActual());

        assertEquals(new Posicion(2, 4), unidad.getPosicion());
    }
}
