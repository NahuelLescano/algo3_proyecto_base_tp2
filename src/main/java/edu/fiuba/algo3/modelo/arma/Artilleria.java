package edu.fiuba.algo3.modelo.arma;

import edu.fiuba.algo3.modelo.tablero.Direccion;
import edu.fiuba.algo3.modelo.tablero.Posicion;
import edu.fiuba.algo3.modelo.tablero.Tablero;
import edu.fiuba.algo3.modelo.excepciones.AtaqueInvalidoException;

public class Artilleria implements Arma {

    private int danio = 1;
    private int distanciaMinima = 2;

    @Override
    public void atacar(Tablero tablero, Posicion origen, Posicion objetivo) {
        if (!esObjetivoValido(origen, objetivo)) {
            throw new AtaqueInvalidoException("Objetivo invalido para artilleria");
        }

        tablero.daniarEn(objetivo, danio);

        for (Direccion direccion : Direccion.values()) {
            Posicion vecina = objetivo.desplazar(direccion);
            tablero.empujar(vecina, direccion);
        }
    }

    private boolean esObjetivoValido(Posicion origen, Posicion objetivo) {
        return origen.estaAlineadaCon(objetivo)
                && origen.distanciaA(objetivo) >= distanciaMinima;
    }
}
