package edu.fiuba.algo3.modelo.arma;

import edu.fiuba.algo3.modelo.tablero.Tablero;
import edu.fiuba.algo3.modelo.tablero.Posicion;
import edu.fiuba.algo3.modelo.tablero.Direccion;

// La responsabilidad de Canon es encontrar el primer elemento alcanzado por el disparo.
public class Canion implements Arma {

    private static final int DANIO = 1;

    @Override
    public void atacar(Tablero tablero, Posicion origen, Posicion objetivo) {

        Direccion direccion = origen.direccionHacia(objetivo);

        int fila = origen.getFila() + direccion.getDesplazamientoEnFila();
        int columna = origen.getColumna() + direccion.getDesplazamientoEnColumna();

        while (tablero.estaDentroDelTablero(new Posicion(fila, columna))) {

            Posicion posicionActual = new Posicion(fila, columna);

            if (tablero.detieneDisparoEn(posicionActual)) {
                tablero.resolverImpacto(posicionActual, direccion, DANIO);
                return;
            }

            // Si encontramos el objetivo seleccionado sin impactar nada,
            // terminamos el disparo.
            if (posicionActual.equals(objetivo)) {
                return;
            }

            fila += direccion.getDesplazamientoEnFila();
            columna += direccion.getDesplazamientoEnColumna();
        }
    }
}
