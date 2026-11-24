package edu.fiuba.algo3.modelo.unidad.estado;

public class Contaminada implements EstadoUnidad{
    public int modificar(int danio){
        return danio * 2;
    }
    public boolean permiteAtacar(){
        return true;
    }
    
}
