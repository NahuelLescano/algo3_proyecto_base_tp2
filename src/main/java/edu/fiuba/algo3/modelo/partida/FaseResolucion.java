package edu.fiuba.algo3.modelo.partida;

import java.util.List;

public class FaseResolucion implements FaseDeTurno {

    @Override
    public void ejecutar(Juego juego) {
        // Ejecutar los ataques anunciados en el orden en que fueron anunciados.
        // Un Vek muerto antes de su turno no ataca (verificado dentro de AtaqueAnunciado).
        List<AtaqueAnunciado> ataques = juego.getAtaquesAnunciados();

        for (AtaqueAnunciado ataque : ataques) {
            ataque.ejecutar(juego.getTablero());
        }

        juego.limpiarAtaquesAnunciados();

        // Verificar si la partida terminó después de resolver los ataques
        juego.verificarFinDePartida();

        if (juego.estaEnCurso()) {
            juego.cambiarFase(new FaseEmergencia());
        }
    }
}
