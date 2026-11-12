package edu.fiuba.algo3.modelo.movimiento;

import edu.fiuba.algo3.modelo.excepciones.AccionInvalidaException;

public abstract class Mech extends Unidad {
    private final Arma arma;
    private final EstadoTurnoMech estadoTurno;

    public Mech(int vida, int rangoMovimiento, Arma arma) {
        super(vida, rangoMovimiento, new LocomocionTerrestre());
        this.arma        = arma;
        this.estadoTurno = new EstadoTurnoMech();
    }

    @Override
    public void reaccionarAguaTerrestre() {
        this.estadoTurno.inhabilitarAtaque();
    }

    @Override
    public void reiniciarTurno() {
        super.reiniciarTurno();
        this.estadoTurno.reiniciar();
    }

    @Override
    public void trasladarseA(Casilla origen, Casilla destino) {
        boolean podiaAtacar = this.estadoTurno.puedeAtacar();
        super.trasladarseA(origen, destino);
        this.estadoTurno.registrarMovimiento(
                origen.obtenerPosicion(),
                destino.obtenerPosicion(),
                podiaAtacar);
    }

    public void atacar(Posicion origen, Posicion objetivo, Tablero tablero) {
        if (!this.estadoTurno.puedeAtacar()) {
            throw new AccionInvalidaException("El Mech no puede atacar");
        }
        this.arma.disparar(origen, objetivo, tablero);
        this.estadoTurno.registrarAtaque();
    }

    public void deshacerMovimiento(Tablero tablero) {
        if (!this.estadoTurno.puedeDeshacerMovimiento()) {
            throw new AccionInvalidaException("No es posible deshacer el movimiento en este momento.");
        }

        tablero.reubicarUnidad(
                this,
                this.estadoTurno.obtenerPosicionActual(),
                this.estadoTurno.obtenerPosicionAnterior());

        this.estadoTurno.registrarDeshacerMovimiento();
        this.habilitarMovimiento();
    }

    public boolean puedeAtacar() {
        return this.estadoTurno.puedeAtacar();
    }

    public boolean puedeDeshacerMovimiento() {
        return this.estadoTurno.puedeDeshacerMovimiento();
    }
}
