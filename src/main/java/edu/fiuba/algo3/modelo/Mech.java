package edu.fiuba.algo3.modelo;

public class Mech extends Unidad {

    private final Arma arma;
    private final TurnoMech turno = new TurnoMech();

    public Mech(int vida, Posicion posicion, Arma arma) {
        super(vida, posicion);
        this.arma = arma;
    }

    public boolean iniciarAtaque(Tablero tablero, Posicion objetivo) {
        if (!turno.puedeAtacar()) {
            return false;
        }

        arma.atacar(tablero, getPosicion(), objetivo);
        turno.registrarAtaque();

        return true;
    }

    public void iniciarTurno() {
        turno.iniciarNuevoTurno();
    }

    @Override
    public boolean puedeMoverse() {
        return turno.puedeMoverse();
    }

    @Override
    public void registrarMovimiento() {
        turno.registrarMovimiento();
    }
}
