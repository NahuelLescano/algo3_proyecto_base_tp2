package edu.fiuba.algo3.modelo;

import edu.fiuba.algo3.modelo.exceptions.*;

public class Canion implements Arma {

    private static final int DANIO = 1;

    @Override
    public void atacar(Tablero tablero, Posicion origen, Posicion objetivo) {
        if (origen.equals(objetivo)) {
            throw new AtaqueInvalidoException("Ataque invalido");
        }

        Direccion direccion = origen.direccionHacia(objetivo);
        Posicion actual = origen.desplazar(direccion);

        while (actual != null) {
            Casilla casilla = tablero.buscarCasilla(actual);
            actual = casilla.avanzarDisparoEnLinea(actual, direccion, tablero, DANIO);
        }
    }
}