package edu.fiuba.algo3.modelo;

public class Contaminado extends EstadoUnidad {

    @Override
    public int calcularDanioRecibido(int danio) {
        return danio * 2;
    }
}
