package edu.fiuba.algo3.modelo.terreno.efecto;

import edu.fiuba.algo3.modelo.unidad.Unidad;
import edu.fiuba.algo3.modelo.unidad.estado.Contaminada;

public class EfectoAcido implements Efecto {
    @Override
    public void afectarTerrestre(Unidad unidad){
        contaminar(unidad);
    }
    @Override
    public void afectarVolador(Unidad unidad){

    }

    private void contaminar(Unidad unidad){
        unidad.contaminar();
    }
}
