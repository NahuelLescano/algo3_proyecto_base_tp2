package edu.fiuba.algo3.modelo.unidad.turno;

import edu.fiuba.algo3.modelo.excepciones.AccionYaRealizadaException;
import edu.fiuba.algo3.modelo.tablero.Posicion;
import edu.fiuba.algo3.modelo.tablero.Tablero;
import edu.fiuba.algo3.modelo.unidad.Mech;

public class AtacoSinMoverse implements EstadoTurnoMech {
    // TODO: solid  -> interface segregation

    @Override
    public EstadoTurnoMech mover(Mech mech, Tablero tablero, Posicion destino) {
        throw new AccionYaRealizadaException("El mech ya ataco en este turno");
    }

    @Override
    public EstadoTurnoMech atacar(Mech mech) {
        throw new AccionYaRealizadaException("El mech ya ataco en este turno");
    }

    @Override
    public EstadoTurnoMech deshacerMovimiento(Mech mech, Tablero tablero) {
        throw new AccionYaRealizadaException("El mech ya ataco en este turno");
    }

}
