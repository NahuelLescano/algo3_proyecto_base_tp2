package edu.fiuba.algo3.modelo;

public class AtaqueAcido implements AtaqueVek {

    private static final int DANIO = 1;

    @Override
    public void atacar(Tablero tablero, Posicion origen, Posicion objetivo) {

        Direccion direccion = origen.direccionHacia(objetivo);

        int fila = origen.getFila()
                + direccion.getDesplazamientoEnFila();

        int columna = origen.getColumna()
                + direccion.getDesplazamientoEnColumna();

        while (tablero.estaDentroDelTablero(new Posicion(fila, columna))) {

            Posicion posicionActual = new Posicion(fila, columna);

            if (tablero.detieneDisparoEn(posicionActual)) {

                Unidad unidad = tablero.obtenerUnidadEn(posicionActual);

                tablero.daniarEn(posicionActual, DANIO);

                if (unidad != null && unidad.estaViva()) {
                    unidad.contaminar();
                }

                return;
            }

            if (posicionActual.equals(objetivo)) {
                return;
            }

            fila += direccion.getDesplazamientoEnFila();
            columna += direccion.getDesplazamientoEnColumna();
        }
    }
}
