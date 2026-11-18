package edu.fiuba.algo3.modelo.unidad;

import edu.fiuba.algo3.modelo.arma.Arma;
import edu.fiuba.algo3.modelo.excepciones.AccionYaRealizadaException;
import edu.fiuba.algo3.modelo.tablero.Posicion;
import edu.fiuba.algo3.modelo.tablero.Tablero;
import edu.fiuba.algo3.modelo.unidad.turno.EstadoTurnoMech;
import edu.fiuba.algo3.modelo.unidad.turno.PuedeMoverYAtacar;

public abstract class Mech extends Unidad {
    private Arma arma;
    private EstadoTurnoMech estadoTurno;

    public Mech(int vida, Posicion posicion, Arma arma) {
        super(vida, posicion);
        this.arma = arma;
        this.estadoTurno = new PuedeMoverYAtacar();
    }

    @Override
    public void mover(Tablero tablero, Posicion destino) {
        this.estadoTurno = estadoTurno.mover(this, tablero, destino);
    }

    public void deshacerMovimiento(Tablero tablero) {
        this.estadoTurno = this.estadoTurno.deshacerMovimiento(this, tablero);
    }

    protected boolean consumirAtaque() {
        try {
            this.estadoTurno = estadoTurno.atacar(this);
            return true;
        } catch (AccionYaRealizadaException e) {
            return false;
        }
    }

    // TODO: refactor
    protected Arma getArma() {
        return this.arma;
    }
}
