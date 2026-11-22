package edu.fiuba.algo3.modelo.unidad.turno;

import edu.fiuba.algo3.modelo.casilla.Casilla;
import edu.fiuba.algo3.modelo.excepciones.MovimientoInvalidoException;
import edu.fiuba.algo3.modelo.tablero.Tablero;
import edu.fiuba.algo3.modelo.unidad.Mech;

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
        mech.iniciarAtaque(objetivo, tablero);
        controlador.invalidarSiCorresponde(mech);
        mech.cambiarEstadoTurno(new Agotado());
    }

    @Override
    public void deshacerMovimiento(Mech mech, Tablero tablero) {
        mech.restaurarDesdeMemento(memento, tablero);
        mech.cambiarEstadoTurno(new Disponible());
    }
}
