package edu.fiuba.algo3.modelo;


public class Punio implements ArmaDireccional {
    private static final int DANIO = 2;

    public Punio() {
    }
    
    @Override
    public void atacar(Tablero tablero, Posicion origen, Direccion direccion) {

        int fila = origen.getFila() + direccion.getDesplazamientoEnFila();
        int columna = origen.getColumna() + direccion.getDesplazamientoEnColumna();

        Posicion objetivo = new Posicion(fila, columna);

        if (tablero.estaDentroDelTablero(objetivo)) {
            tablero.resolverImpacto(objetivo, direccion, DANIO);
        }
    }
}
