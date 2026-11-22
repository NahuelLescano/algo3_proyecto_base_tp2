package edu.fiuba.algo3.entrega_1;

import edu.fiuba.algo3.modelo.excepciones.AtaqueInvalidoException;
import edu.fiuba.algo3.modelo.excepciones.MovimientoInvalidoException;
import edu.fiuba.algo3.modelo.tablero.ParserTablero;
import edu.fiuba.algo3.modelo.tablero.Posicion;
import edu.fiuba.algo3.modelo.tablero.Tablero;
import edu.fiuba.algo3.modelo.unidad.Mech;
import edu.fiuba.algo3.modelo.unidad.MechFactory;
import edu.fiuba.algo3.modelo.unidad.Vek;
import edu.fiuba.algo3.modelo.unidad.VekFactory;
import edu.fiuba.algo3.modelo.unidad.turno.ControladorDeshacer;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


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
    private ControladorDeshacer controlador;
    private Posicion posicionInicial;

    @BeforeEach
    public void setup() {
        this.tablero = new Tablero(MAPA_VACIO, new ParserTablero());
        this.controlador = new ControladorDeshacer();
        this.posicionInicial = new Posicion(1, 1);
    }

    @Test
    public void test01MoverMechQueYaSeMovioEnElTurnoFalla() {
        Posicion destino1 = new Posicion(1, 2);
        Posicion destino2 = new Posicion(1, 3);

        Mech mech = new MechFactory().crearMechDeCombate();
        tablero.colocarUnidad(mech, posicionInicial);

        mech.mover(destino1, tablero, controlador);
        assertEquals(destino1, mech.posicion());

        assertThrows(MovimientoInvalidoException.class,
                () -> mech.mover(destino2, tablero, controlador),
                "Un segundo movimiento en el mismo turno no debería permitirse");

        assertEquals(destino1, mech.posicion());
    }

    @Test
    public void test02AtacarMechQueYaAtacoEnElTurnoFalla() {
        Posicion objetivo = new Posicion(1, 2);

        Mech mech = new MechFactory().crearMechDeCombate();
        Vek vek = new VekFactory().crearLuciernaga();

        tablero.colocarUnidad(mech, posicionInicial);
        tablero.colocarUnidad(vek, objetivo);

        mech.atacar(objetivo, tablero, controlador);

        assertThrows(AtaqueInvalidoException.class,
                () -> mech.atacar(objetivo, tablero, controlador),
                "Un segundo ataque en el mismo turno no debería permitirse");
    }
}
