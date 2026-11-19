package edu.fiuba.algo3.modelo.unidad;

import edu.fiuba.algo3.modelo.terreno.efecto.Efecto;

public interface Desplazamiento {
    public void afectar(Efecto efecto, Unidad unidad);
}
