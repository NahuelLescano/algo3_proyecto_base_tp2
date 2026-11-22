package edu.fiuba.algo3.modelo.partida;

public interface EstadoPartida {

    boolean estaEnCurso();

    void finalizarFaseJugador(Juego juego);

}
