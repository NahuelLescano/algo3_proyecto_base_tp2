package edu.fiuba.algo3.modelo.unidad.efecto;

import edu.fiuba.algo3.modelo.tablero.Direccion;
import edu.fiuba.algo3.modelo.tablero.Posicion;
import edu.fiuba.algo3.modelo.tablero.Tablero;

public class EfectoEmpuje extends Efecto {

    @Override
    public void postImpacto(Tablero tablero, Posicion posicion, Direccion direccion) {
        tablero.empujar(posicion, direccion);
    }
}
