package edu.fiuba.algo3.modelo.partida;

import java.util.List;

public class FaseEmergencia implements FaseDeTurno {

    @Override
    public void ejecutar(Juego juego) {
        // Resolver los puntos de emergencia: aparecen nuevos Vek
        List<PuntoEmergencia> puntos = juego.getPuntosEmergencia();

        for (PuntoEmergencia punto : puntos) {
            punto.resolverEmergencia(juego.getTablero());
            juego.agregarVek(punto.getVek());
        }

        juego.limpiarPuntosEmergencia();

        // Avanzar al siguiente turno
        juego.avanzarTurno();

        // Verificar si se completaron todos los turnos
        juego.verificarFinDePartida();

        if (juego.estaEnCurso()) {
            juego.cambiarFase(new FaseVek());
        }
    }
}
