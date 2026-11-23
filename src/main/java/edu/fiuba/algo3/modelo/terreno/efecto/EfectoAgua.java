package edu.fiuba.algo3.modelo.terreno.efecto;

import edu.fiuba.algo3.modelo.unidad.Mech;
import edu.fiuba.algo3.modelo.unidad.Unidad;
import edu.fiuba.algo3.modelo.unidad.Unidad.*;
import edu.fiuba.algo3.modelo.unidad.Vek;
import edu.fiuba.algo3.modelo.unidad.estado.Sumergido;

public class EfectoAgua implements Efecto {
    @Override public void afectarTerrestre(Unidad unidad) { unidad.hundirse(); }
    @Override public void afectarVolador(Unidad unidad)   { }
}