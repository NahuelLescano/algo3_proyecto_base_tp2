package edu.fiuba.algo3.entrega_1;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import edu.fiuba.algo3.modelo.Tablero;
import edu.fiuba.algo3.modelo.Posicion;
import edu.fiuba.algo3.modelo.Mech;
import edu.fiuba.algo3.modelo.MechFactory;
import edu.fiuba.algo3.modelo.Luciernaga;

public class MechTurno {

    private Tablero crearTableroPrueba() {
        String[] mapaVacio = {
            "--------",
            "--------",
            "--------",
            "--------",
            "--------",
            "--------",
            "--------",
            "--------"
        };
        return new Tablero(mapaVacio);
    }

    @Test
    public void test01MoverMechQueYaSeMovioEnElTurnoFalla() {
        Tablero tablero = crearTableroPrueba();

        Posicion origen = new Posicion(1, 1);
        Posicion destino1 = new Posicion(1, 2);
        Posicion destino2 = new Posicion(1, 3);

        MechFactory fabrica = new MechFactory();
        Mech mech = fabrica.crearMechDeCombate(origen);

        assertTrue(tablero.colocarUnidad(mech));

        boolean primerMovimiento = tablero.moverUnidad(mech, destino1);
        assertTrue(primerMovimiento, "El primer movimiento debería ser exitoso");

        boolean segundoMovimiento = tablero.moverUnidad(mech, destino2);
        assertFalse(segundoMovimiento,
                "Un segundo movimiento en el mismo turno no debería permitirse");
    }

    @Test
    public void test02AtacarMechQueYaAtacoEnElTurnoFalla() {
        Tablero tablero = crearTableroPrueba();

        Posicion origen = new Posicion(1, 1);
        Posicion objetivo = new Posicion(1, 2);

        MechFactory fabrica = new MechFactory();
        Mech mech = fabrica.crearMechDeCombate(origen);
        Luciernaga vek = new Luciernaga(objetivo);

        assertTrue(tablero.colocarUnidad(mech));
        assertTrue(tablero.colocarUnidad(vek));

        boolean primerAtaque =
                mech.iniciarAtaque(tablero, objetivo).fueRealizado();

        assertTrue(primerAtaque, "El primer ataque debería ser exitoso");

        boolean segundoAtaque =
                mech.iniciarAtaque(tablero, objetivo).fueRealizado();

        assertFalse(segundoAtaque,
                "Un segundo ataque en el mismo turno no debería permitirse");
    }

    // Test de deshacer movimiento pendiente.
    
    // @Test
    // public void test03DeshacerMovimientoMechQueSoloSeMovioVuelveAPosicionAnterior() {
    //    Tablero tablero = crearTableroPrueba();

    //   Posicion inicial = new Posicion(2, 2);
    //   Posicion destino = new Posicion(2, 4);

    //    MechFactory fabrica = new MechFactory();
    //    Mech mech = fabrica.crearMechDeCombate(inicial);

    //    assertTrue(tablero.colocarUnidad(mech));

    //    boolean movimientoExitoso = tablero.moverUnidad(mech, destino);
    //    assertTrue(movimientoExitoso);

    //    assertEquals(destino, mech.getPosicion());

    //    tablero.deshacerMovimiento(mech);

    //    assertEquals(inicial, mech.getPosicion());
    // }
}
