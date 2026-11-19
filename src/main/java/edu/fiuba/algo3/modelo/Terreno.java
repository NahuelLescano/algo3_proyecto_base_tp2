package edu.fiuba.algo3.modelo;

public interface Terreno {
    void recibir(Unidad unidad, Destino casilla);
    Terreno recibirImpacto(int danio);
    void recibirEmpuje(Unidad empujada, Destino destino);
    Posicion avanzarDisparoEnLinea(Posicion actual, Direccion direccion, Tablero tablero, int danio); 
    
}
