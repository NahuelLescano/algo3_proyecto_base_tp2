package edu.fiuba.algo3.modelo;

public class Suelo implements  Terreno {
    @Override
    public void recibir(Unidad unidad, Casilla casilla){
        casilla.alojar(unidad);
    }

    public Terreno recibirImpacto(int danio){
        return this;
    }
}
