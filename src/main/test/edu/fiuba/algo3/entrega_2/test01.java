package edu.fiuba.algo3.entrega_2;

import static org.junit.jupiter.api.Assertions.assertEquals;

import edu.fiuba.algo3.modelo.*;
import org.junit.jupiter.api.Test;

public class test01 {
    private String[] mapaValido = {"--", "--"};
    @Test
        public void empujarUnidadContraOtraUnidadNoLaMueveYAmbasRecibenDanio() {
            
            Tablero tablero = new Tablero(mapaValido, new ParserTablero());
            Unidad empujada = new Vek(2 );   // vida 2
            Unidad bloqueo  = new Vek(2);   // vida 2
            tablero.colocarUnidad(empujada, new Posicion(0, 0));
            tablero.colocarUnidad(bloqueo,  new Posicion(0, 1));

            tablero.empujar(empujada, Direccion.ESTE);

            assertEquals(new Posicion(0, 0), empujada.getPosicion());   // no se movió
            assertEquals(1, empujada.getVidaActual());
            assertEquals(1, bloqueo.getVidaActual());
}    
}
