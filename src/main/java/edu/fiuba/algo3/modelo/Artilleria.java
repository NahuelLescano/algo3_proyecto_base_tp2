package edu.fiuba.algo3.modelo;

public class Artilleria implements Arma {

    private static final int DANIO = 1;
    private static final int DISTANCIA_MINIMA = 2;

    @Override
    public ResultadoAtaque atacar(Tablero tablero, Posicion origen, Posicion objetivo) {

        if (!esObjetivoValido(origen, objetivo)) {
            return new ResultadoAtaque(0);
        }

        // Resultado del daño a la casilla objetivo.
        ResultadoAtaque resultado = tablero.daniarEn(objetivo, DANIO);

        // Empuja las unidades de las 4 casillas adyacentes hacia afuera.
        for (Direccion direccion : Direccion.values()) {
            Posicion vecina = objetivo.desplazar(direccion);

            ResultadoAtaque resultadoEmpuje =
                    tablero.empujarUnidadEn(vecina, direccion);

            resultado = resultado.combinar(resultadoEmpuje);
        }

        return resultado;
    }

    private boolean esObjetivoValido(Posicion origen, Posicion objetivo) {
        int dFila = Math.abs(objetivo.getFila() - origen.getFila());
        int dColumna = Math.abs(objetivo.getColumna() - origen.getColumna());

        boolean enLineaRecta = (dFila == 0) != (dColumna == 0);
        int distancia = dFila + dColumna;

        return enLineaRecta && distancia >= DISTANCIA_MINIMA;
    }
}
