package edu.fiuba.algo3.modelo;

public class ResultadoDanio {

    private final Unidad unidadSobreviviente;
    private final int danioAlEdificio;

    public ResultadoDanio(Unidad unidadSobreviviente, int danioAlEdificio) {
        this.unidadSobreviviente = unidadSobreviviente;
        this.danioAlEdificio = danioAlEdificio;
    }

    public Unidad getUnidadSobreviviente() {
        return unidadSobreviviente;
    }

    public int getDanioAlEdificio() {
        return danioAlEdificio;
    }
}
