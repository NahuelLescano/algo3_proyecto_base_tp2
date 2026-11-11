package edu.fiuba.algo3.modelo;

// La responsabilidad de Canion es encontrar el primer elemento alcanzado por el disparo
public class Canion implements Arma {

    private static final int DANIO = 1;

    @Override
    public void atacar(Tablero tablero, Posicion origen, Direccion direccion) {

        int fila = origen.getFila() + direccion.getDesplazamientoEnFila();
        int columna = origen.getColumna() + direccion.getDesplazamientoEnColumna();

        while (tablero.estaDentroDelTablero(new Posicion(fila, columna))) {

            Posicion posicionActual = new Posicion(fila, columna);

            // Preguntamos si en la casilla actual encontramos algo que detenga el disparo; o hay una unidad o el terreno detiene el disparo. Delegamos a tablero la resolución del impacto
            if (tablero.hayUnidad(posicionActual) ||
                tablero.bloqueaDisparos(posicionActual)) {

                tablero.resolverImpacto(posicionActual, direccion, DANIO);
                return;
            }

            // Si la casilla actual no tiene obstáculos se avanza hacia la siguiente
            fila += direccion.getDesplazamientoEnFila();
            columna += direccion.getDesplazamientoEnColumna();
        }
    }
}
