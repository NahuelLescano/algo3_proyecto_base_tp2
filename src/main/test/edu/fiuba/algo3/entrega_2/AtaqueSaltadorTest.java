package edu.fiuba.algo3.entrega_2;

import edu.fiuba.algo3.modelo.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class AtaqueSaltadorTest {

    @Test
    public void testElSaltadorHaceDanioYEmpujaAUnidadAdyacente() {

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

        Tablero tablero = new Tablero(mapa);

        Saltador saltador = new Saltador(new Posicion(2, 2));
        Mech mech = new MechFactory().crearMechDeCombate(new Posicion(2, 3));

        tablero.colocarUnidad(saltador);
        tablero.colocarUnidad(mech);

        saltador.iniciarAtaque(tablero, new Posicion(2, 3));
        
        assertEquals(1, mech.getVidaActual());

        assertEquals(new Posicion(2, 4), mech.getPosicion());
    }
}
