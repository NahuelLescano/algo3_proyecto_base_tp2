package edu.fiuba.algo3.modelo.unidad.efecto;

import edu.fiuba.algo3.modelo.unidad.Unidad;
import edu.fiuba.algo3.modelo.unidad.efecto.Efecto;

public class EfectoAcido extends Efecto {
    public EfectoAcido(){} 
    @Override 
    public void aplicarEfecto(Unidad unidad){
        unidad.contaminar();
    } 
}
