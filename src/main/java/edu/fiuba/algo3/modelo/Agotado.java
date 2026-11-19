package edu.fiuba.algo3.modelo;

import edu.fiuba.algo3.modelo.casilla.Casilla;
import edu.fiuba.algo3.modelo.exceptions.*;
import edu.fiuba.algo3.modelo.unidad.Mech;

public class Agotado implements EstadoTurnoMech {

    @Override
    public void mover(Mech mech, Casilla destino, Tablero tablero, ControladorDeshacer controlador) {
        throw new MovimientoInvalidoException("El Mech ya completó sus acciones en este turno.");
    }

    @Override
    public void atacar(Mech mech, Casilla objetivo, Tablero tablero, ControladorDeshacer controlador) {
        throw new AtaqueInvalidoException("El Mech ya atacó en este turno.");
    }

    @Override
    public void deshacerMovimiento(Mech mech, Tablero tablero) {
        throw new AccionInvalidaException("No se puede deshacer el movimiento después de haber atacado.");
    }

}