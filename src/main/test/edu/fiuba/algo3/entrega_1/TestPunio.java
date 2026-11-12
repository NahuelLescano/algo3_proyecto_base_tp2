package edu.fiuba.algo3.entrega_1;

import edu.fiuba.algo3.modelo.Tablero;
import edu.fiuba.algo3.modelo.Posicion;
import edu.fiuba.algo3.modelo.Luciernaga;
import edu.fiuba.algo3.modelo.Punio;
import edu.fiuba.algo3.modelo.Direccion;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

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