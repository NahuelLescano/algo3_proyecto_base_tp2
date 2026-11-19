package edu.fiuba.algo3.modelo;

public class FaseResolucion implements FaseDeTurno {

    @Override
    public void ejecutar(Juego juego) {

        // Hay que resolver los ataques anunciados por los Vek.

        juego.cambiarFase(new FaseEmergencia());
    }

}