package edu.fiuba.algo3.modelo.arma;

import edu.fiuba.algo3.modelo.tablero.Direccion;
import edu.fiuba.algo3.modelo.tablero.Posicion;
import edu.fiuba.algo3.modelo.tablero.Tablero;

public class Canion implements ArmaDireccional {

    private static final int DANIO = 1;

    @Override
    public void atacar(Tablero tablero, Posicion origen, Posicion objetivo) {
        atacar(tablero, origen, origen.direccionHacia(objetivo));
    }

    @Override
    public void atacar(Tablero tablero, Posicion origen, Direccion direccion) {
        int fila = origen.getFila() + direccion.getDesplazamientoEnFila();
        int columna = origen.getColumna() + direccion.getDesplazamientoEnColumna();

        while (tablero.estaDentroDelTablero(new Posicion(fila, columna))) {
            Posicion posicionActual = new Posicion(fila, columna);

            if (tablero.detieneDisparoEn(posicionActual)) {
                tablero.resolverImpacto(posicionActual, direccion, DANIO);
                return;
            }

            fila += direccion.getDesplazamientoEnFila();
            columna += direccion.getDesplazamientoEnColumna();
        }
    }
}
