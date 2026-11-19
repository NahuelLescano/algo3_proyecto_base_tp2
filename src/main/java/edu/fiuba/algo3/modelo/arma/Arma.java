package edu.fiuba.algo3.modelo.arma;

import edu.fiuba.algo3.modelo.Posicion;
import edu.fiuba.algo3.modelo.Tablero;

public interface Arma {

    void atacar(Tablero tablero, Posicion origen, Posicion objetivo);

}
