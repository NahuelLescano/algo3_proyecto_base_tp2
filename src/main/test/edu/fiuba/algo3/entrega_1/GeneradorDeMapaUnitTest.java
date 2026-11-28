package edu.fiuba.algo3.entrega_1;

import edu.fiuba.algo3.modelo.tablero.GeneradorDeMapa;
import edu.fiuba.algo3.modelo.tablero.ParserTablero;
import edu.fiuba.algo3.modelo.tablero.Tablero;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

public class GeneradorDeMapaUnitTest {

    private long contar(String[] mapa, char simbolo) {
        return Arrays.stream(mapa)
                .flatMapToInt(String::chars)
                .filter(c -> c == simbolo)
                .count();
    }

    @Test
    public void test01ElMapaGeneradoEsDe8x8() {
        String[] mapa = new GeneradorDeMapa(new Random(1)).generar();

        assertEquals(8, mapa.length);
        for (String fila : mapa) {
            assertEquals(8, fila.length());
        }
    }

    @Test
    public void test02ElMapaGeneradoTieneExactamente5Edificios() {
        for (long seed = 0; seed < 100; seed++) {
            String[] mapa = new GeneradorDeMapa(new Random(seed)).generar();

            assertEquals(5, contar(mapa, 'E'));
        }
    }

    @Test
    public void test03ElMapaGeneradoNoSuperaLosMaximosDeObstaculos() {
        for (long seed = 0; seed < 100; seed++) {
            String[] mapa = new GeneradorDeMapa(new Random(seed)).generar();

            assertTrue(contar(mapa, 'M') <= 6);
            assertTrue(contar(mapa, 'A') <= 6);
            assertTrue(contar(mapa, 'X') <= 4);
        }
    }

    @Test
    public void test04ElMapaGeneradoSoloContieneSimbolosValidos() {
        String[] mapa = new GeneradorDeMapa(new Random(7)).generar();

        for (String fila : mapa) {
            for (char c : fila.toCharArray()) {
                assertTrue("-MAXE".indexOf(c) >= 0);
            }
        }
    }

    @Test
    public void test05ElMismoSeedGeneraElMismoMapa() {
        String[] primero = new GeneradorDeMapa(new Random(42)).generar();
        String[] segundo = new GeneradorDeMapa(new Random(42)).generar();

        assertArrayEquals(primero, segundo);
    }

    @Test
    public void test06SeedsDistintosGeneranMapasDistintos() {
        String[] primero = new GeneradorDeMapa(new Random(1)).generar();
        String[] segundo = new GeneradorDeMapa(new Random(2)).generar();

        assertFalse(Arrays.equals(primero, segundo));
    }

    @Test
    public void test07ElMapaGeneradoSePuedeCargarEnUnTablero() {
        String[] mapa = new GeneradorDeMapa(new Random(3)).generar();

        assertDoesNotThrow(() -> new Tablero(mapa, new ParserTablero()));
    }
}