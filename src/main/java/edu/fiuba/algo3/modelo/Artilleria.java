package edu.fiuba.algo3.modelo;

public class Artilleria implements ArmaDePosicion {

    private static final int DANIO = 1;
    private static final int DISTANCIA_MINIMA = 2;

    @Override
    public void atacar(Tablero tablero, Posicion origen, Posicion objetivo) {
        if (!esObjetivoValido(origen, objetivo)) {
            return;
        }

        // La casilla objetivo recibe daño (la artillería no se frena con obstáculos)
        tablero.daniarEn(objetivo, DANIO);

        // Las 4 casillas adyacentes se empujan hacia afuera del objetivo
        for (Direccion direccion : Direccion.values()) {
            Posicion vecina = objetivo.desplazar(direccion);
            tablero.empujarUnidadEn(vecina, direccion);
        }
    }

    private boolean esObjetivoValido(Posicion origen, Posicion objetivo) {
        int dFila = Math.abs(objetivo.getFila() - origen.getFila());
        int dColumna = Math.abs(objetivo.getColumna() - origen.getColumna());

        boolean enLineaRecta = (dFila == 0) != (dColumna == 0);
        int distancia = dFila + dColumna;

        return enLineaRecta && distancia >= DISTANCIA_MINIMA;
    }
}