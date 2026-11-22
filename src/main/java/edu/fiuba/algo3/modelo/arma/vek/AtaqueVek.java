package edu.fiuba.algo3.modelo.arma.vek;

import edu.fiuba.algo3.modelo.tablero.Posicion;
import edu.fiuba.algo3.modelo.tablero.Tablero;

public interface AtaqueVek {
    void atacar(Tablero tablero, Posicion origen, Posicion objetivo);
}
