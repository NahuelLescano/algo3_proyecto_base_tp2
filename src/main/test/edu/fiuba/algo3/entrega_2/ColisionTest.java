package edu.fiuba.algo3.entrega_2;

import edu.fiuba.algo3.modelo.tablero.*;
import edu.fiuba.algo3.modelo.terreno.Montania;
import edu.fiuba.algo3.modelo.unidad.*;
import org.junit.jupiter.api.Test;
import edu.fiuba.algo3.modelo.tablero.*;


import static org.junit.jupiter.api.Assertions.*;

public class ColisionTest {

    private final MechFactory mechFactory = new MechFactory();
    private final VekFactory vekFactory = new VekFactory();

    @Test
    public void testEmpujarUnidadContraOtraUnidadProvocaColision() {

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

        Tablero tablero = new Tablero(mapa, new ParserTablero());

        Mech mech = mechFactory.crearMechDeCombate();
        Vek vek = vekFactory.crearSaltador();

        Posicion posicionMech = new Posicion(2, 2);
        Posicion posicionVek = new Posicion(2, 3);

        tablero.colocarUnidad(mech, posicionMech);
        tablero.colocarUnidad(vek, posicionVek);

        int vidaInicialMech = mech.getVidaActual();
        int vidaInicialVek = vek.getVidaActual();

        tablero.empujar(posicionMech, Direccion.ESTE);

        assertEquals(posicionMech, mech.posicion());
        assertEquals(posicionVek, vek.posicion());

        assertEquals(vidaInicialMech - 1, mech.getVidaActual());
        assertEquals(vidaInicialVek - 1, vek.getVidaActual());
    }

    @Test
    public void testEmpujarUnidadContraMontaniaProvocaColision() {

        String[] mapa = {
            "--------",
            "--------",
            "--M-----",
            "--------",
            "--------",
            "--------",
            "--------",
            "--------"
        };

        Tablero tablero = new Tablero(mapa, new ParserTablero());

        Mech mech = mechFactory.crearMechDeCombate();

        Posicion posicionMech = new Posicion(2, 1);
        Posicion posicionMontania = new Posicion(2, 2);

        tablero.colocarUnidad(mech, posicionMech);

        int vidaInicial = mech.getVidaActual();

        tablero.empujar(posicionMech, Direccion.ESTE);

        assertEquals(posicionMech, mech.posicion());
        assertEquals(vidaInicial - 1, mech.getVidaActual());

        assertTrue(tablero.buscarCasilla(posicionMontania)
                .getTerreno() instanceof Montania);
    }
}
