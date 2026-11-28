package edu.fiuba.algo3.modelo.terreno;

import edu.fiuba.algo3.modelo.casilla.Casilla;
import edu.fiuba.algo3.modelo.casilla.Destino;
import edu.fiuba.algo3.modelo.tablero.Direccion;
import edu.fiuba.algo3.modelo.tablero.Posicion;
import edu.fiuba.algo3.modelo.tablero.Tablero;
import edu.fiuba.algo3.modelo.unidad.Unidad;

import java.util.List;

import edu.fiuba.algo3.modelo.arma.Disparo;
public interface Terreno {
    void recibir(Unidad unidad, Destino casilla);
    Terreno recibirImpacto(int danio);
    void recibirEmpuje(Unidad empujada, Destino destino);
    Posicion avanzarDisparoEnLinea(Posicion actual, Direccion direccion, Tablero tablero, Disparo disparo);
    char obtenerSimbolo();
    default void agregarSiEsSueloLibre(Casilla casilla, List<Casilla> casillas) {
}
}
