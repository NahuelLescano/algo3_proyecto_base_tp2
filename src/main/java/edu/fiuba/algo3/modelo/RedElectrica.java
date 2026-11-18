package edu.fiuba.algo3.modelo;

public class RedElectrica implements Daniable {
    private int vida = 5;

    @Override 
    public void recibirDanio(int danio){
        this.vida -= danio;
    }

    public void registrarEdificioDestruido() {
        this.recibirDanio(1);
    }

}
