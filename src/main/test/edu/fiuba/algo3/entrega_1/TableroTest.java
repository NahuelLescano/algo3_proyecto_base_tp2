package edu.fiuba.algo3.entrega_1;

import edu.fiuba.algo3.modelo.tablero.ParserTablero;
import edu.fiuba.algo3.modelo.tablero.Posicion;
import edu.fiuba.algo3.modelo.tablero.Tablero;
import edu.fiuba.algo3.modelo.terreno.Acido;
import edu.fiuba.algo3.modelo.terreno.Agua;
import edu.fiuba.algo3.modelo.terreno.Edificio;
import edu.fiuba.algo3.modelo.terreno.Montania;
import edu.fiuba.algo3.modelo.terreno.Suelo;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TableroTest {

        private String[] mapaValido = {
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
    public void test01DimensionYLimitesTablero() {
        Tablero tablero = new Tablero(mapaValido, new ParserTablero());
        
        
        Posicion posicionValida = new Posicion(7, 7);
        assertNotNull(tablero.buscarCasilla(posicionValida));

    }

    @Test 
    public void test02CasillasValidas() {
        Tablero tablero = new Tablero(mapaValido, new ParserTablero());

        Posicion pos1 = new Posicion(0, 0);
        assertTrue(tablero.buscarCasilla(pos1).getTerreno() instanceof Suelo);

        Posicion pos2 = new Posicion(0, 1);
        assertTrue(tablero.buscarCasilla(pos2).getTerreno() instanceof Montania);

        Posicion pos3 = new Posicion(2, 3);
        assertTrue(tablero.buscarCasilla(pos3).getTerreno() instanceof Edificio);
        
        Posicion pos4 = new Posicion(7, 5);
        assertTrue(tablero.buscarCasilla(pos4).getTerreno() instanceof Acido);

        Posicion pos5 = new Posicion(6, 2);
        assertTrue(tablero.buscarCasilla(pos5).getTerreno() instanceof Agua);
    }    
}
