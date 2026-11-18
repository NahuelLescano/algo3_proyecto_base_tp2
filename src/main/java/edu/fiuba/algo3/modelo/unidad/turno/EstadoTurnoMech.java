package edu.fiuba.algo3.modelo.unidad.turno;

import edu.fiuba.algo3.modelo.tablero.Posicion;
import edu.fiuba.algo3.modelo.tablero.Tablero;
import edu.fiuba.algo3.modelo.unidad.Mech;

public interface EstadoTurnoMech {
    EstadoTurnoMech mover(Mech mech, Tablero tablero, Posicion destino);
    EstadoTurnoMech atacar(Mech mech);
    EstadoTurnoMech deshacerMovimiento(Mech mech, Tablero tablero);
}
