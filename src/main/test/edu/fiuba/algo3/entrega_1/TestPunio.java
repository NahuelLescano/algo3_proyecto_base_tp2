package edu.fiuba.algo3.entrega_1;

import edu.fiuba.algo3.modelo.Tablero;
import edu.fiuba.algo3.modelo.Posicion;
import edu.fiuba.algo3.modelo.Luciernaga;
import edu.fiuba.algo3.modelo.Mech;
import edu.fiuba.algo3.modelo.MechFactory;

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
        MechFactory fabrica = new MechFactory();

        Mech mech = fabrica.crearMechDeCombate(new Posicion(2, 2));
        Luciernaga unidad = new Luciernaga(new Posicion(2, 3));

        assertTrue(tablero.colocarUnidad(mech));
        assertTrue(tablero.colocarUnidad(unidad));

        int vidaInicial = unidad.getVidaActual();

        mech.iniciarAtaque(tablero, new Posicion(2, 3));

        assertEquals(vidaInicial - 2, unidad.getVidaActual());
        assertEquals(new Posicion(2, 4), unidad.getPosicion());
    }
}
