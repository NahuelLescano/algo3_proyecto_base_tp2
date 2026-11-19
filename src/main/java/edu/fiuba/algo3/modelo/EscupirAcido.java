package edu.fiuba.algo3.modelo;

import edu.fiuba.algo3.modelo.exceptions.*;

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

            if (casilla.estaOcupada() || casilla.bloqueaDisparos()) {
                tablero.daniarEn(actual, 1);
                Unidad unidad = casilla.obtenerUnidad();
                if (unidad != null) {
                    unidad.contaminar();
                }
                return;
            }

            actual = actual.desplazar(direccion);
        }
    }
}