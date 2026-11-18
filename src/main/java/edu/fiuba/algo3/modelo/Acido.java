package edu.fiuba.algo3.modelo;

public class Acido implements Terreno {
    public void recibir(Unidad unidad, Casilla casilla){
        casilla.alojar(unidad);
        unidad.sufrir(new EfectoAcido());
    }

    public Terreno recibirImpacto(int danio){
        return this;
    }
}
