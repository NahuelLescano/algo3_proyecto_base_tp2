package edu.fiuba.algo3.entrega_1;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import edu.fiuba.algo3.modelo.tablero.Posicion;
import edu.fiuba.algo3.modelo.tablero.Tablero;
import edu.fiuba.algo3.modelo.unidad.Luciernaga;
import edu.fiuba.algo3.modelo.unidad.MechDeCombate;

public class MovimientoTest {
    private static final String[] MAPA_VACIO = {
            "--------",
            "--------",
            "--------",
            "--------",
            "--------",
            "--------",
            "--------",
            "--------"
    };

    private Tablero tablero;
    private Posicion posicionInicial;

    @BeforeEach
    public void setup() {
        this.tablero = new Tablero(MAPA_VACIO);
        this.posicionInicial = new Posicion(1, 1);
    }

    @Test
    public void test01MoverMechQueYaSeMovioEnElTurnoFalla() {
        Posicion destino1 = new Posicion(1, 2);
        Posicion destino2 = new Posicion(1, 3);

        MechDeCombate mech = new MechDeCombate(posicionInicial);
        assertTrue(tablero.colocarUnidad(mech));

        boolean primerMovimiento = tablero.moverUnidad(mech, destino1);
        assertTrue(primerMovimiento, "El primer movimiento debería ser exitoso");

        boolean segundoMovimiento = tablero.moverUnidad(mech, destino2);
        assertFalse(segundoMovimiento,
                "Un segundo movimiento en el mismo turno no debería permitirse");
    }

    @Test
    public void test02AtacarMechQueYaAtacoEnElTurnoFalla() {
        Posicion objetivo = new Posicion(1, 2);

        MechDeCombate mech = new MechDeCombate(posicionInicial);
        Luciernaga vek = new Luciernaga(objetivo);

        assertTrue(tablero.colocarUnidad(mech));
        assertTrue(tablero.colocarUnidad(vek));

        boolean primerAtaque = mech.iniciarAtaque(tablero, objetivo);
        assertTrue(primerAtaque, "El primer ataque debería ser exitoso");

        boolean segundoAtaque = mech.iniciarAtaque(tablero, objetivo);
        assertFalse(segundoAtaque,
                "Un segundo ataque en el mismo turno no debería permitirse");
    }
}
