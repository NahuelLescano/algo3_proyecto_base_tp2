package edu.fiuba.algo3.modelo;

public interface EstadoCasilla {

    void ocupar(Unidad unidad, CasillaDelTablero casilla);
    void recibirImpacto(int danio, CasillaDelTablero casilla);
    void recibirEmpuje(Unidad unidad, CasillaDelTablero casilla);
    void empujarHacia(Casilla casilla);
    Posicion avanzarDisparoEnLinea(CasillaDelTablero casilla, Posicion actual, Direccion direccion, Tablero tablero, int danio);
    
}
 