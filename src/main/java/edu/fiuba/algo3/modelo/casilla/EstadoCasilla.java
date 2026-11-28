package edu.fiuba.algo3.modelo.casilla;

import edu.fiuba.algo3.modelo.tablero.Direccion;
import edu.fiuba.algo3.modelo.tablero.Posicion;
import edu.fiuba.algo3.modelo.tablero.Tablero;
import edu.fiuba.algo3.modelo.unidad.Unidad;
import edu.fiuba.algo3.modelo.unidad.Vek;

import java.util.List;

import edu.fiuba.algo3.modelo.arma.Disparo;
public interface EstadoCasilla {

    void ocupar(Unidad unidad, CasillaDelTablero casilla);
    void recibirImpacto(int danio, CasillaDelTablero casilla);
    void recibirEmpuje(Unidad unidad, CasillaDelTablero casilla);
    void empujarHacia(Casilla casilla);
    Posicion avanzarDisparoEnLinea(CasillaDelTablero casilla, Posicion actual, Direccion direccion, Tablero tablero, Disparo disparo);
    void recibirEmergencia(Vek vek, CasillaDelTablero casilla);
    void agregarSiEsSueloLibreA(CasillaDelTablero casilla, List<Casilla> casillas);
}
 
