package edu.fiuba.algo3.entrega_2;

import edu.fiuba.algo3.modelo.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class EfectoAcidoTest {

    @Test
    public void mechQueEntraEnAcidoQuedaContaminadoYRecibeDobleDanio() {

        String[] mapa = {
            "--------",
            "-X------",
            "--------",
            "--------",
            "--------",
            "--------",
            "--------",
            "--------"
        };

        Tablero tablero = new Tablero(mapa);

        MechFactory factory = new MechFactory();
        Mech mech = factory.crearMechDeCombate(new Posicion(0, 1));

        assertTrue(tablero.colocarUnidad(mech));
        assertTrue(tablero.desplazarUnidad(mech, new Posicion(1, 1)));

        int vidaInicial = mech.getVidaActual();

        mech.recibirDanio(1);

        assertEquals(vidaInicial - 2, mech.getVidaActual());
    }

    @Test
    public void avisponQueEntraEnAcidoNoSeContamina() {

        String[] mapa = {
            "--------",
            "-A------",
            "--------",
            "--------",
            "--------",
            "--------",
            "--------",
            "--------"
        };

        Tablero tablero = new Tablero(mapa);

        Avispon avispon = new Avispon(new Posicion(0, 1));

        assertTrue(tablero.colocarUnidad(avispon));
        assertTrue(tablero.desplazarUnidad(avispon, new Posicion(1, 1)));

        int vidaInicial = avispon.getVidaActual();

        avispon.recibirDanio(1);

        assertEquals(vidaInicial - 1, avispon.getVidaActual());
    }
}
