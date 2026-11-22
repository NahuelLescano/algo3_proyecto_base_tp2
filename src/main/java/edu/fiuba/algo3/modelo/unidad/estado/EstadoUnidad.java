package edu.fiuba.algo3.modelo.unidad.estado;

import edu.fiuba.algo3.modelo.casilla.Casilla;
import edu.fiuba.algo3.modelo.tablero.Posicion;
import edu.fiuba.algo3.modelo.tablero.Tablero;

public interface EstadoUnidad {
    public void modificar(int danio);
    public void atacar(Tablero tablero, Posicion origen, Casilla destino);
}
