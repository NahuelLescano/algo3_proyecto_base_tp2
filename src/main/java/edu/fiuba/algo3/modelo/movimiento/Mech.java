package edu.fiuba.algo3.modelo.movimiento;

import edu.fiuba.algo3.modelo.excepciones.AccionInvalidaException;

public abstract class Mech extends Unidad {
    private boolean puedeAtacar;
    private final Arma arma;

    public Mech(int vida, int rangoMovimiento, Arma arma) {
        super(vida, rangoMovimiento, new LocomocionTerrestre());
        this.arma = arma;
        this.puedeAtacar = true;
    }

    @Override
    public void reaccionarAguaTerrestre() {
        this.puedeAtacar = false;
    }

    @Override
    public void reiniciarTurno() {
        super.reiniciarTurno();
        this.puedeAtacar = true;
    }

    public void atacar(Posicion origen, Posicion objetivo, Tablero tablero) {
        if (!this.puedeAtacar) {
            throw new AccionInvalidaException("El Mech no puede atacar");
        }
        this.arma.disparar(origen, objetivo, tablero);
        this.puedeAtacar = false;
    }

    public boolean puedeAtacar() {
        return this.puedeAtacar;
    }
}
