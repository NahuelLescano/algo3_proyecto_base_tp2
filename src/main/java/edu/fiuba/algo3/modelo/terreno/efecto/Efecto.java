package edu.fiuba.algo3.modelo.terreno.efecto;

import edu.fiuba.algo3.modelo.unidad.Mech;
import edu.fiuba.algo3.modelo.unidad.Unidad;
import edu.fiuba.algo3.modelo.unidad.Vek;

public interface Efecto {
    void afectarTerrestre(Unidad unidad);
    void afectarVolador(Unidad unidad);
}
