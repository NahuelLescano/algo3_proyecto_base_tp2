package edu.fiuba.algo3.modelo.partida;

public class Victoria implements EstadoPartida {

    @Override
    public boolean estaEnCurso() {
        return false;
    }

    @Override
    public void finalizarFaseJugador(Juego juego) {
        // La partida ya terminó, no hacer nada
    }
}
