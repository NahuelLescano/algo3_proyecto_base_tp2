package edu.fiuba.algo3.modelo;

public class Juego {

    private final Tablero tablero;
    private final RedElectrica redElectrica;

    private EstadoPartida estadoPartida;
    private FaseDeTurno faseActual;

    // ==================================================
    // CONSTRUCTOR
    // ==================================================

    public Juego(Tablero tablero, RedElectrica redElectrica) {
        this.tablero = tablero;
        this.redElectrica = redElectrica;

        this.tablero.registrarObservadorEnEdificios(redElectrica);

        this.estadoPartida = new EnCurso();
        this.faseActual = new FaseVek();
    }

    // ==================================================
    // CAMBIOS DE ESTADO Y FASE
    // ==================================================

    public void cambiarFase(FaseDeTurno nuevaFase) {
        this.faseActual = nuevaFase;
    }

    public void cambiarEstadoDePartida(EstadoPartida nuevoEstado) {
        this.estadoPartida = nuevoEstado;
    }

    // ==================================================
    // EJECUCIÓN Y AVANCE DE LA PARTIDA
    // ==================================================

    public void ejecutarFaseActual() {
        if (estadoPartida.estaEnCurso()) {
            faseActual.ejecutar(this);
        }
    }

    public void finalizarFaseJugador() {
        estadoPartida.finalizarFaseJugador(this);
    }

    // ==================================================
    // ATAQUES
    // ==================================================

    public boolean iniciarAtaque(Mech mech, Posicion objetivo) {
        return mech.iniciarAtaque(tablero, objetivo);
    }
}