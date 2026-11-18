package edu.fiuba.algo3.entrega_1;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import edu.fiuba.algo3.modelo.*;

public class ColisionTest {

    private Tablero crearTableroPrueba() {
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

        return new Tablero(mapa);
    }

    @Test
    public void test01ColisionEntreDosVek() {
        Tablero tablero = crearTableroPrueba();

        Posicion posicionVek1 = new Posicion(2, 2);
        Posicion posicionVek2 = new Posicion(2, 3);

        Luciernaga vek1 = new Luciernaga(posicionVek1);
        Luciernaga vek2 = new Luciernaga(posicionVek2);

        tablero.colocarUnidad(vek1);
        tablero.colocarUnidad(vek2);

        int vidaInicialVek1 = vek1.getVidaActual();
        int vidaInicialVek2 = vek2.getVidaActual();

        tablero.empujarUnidad(vek1, Direccion.ESTE);

        assertEquals(posicionVek1, vek1.getPosicion());
        assertEquals(posicionVek2, vek2.getPosicion());

        assertEquals(vidaInicialVek1 - 1, vek1.getVidaActual());
        assertEquals(vidaInicialVek2 - 1, vek2.getVidaActual());
    }

    @Test
    public void test02ColisionEntreVekYMech() {
        Tablero tablero = crearTableroPrueba();

        Posicion posicionVek = new Posicion(5, 1);
        Posicion posicionMech = new Posicion(5, 2);

        Luciernaga vek = new Luciernaga(posicionVek);
        Mech mech = new MechFactory().crearMechDeCombate(posicionMech);

        tablero.colocarUnidad(vek);
        tablero.colocarUnidad(mech);

        int vidaInicialVek = vek.getVidaActual();
        int vidaInicialMech = mech.getVidaActual();

        tablero.empujarUnidad(vek, Direccion.ESTE);

        assertEquals(posicionVek, vek.getPosicion());
        assertEquals(posicionMech, mech.getPosicion());

        assertEquals(vidaInicialVek - 1, vek.getVidaActual());
        assertEquals(vidaInicialMech - 1, mech.getVidaActual());
    }

    @Test
    public void test03ColisionEntreDosMechs() {
        Tablero tablero = crearTableroPrueba();

        Posicion posicionMech1 = new Posicion(6, 5);
        Posicion posicionMech2 = new Posicion(7, 5);

        MechFactory fabrica = new MechFactory();

        Mech mech1 = fabrica.crearMechDeCombate(posicionMech1);
        Mech mech2 = fabrica.crearMechDeCombate(posicionMech2);

        tablero.colocarUnidad(mech1);
        tablero.colocarUnidad(mech2);

        int vidaInicialMech1 = mech1.getVidaActual();
        int vidaInicialMech2 = mech2.getVidaActual();

        tablero.empujarUnidad(mech1, Direccion.SUR);

        assertEquals(posicionMech1, mech1.getPosicion());
        assertEquals(posicionMech2, mech2.getPosicion());

        assertEquals(vidaInicialMech1 - 1, mech1.getVidaActual());
        assertEquals(vidaInicialMech2 - 1, mech2.getVidaActual());
    }
}
