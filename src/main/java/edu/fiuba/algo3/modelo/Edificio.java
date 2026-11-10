package edu.fiuba.algo3.modelo;

public class Edificio extends Terreno {
    private int vida;

    public Edificio(int vida) {
        this.vida = vida;
    }

    public void recibirDanio(int danio) {
        this.vida -= danio;
    }

    public boolean estaDestruido() {
        return this.vida <= 0;
    }
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
    
}
