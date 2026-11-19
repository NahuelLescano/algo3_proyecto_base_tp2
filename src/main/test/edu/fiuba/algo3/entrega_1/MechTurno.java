package edu.fiuba.algo3.entrega_1;

import edu.fiuba.algo3.modelo.unidad.Mech;
import edu.fiuba.algo3.modelo.unidad.MechFactory;
import edu.fiuba.algo3.modelo.unidad.Vek;
import edu.fiuba.algo3.modelo.unidad.VekFactory;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import edu.fiuba.algo3.modelo.*;

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
        return new Tablero(mapaVacio, new ParserTablero());
    }

    @Test
    public void test01MoverMechQueYaSeMovioEnElTurnoFalla() {
        Tablero tablero = crearTableroPrueba();
        ControladorDeshacer controlador = new ControladorDeshacer();

        Posicion origen = new Posicion(1, 1);
        Posicion destino1 = new Posicion(1, 2);
        Posicion destino2 = new Posicion(1, 3);

        Mech mech = new MechFactory().crearMechDeCombate();
        tablero.colocarUnidad(mech, origen);

        mech.mover(destino1, tablero, controlador);
        assertEquals(destino1, mech.posicion());

        assertThrows(RuntimeException.class,
                () -> mech.mover(destino2, tablero, controlador));
        assertEquals(destino1, mech.posicion());
    }

    @Test
    public void test02AtacarMechQueYaAtacoEnElTurnoFalla() {
        Tablero tablero = crearTableroPrueba();
        ControladorDeshacer controlador = new ControladorDeshacer();

        Posicion origen = new Posicion(1, 1);
        Posicion objetivo = new Posicion(1, 2);

        Mech mech = new MechFactory().crearMechDeCombate();
        Vek vek = new VekFactory().crearLuciernaga();

        tablero.colocarUnidad(mech, origen);
        tablero.colocarUnidad(vek, objetivo);

        mech.atacar(objetivo, tablero, controlador);

        assertThrows(RuntimeException.class,
                () -> mech.atacar(objetivo, tablero, controlador));
    }

    @Test
    public void test03DeshacerMovimientoMechQueSoloSeMovioVuelveAPosicionAnterior() {
        Tablero tablero = crearTableroPrueba();
        ControladorDeshacer controlador = new ControladorDeshacer();

        Posicion inicial = new Posicion(2, 2);
        Posicion destino = new Posicion(2, 4);

        Mech mech = new MechFactory().crearMechDeCombate();
        tablero.colocarUnidad(mech, inicial);

        mech.mover(destino, tablero, controlador);
        assertEquals(destino, mech.posicion());

        mech.deshacerMovimiento(tablero);
        assertEquals(inicial, mech.posicion());
    }
}