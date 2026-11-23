package edu.fiuba.algo3.modelo.unidad.estado;

public class Sano implements EstadoUnidad {
    public int modificar(int danio){
        return danio;
    }
    public boolean permiteAtacar(){
        return true;
    }

}
