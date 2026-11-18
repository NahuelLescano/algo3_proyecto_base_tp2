package edu.fiuba.algo3.modelo;

public class ResultadoInicioAtaque {

    private final boolean ataqueRealizado;
    private final ResultadoAtaque resultadoAtaque;

    public ResultadoInicioAtaque(boolean ataqueRealizado, ResultadoAtaque resultadoAtaque) {
        this.ataqueRealizado = ataqueRealizado;
        this.resultadoAtaque = resultadoAtaque;
    }

    public boolean fueRealizado() {
        return ataqueRealizado;
    }

    public ResultadoAtaque getResultadoAtaque() {
        return resultadoAtaque;
    }
}
