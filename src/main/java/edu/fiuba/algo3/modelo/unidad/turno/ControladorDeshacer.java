package edu.fiuba.algo3.modelo.unidad.turno;

import edu.fiuba.algo3.modelo.excepciones.AccionInvalidaException;
import edu.fiuba.algo3.modelo.tablero.Tablero;
import edu.fiuba.algo3.modelo.unidad.Mech;

import java.util.Stack;

public class ControladorDeshacer {
    private  Stack<MementoMech> historial = new Stack<>();
    private Mech mechMovido;
    private boolean yaSeDeshizoEnTurno = false;

    public void registrarMovimiento(Mech mech, MementoMech memento) {
        this.historial.clear();
        this.historial.push(memento);
        this.mechMovido = mech;
    }

    public void invalidarSiCorresponde(Mech mechQueAtaco) {
        if (this.mechMovido == mechQueAtaco) {
            this.historial.clear();
            this.mechMovido = null;
        }
    }

    public void deshacerUltimoMovimiento(Tablero tablero) {
        if (yaSeDeshizoEnTurno || mechMovido == null || historial.isEmpty()) {
            throw new AccionInvalidaException("No hay un movimiento válido para deshacer en este turno.");
        }
        mechMovido.deshacerMovimiento(tablero);
        historial.clear();
        mechMovido = null;
        yaSeDeshizoEnTurno = true;
    }

    public void reiniciarTurno() {
        historial.clear();
        mechMovido = null;
        yaSeDeshizoEnTurno = false;
    }
}
