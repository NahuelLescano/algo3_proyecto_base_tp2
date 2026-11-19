package edu.fiuba.algo3.modelo;

import edu.fiuba.algo3.modelo.casilla.Casilla;
import edu.fiuba.algo3.modelo.exceptions.*;
import edu.fiuba.algo3.modelo.unidad.Mech;

public class Disponible implements EstadoTurnoMech {

    @Override
    public void mover(Mech mech, Casilla destino, Tablero tablero, ControladorDeshacer controlador) {
        MementoMech memento = mech.crearMemento();
        mech.ejecutarDesplazamiento(destino, tablero);
        controlador.registrarMovimiento(mech, memento);
        mech.cambiarEstadoTurno(new Movido(memento));
    }

    @Override
    public void atacar(Mech mech, Casilla objetivo, Tablero tablero, ControladorDeshacer controlador) {
        mech.iniciarAtaque(objetivo, tablero);
        mech.cambiarEstadoTurno(new Agotado());
    }

    @Override
    public void deshacerMovimiento(Mech mech, Tablero tablero) {
        throw new AccionInvalidaException("El Mech aún no se ha movido en este turno.");
    }

}