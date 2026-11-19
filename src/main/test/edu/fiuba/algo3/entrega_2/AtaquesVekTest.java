package edu.fiuba.algo3.entrega_2;

import edu.fiuba.algo3.modelo.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class AtaquesVekTest {

    private Tablero crearTableroVacio() {
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
    public void testSaltadorHaceDosDanioYEmpujaAUnidadAdyacente() {

        Tablero tablero = crearTableroVacio();

        Saltador saltador = new Saltador(new Posicion(2, 2));
        Mech mech = new MechFactory().crearMechDeCombate(new Posicion(2, 3));

        tablero.colocarUnidad(saltador);
        tablero.colocarUnidad(mech);

        saltador.iniciarAtaque(tablero, new Posicion(2, 3));

        assertEquals(1, mech.getVidaActual());
        assertEquals(new Posicion(2, 4), mech.getPosicion());
    }

    @Test
    public void testLuciernagaHaceUnDanioYContaminaAUnidadQueImpacta() {

        Tablero tablero = crearTableroVacio();

        Luciernaga luciernaga = new Luciernaga(new Posicion(2, 1));
        Mech mech = new MechFactory().crearMechDeCombate(new Posicion(2, 3));

        tablero.colocarUnidad(luciernaga);
        tablero.colocarUnidad(mech);

        luciernaga.iniciarAtaque(tablero, new Posicion(2, 3));

        // El primer ataque hace solamente 1 de daño
        assertEquals(2, mech.getVidaActual());

        // Un daño posterior se duplica porque el Mech está contaminado
        mech.recibirDanio(1);

        assertEquals(0, mech.getVidaActual());
    }

    @Test
    public void testAvisponHaceUnDanioSinEmpujarAUnidadAdyacente() {

        Tablero tablero = crearTableroVacio();

        Avispon avispon = new Avispon(new Posicion(2, 2));
        Mech mech = new MechFactory().crearMechDeCombate(new Posicion(2, 3));

        tablero.colocarUnidad(avispon);
        tablero.colocarUnidad(mech);

        avispon.iniciarAtaque(tablero, new Posicion(2, 3));

        assertEquals(2, mech.getVidaActual());
        assertEquals(new Posicion(2, 3), mech.getPosicion());
    }

    @Test
    public void testDosLuciernagasNoAcumulanLaContaminacion() {

        Tablero tablero = crearTableroVacio();

        Luciernaga luciernaga1 = new Luciernaga(new Posicion(2, 1));
        Luciernaga luciernaga2 = new Luciernaga(new Posicion(1, 3));

        Mech mech = new MechFactory().crearMechDeCombate(new Posicion(2, 3));

        tablero.colocarUnidad(luciernaga1);
        tablero.colocarUnidad(luciernaga2);
        tablero.colocarUnidad(mech);

        luciernaga1.iniciarAtaque(tablero, new Posicion(2, 3));

        assertEquals(2, mech.getVidaActual());

        luciernaga2.iniciarAtaque(tablero, new Posicion(2, 3));

        assertEquals(0, mech.getVidaActual());
    }
}
