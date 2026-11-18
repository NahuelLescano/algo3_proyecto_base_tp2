package edu.fiuba.algo3.entrega_2;

import edu.fiuba.algo3.modelo.*;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class JuegoTest {

    @Test
    public void ataqueDeArtilleriaReduceLaRedElectricaPorTodosLosEdificiosDanados() {

        String[] mapa = {
                "-------",
                "---E---",
                "-------",
                "-E-E-E-",
                "-------",
                "---E---",
                "-------"
        };

        Tablero tablero = new Tablero(mapa);

        RedElectrica redElectrica = new RedElectrica(5);
        Juego juego = new Juego(tablero, redElectrica);

        // Mech atacante equipado con Artillería
        Mech atacante = new Mech(3, new Posicion(3, 0), new Artilleria());

        atacante.iniciarTurno();

        // Creo las unidades que serán empujadas
        Mech unidadNorte = new Mech(
                3, new Posicion(4, 3), new Punio()
        );

        Mech unidadSur = new Mech(
                3, new Posicion(2, 3), new Punio()
        );

        Mech unidadEste = new Mech(
                3, new Posicion(3, 4), new Punio()
        );

        Mech unidadOeste = new Mech(
                3, new Posicion(3, 2), new Punio()
        );

        // Coloco dichas unidades
        assertTrue(tablero.colocarUnidad(atacante));
        assertTrue(tablero.colocarUnidad(unidadNorte));
        assertTrue(tablero.colocarUnidad(unidadSur));
        assertTrue(tablero.colocarUnidad(unidadEste));
        assertTrue(tablero.colocarUnidad(unidadOeste));

        // Ejecución del ataque
        Posicion objetivo = new Posicion(3, 3);

        assertTrue(juego.iniciarAtaque(atacante, objetivo));

        assertEquals(0, redElectrica.getEnergia());
    }
}
