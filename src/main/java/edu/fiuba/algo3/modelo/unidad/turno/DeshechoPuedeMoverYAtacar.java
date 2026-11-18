package edu.fiuba.algo3.modelo.unidad.turno;

import edu.fiuba.algo3.modelo.excepciones.AccionYaRealizadaException;
import edu.fiuba.algo3.modelo.excepciones.CasillaBloqueadaException;
import edu.fiuba.algo3.modelo.tablero.Posicion;
import edu.fiuba.algo3.modelo.tablero.Tablero;
import edu.fiuba.algo3.modelo.unidad.Mech;

public class DeshechoPuedeMoverYAtacar implements EstadoTurnoMech {

    @Override
    public EstadoTurnoMech mover(Mech mech, Tablero tablero, Posicion destino) {
        if (!tablero.desplazarUnidad(mech, destino)) {
            throw new CasillaBloqueadaException("El mech no puede moverse a la posicion indicada");
        }
        return new MovidoDeshacerAgotado();
    }

    @Override
    public EstadoTurnoMech atacar(Mech mech) {
        return new AtacoSinMoverse();
    }

    @Override
    public EstadoTurnoMech deshacerMovimiento(Mech mech, Tablero tablero) {
        // TODO: solid -> interface segregation
        throw new AccionYaRealizadaException("El movimiento ya fue deshecho en este turno");
    }

}
