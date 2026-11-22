package edu.fiuba.algo3.modelo.terreno;

import edu.fiuba.algo3.modelo.casilla.Destino;
import edu.fiuba.algo3.modelo.tablero.Direccion;
import edu.fiuba.algo3.modelo.tablero.Posicion;
import edu.fiuba.algo3.modelo.tablero.Tablero;
import edu.fiuba.algo3.modelo.unidad.Unidad;

public interface Terreno {
    void recibir(Unidad unidad, Destino casilla);
    Terreno recibirImpacto(int danio);
    void recibirEmpuje(Unidad empujada, Destino destino);
    Posicion avanzarDisparoEnLinea(Posicion actual, Direccion direccion, Tablero tablero, int danio);
}
