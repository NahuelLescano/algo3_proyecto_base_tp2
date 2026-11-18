package edu.fiuba.algo3.entrega_1;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import edu.fiuba.algo3.modelo.Mech;
import edu.fiuba.algo3.modelo.MechFactory;
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
