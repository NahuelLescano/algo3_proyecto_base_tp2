package edu.fiuba.algo3.entrega_2;

import edu.fiuba.algo3.modelo.arma.vek.EscupirAcido;
import edu.fiuba.algo3.modelo.tablero.*;
import edu.fiuba.algo3.modelo.tablero.Posicion;
import edu.fiuba.algo3.modelo.tablero.Tablero;
import edu.fiuba.algo3.modelo.unidad.Mech;
import edu.fiuba.algo3.modelo.unidad.MechFactory;
import edu.fiuba.algo3.modelo.unidad.Vek;
import edu.fiuba.algo3.modelo.unidad.VekFactory;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class LuciernagaContaminacion {

    private Tablero crearTableroVacio() {
        String[] mapa = {
            "--------",
            "--------",
            "--------",
            "--------",
            "--------",
            "--------",
            "--------",
            "--------"
        };
        return new Tablero(mapa, new ParserTablero());
    }

    @Test
    void luciernagaContaminaAUnidadImpactadaYDuplicaDanioPosterior() {
        Tablero tablero = crearTableroVacio();
        VekFactory vekFactory = new VekFactory();
        MechFactory mechFactory = new MechFactory();

        Vek luciernaga = vekFactory.crearLuciernaga();
        Mech objetivo = mechFactory.crearMechDeCombate(); // vida inicial: 3

        Posicion posLuciernaga = new Posicion(2, 1);
        Posicion posObjetivo = new Posicion(2, 4);
        Posicion posApuntar = new Posicion(2, 7);

        tablero.colocarUnidad(luciernaga, posLuciernaga);
        tablero.colocarUnidad(objetivo, posObjetivo);

        new EscupirAcido().atacar(tablero, posLuciernaga, posApuntar);

        // Recibe 1 de daño del escupitajo y queda contaminada (sin empujarse)
        assertEquals(2, objetivo.getVidaActual());
        assertEquals(posObjetivo, objetivo.posicion());

        // Al recibir 1 punto de daño posterior, recibe el doble (2 puntos) por estar contaminada
        objetivo.recibirDanio(1);
        assertEquals(0, objetivo.getVidaActual());
    }

    @Test
    void contaminacionNoSeAcumulaAlImpactarDosVecesConAcido() {
        Tablero tablero = crearTableroVacio();
        VekFactory vekFactory = new VekFactory();
        MechFactory mechFactory = new MechFactory();

        Vek luciernaga1 = vekFactory.crearLuciernaga();
        Vek luciernaga2 = vekFactory.crearLuciernaga();
        Mech objetivo = mechFactory.crearMechArtillero(); // vida inicial: 2 o ajustá según tu mech

        // Usamos un mech con más vida o verificamos tras el primer impacto que ya está contaminada
        Mech mechResistente = mechFactory.crearMechDeCombate(); // vida inicial: 3

        Posicion pos1 = new Posicion(2, 0);
        Posicion pos2 = new Posicion(2, 7);
        Posicion posObjetivo = new Posicion(2, 2);

        tablero.colocarUnidad(luciernaga1, pos1);
        tablero.colocarUnidad(luciernaga2, pos2);
        tablero.colocarUnidad(mechResistente, posObjetivo);

        EscupirAcido escupirAcido = new EscupirAcido();

        // Primer impacto: 1 de daño -> vida pasa de 3 a 2 y queda contaminada
        escupirAcido.atacar(tablero, pos1, new Posicion(2, 5));
        assertEquals(2, mechResistente.getVidaActual());

        // Segundo impacto de ácido: no acumula multiplicador extra, aplica el doble de 1 (= 2) -> vida llega a 0
        escupirAcido.atacar(tablero, pos2, new Posicion(2, 0));
        assertEquals(0, mechResistente.getVidaActual());
    }
}