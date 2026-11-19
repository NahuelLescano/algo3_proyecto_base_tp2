package edu.fiuba.algo3.modelo;

import edu.fiuba.algo3.modelo.casilla.Casilla;
import edu.fiuba.algo3.modelo.unidad.Mech;

public interface EstadoTurnoMech {
    void mover(Mech mech, Casilla destino, Tablero tablero, ControladorDeshacer controlador);
    void atacar(Mech mech, Casilla objetivo, Tablero tablero, ControladorDeshacer controlador);
    void deshacerMovimiento(Mech mech, Tablero tablero);
}