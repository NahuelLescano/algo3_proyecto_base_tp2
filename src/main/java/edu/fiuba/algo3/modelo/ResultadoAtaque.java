package edu.fiuba.algo3.modelo;

public class ResultadoAtaque {

    private final int danioAlEdificio;

    public ResultadoAtaque(int danioAlEdificio) {
        this.danioAlEdificio = danioAlEdificio;
    }

    public int getDanioAlEdificio() {
        return danioAlEdificio;
    }

    public ResultadoAtaque combinar(ResultadoAtaque otroResultado) {
        int danioTotal = this.danioAlEdificio
                + otroResultado.getDanioAlEdificio();

        return new ResultadoAtaque(danioTotal);
    }
}
