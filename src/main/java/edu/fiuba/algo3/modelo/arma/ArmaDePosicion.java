package edu.fiuba.algo3.modelo.arma;

import edu.fiuba.algo3.modelo.tablero.Posicion;
import edu.fiuba.algo3.modelo.tablero.Tablero;

public interface ArmaDePosicion extends Arma {

    void atacar(Tablero tablero, Posicion origen, Posicion objetivo);
}
