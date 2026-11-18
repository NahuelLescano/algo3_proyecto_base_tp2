package edu.fiuba.algo3.modelo;

public class Montania extends Terreno {
    @Override
    public boolean esTransitable() {
        return false;
    }

    @Override
    public boolean bloqueaDisparos() {
        return true;
    }

    @Override
    public void aplicarEfecto(Unidad unidad) {
    }    
  
    @Override
    public Terreno recibirDanio(int danio) {
        return this;
    } // La montaña es indestructible
}
