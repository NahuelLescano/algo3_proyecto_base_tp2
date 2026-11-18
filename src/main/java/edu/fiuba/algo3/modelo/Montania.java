package edu.fiuba.algo3.modelo;

public class Montania implements Terreno {
    
    public void recibir(Unidad unidad, Casilla casilla){
        unidad.noPudeMoverme();
    }

    public Terreno recibirImpacto(int danio){
        return this;
    }
}
