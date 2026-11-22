package edu.fiuba.algo3.modelo.partida;

public class FaseVek implements FaseDeTurno {

    @Override
    public void ejecutar(Juego juego) {

        // Falta hacer que el Vek anuncie sus ataques

        juego.cambiarFase(new FaseJugador());
    }

}
