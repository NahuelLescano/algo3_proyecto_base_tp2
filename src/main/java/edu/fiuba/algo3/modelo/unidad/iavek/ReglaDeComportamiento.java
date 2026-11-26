package edu.fiuba.algo3.modelo.unidad.iavek;

import edu.fiuba.algo3.modelo.tablero.Posicion;
import edu.fiuba.algo3.modelo.tablero.Tablero;
import edu.fiuba.algo3.modelo.unidad.Vek;

public interface ReglaDeComportamiento {
    Posicion ejecutar(Vek vek, Tablero tablero);
}
