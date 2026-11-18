package edu.fiuba.algo3.modelo.unidad.turno;

import edu.fiuba.algo3.modelo.excepciones.AccionYaRealizadaException;
import edu.fiuba.algo3.modelo.excepciones.CasillaBloqueadaException;
import edu.fiuba.algo3.modelo.tablero.Posicion;
import edu.fiuba.algo3.modelo.tablero.Tablero;
import edu.fiuba.algo3.modelo.unidad.Mech;

public class PuedeMoverYAtacar implements EstadoTurnoMech {

    @Override
    public EstadoTurnoMech mover(Mech mech, Tablero tablero, Posicion destino) {
        Posicion origen = mech.getPosicion(); // TODO: por que hacemos un `getPosicion` para tomar una decision
        if (!tablero.desplazarUnidad(mech, destino)) {
            throw new CasillaBloqueadaException("El mech no puede moverse hasta la posicion indicada");
        }
        return new MovidoSinAtacar(origen);
    }

    @Override
    public EstadoTurnoMech atacar(Mech mech) {
        return new AtacoSinMoverse();
    }

    @Override
    public EstadoTurnoMech deshacerMovimiento(Mech mech, Tablero tablero) {
        // TODO: solid -> interface segregation
        throw new AccionYaRealizadaException("El mech no se movio en este turno");
    }
}
