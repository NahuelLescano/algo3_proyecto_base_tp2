package edu.fiuba.algo3.modelo;

public abstract class Mech extends Unidad {
    private final Arma arma;
    private boolean yaSeMovio = false;

    public Mech(int vida, Posicion posicion, Arma arma) {
        super(vida, posicion);
        this.arma = arma;
    }

    public boolean yaSeMovio() {
        return yaSeMovio;
    }

    void marcarComoMovido() {
        yaSeMovio = true;
    }

    public void iniciarTurno() {
        yaSeMovio = false;
    }
    
    @Override
    public boolean puedeMoverse() {
        return !yaSeMovio;
    }

    @Override
    public void registrarMovimiento() {
        marcarComoMovido();
    }
}
