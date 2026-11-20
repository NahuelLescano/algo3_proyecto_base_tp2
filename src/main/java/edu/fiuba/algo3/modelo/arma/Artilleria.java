package edu.fiuba.algo3.modelo.arma;

import edu.fiuba.algo3.modelo.Direccion;
import edu.fiuba.algo3.modelo.Posicion;
import edu.fiuba.algo3.modelo.Tablero;

public class Artilleria implements Arma {

    private int danio = 1;
    private int distanciaMinima = 2;

    @Override
    public void atacar(Tablero tablero, Posicion origen, Posicion objetivo) {
        if (!esObjetivoValido(origen, objetivo)) {
            //throw;
        }

        tablero.daniarEn(objetivo, danio);
        
//        for (Direccion direccion : Direccion.values()) {
//            Posicion vecina = objetivo.desplazar(direccion);
//            tablero.empujar(vecina, direccion);
//        } podria encapsularse en otro metodo
    }

    private boolean esObjetivoValido(Posicion origen, Posicion objetivo) {
        int dFila = Math.abs(objetivo.getFila() - origen.getFila());
        int dColumna = Math.abs(objetivo.getColumna() - origen.getColumna());

        boolean enLineaRecta = (dFila == 0) != (dColumna == 0); // ----> mejorable (aunq no tan mal)
        int distancia = dFila + dColumna;

        return enLineaRecta && distancia >= distanciaMinima;
    } //metodo polemico
}
