package edu.fiuba.algo3.modelo;

public class Montania implements Terreno {
    
    public void recibir(Unidad unidad, Destino casilla){
        unidad.noPudeMoverme();
    }

    public void recibirEmpuje(Unidad empujada, Destino destino){
        empujada.recibirDanio(1);
    }

    public Terreno recibirImpacto(int danio){
        return this;
    }
}
