package edu.fiuba.algo3.modelo.unidad.turno;

import edu.fiuba.algo3.modelo.excepciones.AccionYaRealizadaException;
import edu.fiuba.algo3.modelo.tablero.Posicion;
import edu.fiuba.algo3.modelo.tablero.Tablero;
import edu.fiuba.algo3.modelo.unidad.Mech;

public class TurnoAgotado implements EstadoTurnoMech {

    @Override
    public EstadoTurnoMech mover(Mech mech, Tablero tablero, Posicion destino) {
        throw new AccionYaRealizadaException("El turno del mech ya termino");
    }

    @Override
    public EstadoTurnoMech atacar(Mech mech) {
        throw new AccionYaRealizadaException("El turno del mech ya termino");
    }

    @Override
    public EstadoTurnoMech deshacerMovimiento(Mech mech, Tablero tablero) {
        throw new AccionYaRealizadaException("El turno del mech ya termino");
    }

}
