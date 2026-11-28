package edu.fiuba.algo3.entrega_1;

import edu.fiuba.algo3.modelo.casilla.Casilla;
import edu.fiuba.algo3.modelo.casilla.CasillaDelTablero;
import edu.fiuba.algo3.modelo.casilla.CasillaInexistente;
import edu.fiuba.algo3.modelo.casilla.CasillaOcupada;
import edu.fiuba.algo3.modelo.casilla.CasillaVacia;
import edu.fiuba.algo3.modelo.terreno.Suelo;
import edu.fiuba.algo3.modelo.terreno.Terreno;
import edu.fiuba.algo3.modelo.unidad.Unidad;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class SueloLibreCasillasUnitTest {

    @Test
    public void test01SueloSeOfreceALaLista() {
        Suelo suelo = new Suelo();
        Casilla casilla = mock(Casilla.class);
        List<Casilla> lista = new ArrayList<>();

        suelo.agregarSiEsSueloLibre(casilla, lista);

        assertEquals(1, lista.size());
        assertSame(casilla, lista.get(0));
    }

    @Test
    public void test02CasillaVaciaLePreguntaAlTerreno() {
        CasillaVacia estado = new CasillaVacia();
        CasillaDelTablero casilla = mock(CasillaDelTablero.class);
        Terreno terreno = mock(Terreno.class);
        List<Casilla> lista = new ArrayList<>();

        when(casilla.getTerreno()).thenReturn(terreno);

        estado.agregarSiEsSueloLibreA(casilla, lista);

        verify(terreno).agregarSiEsSueloLibre(casilla, lista);
    }

    @Test
    public void test03CasillaOcupadaNoSeOfrece() {
        CasillaOcupada estado = new CasillaOcupada(mock(Unidad.class));
        CasillaDelTablero casilla = mock(CasillaDelTablero.class);
        List<Casilla> lista = new ArrayList<>();

        estado.agregarSiEsSueloLibreA(casilla, lista);

        assertTrue(lista.isEmpty());
        verifyNoInteractions(casilla);
    }

    @Test
    public void test04CasillaInexistenteNoSeOfrece() {
        List<Casilla> lista = new ArrayList<>();

        CasillaInexistente.INSTANCIA.agregarSiEsSueloLibreA(lista);

        assertTrue(lista.isEmpty());
    }

    @Test
    public void test05TerrenoSinSobrescribirNoSeOfrece() {
        Terreno obstaculo = mock(Terreno.class, CALLS_REAL_METHODS);
        Casilla casilla = mock(Casilla.class);
        List<Casilla> lista = new ArrayList<>();

        obstaculo.agregarSiEsSueloLibre(casilla, lista);

        assertTrue(lista.isEmpty());
    }
}