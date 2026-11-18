package edu.fiuba.algo3.modelo;

public class Agua implements Terreno{
    public void recibir(Unidad unidad, Destino casilla){
        casilla.alojar(unidad);
        unidad.sufrir(new EfectoAgua());
    }

    public void recibirEmpuje(Unidad empujada, Destino destino){
        recibir(empujada, destino);
    }

    public Terreno recibirImpacto(int danio){
        return this;
    }
}
