package edu.fiuba.algo3.modelo.arma;

import edu.fiuba.algo3.modelo.casilla.Casilla;
import edu.fiuba.algo3.modelo.excepciones.AtaqueInvalidoException;
import edu.fiuba.algo3.modelo.tablero.Direccion;
import edu.fiuba.algo3.modelo.tablero.Posicion;
import edu.fiuba.algo3.modelo.tablero.Tablero;

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
