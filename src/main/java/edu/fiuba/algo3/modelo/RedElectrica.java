package edu.fiuba.algo3.modelo;

public class RedElectrica implements Daniable, ObservadorEdificio {
    private int vida = 5;

    public int getEnergia() {
        return vida;
    }

    @Override 
    public void recibirDanio(int danio){
        this.vida -= danio;
    }

    @Override 
    public void enEdificioDestruido(){
        this.recibirDanio(1);
    }

    public void registrarEdificioDestruido() {
        this.recibirDanio(1);
    }

}
