package edu.fiuba.algo3.modelo.partida;

import edu.fiuba.algo3.modelo.casilla.Casilla;
import edu.fiuba.algo3.modelo.estructura.RedElectrica;
import edu.fiuba.algo3.modelo.tablero.Tablero;
import edu.fiuba.algo3.modelo.unidad.Mech;

public class Juego {
    private Tablero tablero;
    private RedElectrica redElectrica;
    private EstadoPartida estadoPartida;
    private FaseDeTurno faseActual;

    public Juego(Tablero tablero, RedElectrica redElectrica) {
        this.tablero = tablero;
        this.redElectrica = redElectrica;

        this.tablero.registrarObservadorEnEdificios(redElectrica);

        this.estadoPartida = new EnCurso();
        this.faseActual = new FaseVek();
    }

    public void cambiarFase(FaseDeTurno nuevaFase) {
        this.faseActual = nuevaFase;
    }

    public void cambiarEstadoDePartida(EstadoPartida nuevoEstado) {
        this.estadoPartida = nuevoEstado;
    }

    public void ejecutarFaseActual() {
        if (estadoPartida.estaEnCurso()) {
            faseActual.ejecutar(this);
        }
    }

    public void finalizarFaseJugador() {
        estadoPartida.finalizarFaseJugador(this);
    }

    public void iniciarAtaque(Mech mech, Casilla objetivo) {
        mech.iniciarAtaque(objetivo, tablero);
    }

    public int getRedElectrica() { return redElectrica.getEnergia(); }
}
