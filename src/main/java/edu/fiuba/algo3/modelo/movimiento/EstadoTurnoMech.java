package edu.fiuba.algo3.modelo.movimiento;

import edu.fiuba.algo3.modelo.excepciones.AccionInvalidaException;

/**
 * Representa el estado de las acciones que un Mech puede realizar durante un turno.
 *
 * Es el objeto responsable de decidir si el Mech todavia puede moverse, atacar o
 * deshacer el movimiento realizado. De esta forma el Mech no acumula banderas
 * sueltas y las reglas del turno quedan encapsuladas en un unico lugar.
 */
public class EstadoTurnoMech {
    private Posicion posicionAnterior;
    private Posicion posicionActual;
    private boolean seMovio;
    private boolean ataco;
    private boolean deshizoMovimiento;
    private boolean ataqueHabilitado;
    private boolean ataqueHabilitadoAntesDelMovimiento;

    public EstadoTurnoMech() {
        this.reiniciar();
    }

    public void registrarMovimiento(Posicion origen, Posicion destino, boolean ataqueHabilitado) {
        if (this.seMovio) {
            throw new AccionInvalidaException("El Mech ya se movio en este turno.");
        }
        this.posicionAnterior                   = origen;
        this.posicionActual                     = destino;
        this.ataqueHabilitadoAntesDelMovimiento = ataqueHabilitado;
        this.seMovio                            = true;
    }

    public void registrarAtaque() {
        if (!this.puedeAtacar()) {
            throw new AccionInvalidaException("El Mech no puede atacar.");
        }
        this.ataco = true;
    }

    public boolean puedeDeshacerMovimiento() {
        return this.seMovio && !this.ataco && !this.deshizoMovimiento;
    }

    public void registrarDeshacerMovimiento() {
        if (!this.puedeDeshacerMovimiento()) {
            throw new AccionInvalidaException("No es posible deshacer el movimiento en este momento.");
        }
        this.seMovio            = false;
        this.deshizoMovimiento  = true;
        this.ataqueHabilitado   = this.ataqueHabilitadoAntesDelMovimiento;
    }

    public Posicion obtenerPosicionAnterior() {
        return this.posicionAnterior;
    }

    public Posicion obtenerPosicionActual() {
        return this.posicionActual;
    }

    public boolean puedeMoverse() {
        return !this.seMovio;
    }

    public boolean puedeAtacar() {
        return this.ataqueHabilitado && !this.ataco;
    }

    public void inhabilitarAtaque() {
        this.ataqueHabilitado = false;
    }

    public void reiniciar() {
        this.posicionAnterior                   = null;
        this.posicionActual                     = null;
        this.seMovio                            = false;
        this.ataco                              = false;
        this.deshizoMovimiento                  = false;
        this.ataqueHabilitado                   = true;
        this.ataqueHabilitadoAntesDelMovimiento = true;
    }
}
