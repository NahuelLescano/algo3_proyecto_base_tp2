package edu.fiuba.algo3.entrega_1;
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

            Posicion pos3 = new Posicion(3, 4);
            Casilla casillaEdificio = tablero.getCasilla(pos3);
            assertTrue(casillaMontana.getTerreno() instanceof Edificio);
            
        }    
}
