package edu.fiuba.algo3.modelo.unidad.efecto;

import edu.fiuba.algo3.modelo.tablero.Direccion;
import edu.fiuba.algo3.modelo.tablero.Posicion;
import edu.fiuba.algo3.modelo.tablero.Tablero;
import edu.fiuba.algo3.modelo.unidad.Unidad;

public abstract class Efecto {
  public void aplicarEfecto(Unidad unidad){}
  public void postImpacto(Tablero tablero, Posicion posicion, Direccion direccion) {}    
}
