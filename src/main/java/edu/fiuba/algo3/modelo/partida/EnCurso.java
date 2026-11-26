package edu.fiuba.algo3.modelo.partida;

public class EnCurso implements EstadoPartida {

    @Override
    public boolean estaEnCurso() {
        return true;
    }

    @Override
    public void finalizarFaseJugador(Juego juego) {
        juego.cambiarFase(new FaseResolucion());
        juego.ejecutarFaseActual();
    }
}
