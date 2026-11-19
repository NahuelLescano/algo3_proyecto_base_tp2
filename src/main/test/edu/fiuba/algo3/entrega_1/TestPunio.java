package edu.fiuba.algo3.entrega_1;

import edu.fiuba.algo3.modelo.*;
import edu.fiuba.algo3.modelo.unidad.Mech;
import edu.fiuba.algo3.modelo.unidad.MechFactory;
import edu.fiuba.algo3.modelo.unidad.Vek;
import edu.fiuba.algo3.modelo.unidad.VekFactory;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TestPunio {

    @Test
    void punioCausaDosDanioYEmpujaUnaCasilla() {
        String[] mapa = {
                "--------",
                "--------",
                "--------",
                "--------",
                "--------",
                "--------",
                "--------",
                "--------",
                "--------"
        };
        Tablero tablero = new Tablero(mapa, new ParserTablero());
        Mech mech = new MechFactory().crearMechDeCombate();
        Vek luciernaga = new VekFactory().crearLuciernaga();

        tablero.colocarUnidad(mech, new Posicion(2, 2));
        tablero.colocarUnidad(luciernaga, new Posicion(2, 3));
        int vidaInicial = luciernaga.getVidaActual();

        mech.iniciarAtaque(tablero.buscarCasilla(new Posicion(2, 3)), tablero);

        assertEquals(vidaInicial - 2, luciernaga.getVidaActual());
        assertEquals(new Posicion(2, 4), luciernaga.posicion());
    }
}