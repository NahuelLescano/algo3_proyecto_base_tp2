package edu.fiuba.algo3.modelo;

import edu.fiuba.algo3.modelo.exceptions.*;
import edu.fiuba.algo3.modelo.*;

public class Movido implements EstadoTurnoMech {
    private final MementoMech memento;

    public Movido(MementoMech memento) {
        this.memento = memento;
    }

    @Override
    public void mover(Mech mech, Casilla destino, Tablero tablero, ControladorDeshacer controlador) {
        throw new MovimientoInvalidoException("El Mech ya se movió en este turno.");
    }

    @Override
    public void atacar(Mech mech, Casilla objetivo, Tablero tablero, ControladorDeshacer controlador) {
        mech.ejecutarAtaque(objetivo, tablero);
        controlador.invalidarSiCorresponde(mech);
        mech.cambiarEstadoTurno(new Agotado());
    }

    @Override
    public void deshacerMovimiento(Mech mech, Tablero tablero) {
        mech.restaurarDesdeMemento(memento, tablero);
        mech.cambiarEstadoTurno(new Disponible());
    }
}