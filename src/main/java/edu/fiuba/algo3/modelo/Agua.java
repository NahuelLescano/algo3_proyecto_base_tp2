package edu.fiuba.algo3.modelo;

public class Agua implements Terreno{
    public void recibir(Unidad unidad, Casilla casilla){
        casilla.alojar(unidad);
        unidad.sufrir(new EfectoAgua());
    }
    public Terreno recibirImpacto(int danio){
        return this;
    }
}
