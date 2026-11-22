package edu.fiuba.algo3.modelo.arma.vek;

import edu.fiuba.algo3.modelo.casilla.Casilla;
import edu.fiuba.algo3.modelo.casilla.CasillaInexistente;
import edu.fiuba.algo3.modelo.excepciones.AtaqueInvalidoException;
import edu.fiuba.algo3.modelo.tablero.Direccion;
import edu.fiuba.algo3.modelo.tablero.Posicion;
import edu.fiuba.algo3.modelo.tablero.Tablero;

public class EscupirAcido implements AtaqueVek {

    @Override
    public void atacar(Tablero tablero, Posicion origen, Posicion objetivo) {
        if (origen.equals(objetivo)) {
            throw new AtaqueInvalidoException("Ataque invalido");
        }

        Direccion direccion = origen.direccionHacia(objetivo);
        Posicion actual = origen.desplazar(direccion);

        while (true) {
            Casilla casilla = tablero.buscarCasilla(actual);
            if (casilla instanceof CasillaInexistente) {
                throw new AtaqueInvalidoException("Sin objetivo en linea recta");
            }

            actual = actual.desplazar(direccion);
        }
    }
}
