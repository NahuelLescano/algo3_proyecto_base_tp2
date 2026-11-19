package edu.fiuba.algo3.modelo.terreno;

import edu.fiuba.algo3.modelo.Destino;
import edu.fiuba.algo3.modelo.Direccion;
import edu.fiuba.algo3.modelo.Posicion;
import edu.fiuba.algo3.modelo.Tablero;
import edu.fiuba.algo3.modelo.unidad.Unidad;

public class Suelo implements Terreno {
    @Override
    public void recibir(Unidad unidad, Destino casilla){
        casilla.alojar(unidad);
    }

    public void recibirEmpuje(Unidad empujada, Destino destino){
        recibir(empujada, destino);
    }

    public Terreno recibirImpacto(int danio){
        return this;
    }
    @Override
    public Posicion avanzarDisparoEnLinea(Posicion actual, Direccion direccion, Tablero tablero, int danio) {
        return actual.desplazar(direccion);
    }
}
