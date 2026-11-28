package edu.fiuba.algo3.modelo.terreno;

import edu.fiuba.algo3.modelo.casilla.Casilla;
import edu.fiuba.algo3.modelo.casilla.Destino;
import edu.fiuba.algo3.modelo.tablero.Direccion;
import edu.fiuba.algo3.modelo.tablero.Posicion;
import edu.fiuba.algo3.modelo.tablero.Tablero;
import edu.fiuba.algo3.modelo.unidad.Unidad;

import java.util.List;

import edu.fiuba.algo3.modelo.arma.Disparo;

public class Suelo implements Terreno {
    @Override
    public void recibir(Unidad unidad, Destino casilla) {
        casilla.alojar(unidad);
    }

    public void recibirEmpuje(Unidad empujada, Destino destino) {
        recibir(empujada, destino);
    }

    public Terreno recibirImpacto(int danio) {
        return this;
    }

    @Override
    public Posicion avanzarDisparoEnLinea(Posicion actual, Direccion direccion, Tablero tablero, Disparo disparo) {
        return actual.desplazar(direccion);
    }
    
    @Override
    public char obtenerSimbolo() {
        return '-';
    }

    @Override
    public void agregarSiEsSueloLibre(Casilla casilla, List<Casilla> casillas) {
        casillas.add(casilla);
    }
}
