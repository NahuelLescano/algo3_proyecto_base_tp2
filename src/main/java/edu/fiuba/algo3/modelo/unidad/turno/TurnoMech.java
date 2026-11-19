package edu.fiuba.algo3.modelo.unidad.turno;

public class TurnoMech {

    private boolean puedeMoverse = true;
    private boolean puedeAtacar = true;

    public boolean puedeMoverse() {
        return puedeMoverse;
    }

    public boolean puedeAtacar() {
        return puedeAtacar;
    }

    public void registrarMovimiento() {
        puedeMoverse = false;
    }

    public void registrarAtaque() {
        puedeAtacar = false;
    }

    public void iniciarNuevoTurno() {
        puedeMoverse = true;
        puedeAtacar = true;
    }
}
