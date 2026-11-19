package edu.fiuba.algo3.modelo;

public interface Casilla extends Destino {
    Terreno getTerreno();
    Posicion getPosicion();
    void ocupar(Unidad unidad);                 
    void recibirEmpuje(Unidad empujada);
    void empujarHacia(Casilla destino);
    void recibirImpacto(int danio);
    void desocupar();
    Posicion avanzarDisparoEnLinea(Posicion actual, Direccion direccion, Tablero tablero, int danio);
}
