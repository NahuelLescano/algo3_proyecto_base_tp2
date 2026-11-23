package edu.fiuba.algo3.modelo.unidad;

import edu.fiuba.algo3.modelo.terreno.efecto.Efecto;

    public class Terrestre implements Desplazamiento {
        public void afectar(Efecto efecto, Unidad unidad){
            efecto.afectarTerrestre(unidad);
        }
    }
