package edu.fiuba.algo3.entrega_1;

import edu.fiuba.algo3.modelo.excepciones.AccionInvalidaException;
import edu.fiuba.algo3.modelo.tablero.ParserTablero;
import edu.fiuba.algo3.modelo.tablero.Posicion;
import edu.fiuba.algo3.modelo.tablero.Tablero;
import edu.fiuba.algo3.modelo.terreno.Suelo;
import edu.fiuba.algo3.modelo.terreno.Terreno;
import edu.fiuba.algo3.modelo.unidad.Unidad;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class SueloLibreAlAzarTableroUnitTest {

    private ParserTablero parser;

    @BeforeEach
    public void preparar() {
        parser = mock(ParserTablero.class);
        when(parser.convertirCaracterATerreno('-')).thenReturn(new Suelo());
        when(parser.convertirCaracterATerreno('M')).thenReturn(mock(Terreno.class));
        when(parser.convertirCaracterATerreno('A')).thenReturn(mock(Terreno.class));
    }

    @Test
    public void test01DevuelveLaUnicaCasillaDeSuelo() {
        Tablero tablero = new Tablero(new String[] { "M-M" }, parser);

        Posicion posicion = tablero.sueloLibreAlAzar(new Random(1));

        assertEquals(new Posicion(0, 1), posicion);
    }

    @Test
    public void test02NoDevuelveUnSueloOcupado() {
        Tablero tablero = new Tablero(new String[] { "M-M" }, parser);
        tablero.colocarUnidad(mock(Unidad.class), new Posicion(0, 1));

        assertThrows(AccionInvalidaException.class,
                () -> tablero.sueloLibreAlAzar(new Random(1)));
    }

    @Test
    public void test03SiNoHaySueloLanzaExcepcion() {
        Tablero tablero = new Tablero(new String[] { "MAM" }, parser);

        assertThrows(AccionInvalidaException.class,
                () -> tablero.sueloLibreAlAzar(new Random(1)));
    }

    @Test
    public void test04ColocandoUnidadesSeObtienenPosicionesDistintas() {
        Tablero tablero = new Tablero(new String[] { "---" }, parser);
        Random random = new Random(5);
        Set<Posicion> posiciones = new HashSet<>();

        for (int i = 0; i < 3; i++) {
            Posicion posicion = tablero.sueloLibreAlAzar(random);
            tablero.colocarUnidad(mock(Unidad.class), posicion);
            posiciones.add(posicion);
        }

        assertEquals(3, posiciones.size());
        assertThrows(AccionInvalidaException.class,
                () -> tablero.sueloLibreAlAzar(random));
    }

    @Test
    public void test05UsaElRandomRecibidoParaElegir() {
        Tablero tablero = new Tablero(new String[] { "---" }, parser);
        Random random = mock(Random.class);
        when(random.nextInt(3)).thenReturn(0);

        tablero.sueloLibreAlAzar(random);

        verify(random).nextInt(3);
    }
}