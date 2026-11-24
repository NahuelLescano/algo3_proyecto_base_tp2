package edu.fiuba.algo3.modelo.casilla;

import edu.fiuba.algo3.modelo.tablero.Direccion;
import edu.fiuba.algo3.modelo.tablero.Posicion;
import edu.fiuba.algo3.modelo.tablero.Tablero;
import edu.fiuba.algo3.modelo.terreno.Terreno;
import edu.fiuba.algo3.modelo.unidad.Unidad;
import edu.fiuba.algo3.modelo.arma.Disparo;

public interface Casilla extends Destino {
    Terreno getTerreno();
    Posicion getPosicion();
    void ocupar(Unidad unidad);
    void recibirEmpuje(Unidad empujada);
    void empujarHacia(Casilla destino);
    void recibirImpacto(int danio);
    void desocupar();
    Posicion avanzarDisparoEnLinea(Posicion actual, Direccion direccion, Tablero tablero, Disparo disparo);
}
