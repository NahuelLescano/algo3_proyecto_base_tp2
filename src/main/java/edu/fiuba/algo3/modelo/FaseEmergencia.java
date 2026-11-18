package edu.fiuba.algo3.modelo;

public class FaseEmergencia implements FaseDeTurno {

    @Override
    public void ejecutar(Juego juego) {

        // Hay que gestionar la aparición de nuevos Vek

        juego.cambiarFase(new FaseVek());
    }

}
