package edu.fiuba.algo3.modelo;

import edu.fiuba.algo3.modelo.casilla.Casilla;

public interface EstadoUnidad {
    public void modificar(int danio);
    public void atacar(Tablero tablero, Posicion origen, Casilla destino);
}
