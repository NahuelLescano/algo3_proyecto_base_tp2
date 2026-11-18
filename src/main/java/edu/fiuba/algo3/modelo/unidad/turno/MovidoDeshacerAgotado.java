package edu.fiuba.algo3.modelo.unidad.turno;

import edu.fiuba.algo3.modelo.excepciones.AccionYaRealizadaException;
import edu.fiuba.algo3.modelo.tablero.Posicion;
import edu.fiuba.algo3.modelo.tablero.Tablero;
import edu.fiuba.algo3.modelo.unidad.Mech;

public class MovidoDeshacerAgotado implements EstadoTurnoMech {

    @Override
    public EstadoTurnoMech mover(Mech mech, Tablero tablero, Posicion destino) {
        // TODO: solid -> interface segregation
        throw new AccionYaRealizadaException("El mech ya se movio en este turno");
    }

    @Override
    public EstadoTurnoMech atacar(Mech mech) {
        return new TurnoAgotado();
    }

    @Override
    public EstadoTurnoMech deshacerMovimiento(Mech mech, Tablero tablero) {
        // TODO: solid -> interface segregation
        throw new AccionYaRealizadaException("No se puede deshacer el movimiento en este turno");
    }

}
