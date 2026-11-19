package edu.fiuba.algo3.modelo.arma.vek;

import edu.fiuba.algo3.modelo.Posicion;
import edu.fiuba.algo3.modelo.Tablero;

public interface AtaqueVek {
    void atacar(Tablero tablero, Posicion origen, Posicion objetivo);
}
