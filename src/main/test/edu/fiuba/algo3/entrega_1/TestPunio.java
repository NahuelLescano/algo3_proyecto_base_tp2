package edu.fiuba.algo3.entrega_1;
import edu.fiuba.algo3.modelo.Posicion;
import edu.fiuba.algo3.modelo.MechDeCombate;
import edu.fiuba.algo3.modelo.Luciernaga;
import edu.fiuba.algo3.modelo.Direccion;
import edu.fiuba.algo3.modelo.Punio;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TestPunio {

    @Test
    void punioCausaDosDanioYEmpujaUnaCasilla() {

        // Arrange
        Posicion posicionMech = new Posicion(2, 2);
        Posicion posicionVek = new Posicion(2, 3);

        MechDeCombate mech = new MechDeCombate(posicionMech, new Punio());
        Luciernaga vek = new Luciernaga(posicionVek);

        int vidaInicial = vek.getVidaActual();

        // Act
        mech.iniciarAtaque(Direccion.ESTE);

        // Assert
        assertEquals(vidaInicial - 2, vek.getVidaActual());
        assertEquals(new Posicion(2, 4), vek.getPosicion());
    }
}