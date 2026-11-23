package edu.fiuba.algo3.modelo.unidad.estado;

public class Sumergido implements EstadoUnidad{
    public int modificar(int danio){
        return danio;
    }
    public boolean permiteAtacar(){
        return false;
    }
}