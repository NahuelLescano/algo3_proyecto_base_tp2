package edu.fiuba.algo3.modelo.partida;

public class FaseJugador implements FaseDeTurno {

    @Override
    public void ejecutar(Juego juego) {
        // Reiniciar turnos de los mechs al inicio de la fase del jugador
        juego.reiniciarTurnosDeMechs();
        juego.getControladorDeshacer().reiniciarTurno();
        // La fase del jugador no hace nada automáticamente.
        // El jugador realiza sus acciones manualmente (mover, atacar, deshacer).
        // La fase termina cuando el jugador invoca finalizarFaseJugador().
    }
}
