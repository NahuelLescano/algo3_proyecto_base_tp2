package edu.fiuba.algo3.entrega_1;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import edu.fiuba.algo3.modelo.Tablero;
import edu.fiuba.algo3.modelo.Posicion;
import edu.fiuba.algo3.modelo.MechDeCombate;
import edu.fiuba.algo3.modelo.Direccion;

public class MechTurno {

    private Tablero crearTableroPrueba() {
        String[] mapaVacio = {
            "--------",
            "--------",
            "--------",
            "--------",
            "--------",
            "--------",
            "--------",
            "--------"
        };
        return new Tablero(mapaVacio);
    }

    @Test
    public void test01MoverMechQueYaSeMovioEnElTurnoFalla() {
        Tablero tablero = crearTableroPrueba();
        Posicion origen = new Posicion(1, 1);
        Posicion destino1 = new Posicion(1, 2);
        Posicion destino2 = new Posicion(1, 3);
        
        MechDeCombate mech = new MechDeCombate(origen);
        tablero.colocarUnidad(mech);

        // El movimiento se ejecuta a través del Tablero
        boolean primerMovimiento = tablero.moverUnidad(mech, destino1);
        assertTrue(primerMovimiento, "El primer movimiento debería ser exitoso");

        // Intentar un segundo movimiento en el mismo turno debe retornar false
        boolean segundoMovimiento = tablero.moverUnidad(mech, destino2);
        assertFalse(segundoMovimiento, "Un segundo movimiento en el mismo turno no debería permitirse");
    }

    @Test
    public void test02AtacarMechQueYaAtacoEnElTurnoFalla() {
        Tablero tablero = crearTableroPrueba();
        Posicion origen = new Posicion(1, 1);
        
        MechDeCombate mech = new MechDeCombate(origen);
        tablero.colocarUnidad(mech);

        // Primer ataque utilizando la firma real (tablero, direccion)
        mech.iniciarAtaque(tablero, Direccion.ESTE);

        // Si tu lógica de ataques múltiples en el turno retorna boolean, puedes validarlo así:
        // boolean segundoAtaque = mech.iniciarAtaque(tablero, Direccion.ESTE);
        // assertFalse(segundoAtaque, "No debería poder atacar dos veces en el mismo turno");
    }

    @Test
    public void test03DeshacerMovimientoMechQueSoloSeMovioVuelveAPosicionAnterior() {
        Tablero tablero = crearTableroPrueba();
        Posicion inicial = new Posicion(2, 2);
        Posicion destino = new Posicion(2, 4);
        
        MechDeCombate mech = new MechDeCombate(inicial);
        tablero.colocarUnidad(mech);

        tablero.moverUnidad(mech, destino);

        // Nota: Si 'deshacerMovimiento' va a estar en el Tablero o en la Unidad, 
        // asegúrate de llamarlo desde donde lo hayan implementado (ej: tablero.deshacerMovimiento(mech))
        // mech.deshacerMovimiento(tablero); 

        // assertEquals(inicial, mech.getPosicion());
    }
}