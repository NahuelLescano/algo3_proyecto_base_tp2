package edu.fiuba.algo3.entrega_1;

import edu.fiuba.algo3.modelo.Tablero;
import edu.fiuba.algo3.modelo.Casilla;
import edu.fiuba.algo3.modelo.Posicion;
import edu.fiuba.algo3.modelo.Suelo;
import edu.fiuba.algo3.modelo.Montania;
import edu.fiuba.algo3.modelo.Agua;
import edu.fiuba.algo3.modelo.Acido;
import edu.fiuba.algo3.modelo.Edificio;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

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
            Casilla casillaSuelo = tablero.getCasilla(pos1);
            assertTrue(casillaSuelo.getTerreno() instanceof Suelo);

            Posicion pos2 = new Posicion(0, 1);
            Casilla casillaMontania = tablero.getCasilla(pos2);
            assertTrue(casillaMontania.getTerreno() instanceof Montania);

            Posicion pos3 = new Posicion(2, 3);
            Casilla casillaEdificio = tablero.getCasilla(pos3);
            assertTrue(casillaEdificio.getTerreno() instanceof Edificio);
            
            Posicion pos4 = new Posicion(7, 5);
            Casilla casillaAcido = tablero.getCasilla(pos4);
            assertTrue(casillaAcido.getTerreno() instanceof Acido);
            
            Posicion pos5 = new Posicion(6, 2);
            Casilla casillaAgua = tablero.getCasilla(pos5);
            assertTrue(casillaAgua.getTerreno() instanceof Agua);
            
        }    
}
