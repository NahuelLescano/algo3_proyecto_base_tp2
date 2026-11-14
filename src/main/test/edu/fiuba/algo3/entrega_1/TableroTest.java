package edu.fiuba.algo3.entrega_1;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import edu.fiuba.algo3.modelo.tablero.Posicion;
import edu.fiuba.algo3.modelo.tablero.Tablero;
import edu.fiuba.algo3.modelo.terreno.Acido;
import edu.fiuba.algo3.modelo.terreno.Agua;
import edu.fiuba.algo3.modelo.terreno.Edificio;
import edu.fiuba.algo3.modelo.terreno.Montania;
import edu.fiuba.algo3.modelo.terreno.Suelo;

public class TableroTest {

    private final String[] mapaValido = {
            "-MM-----",
            "--------",
            "---E----",
            "-X------",
            "-----E--",
            "--------",
            "--A-----",
            "-----X--"
            // - suelo M montaña E edificio X acido A agua
    };

    @Test
    public void test01DimensionTablero() {
        Tablero tablero = new Tablero(mapaValido);
        assertEquals(8, tablero.getFilas());
        assertEquals(8, tablero.getColumnas());
    }

    @Test
    public void test02CasillasValidas() {
        Tablero tablero = new Tablero(mapaValido);

        Posicion pos1 = new Posicion(0, 0);
        assertTrue(tablero.obtenerTerrenoEn(pos1) instanceof Suelo);

        Posicion pos2 = new Posicion(0, 1);
        assertTrue(tablero.obtenerTerrenoEn(pos2) instanceof Montania);

        Posicion pos3 = new Posicion(2, 3);
        assertTrue(tablero.obtenerTerrenoEn(pos3) instanceof Edificio);

        Posicion pos4 = new Posicion(7, 5);
        assertTrue(tablero.obtenerTerrenoEn(pos4) instanceof Acido);

        Posicion pos5 = new Posicion(6, 2);
        assertTrue(tablero.obtenerTerrenoEn(pos5) instanceof Agua);

    }
}
