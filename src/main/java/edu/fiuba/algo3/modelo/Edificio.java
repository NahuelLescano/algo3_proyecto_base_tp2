package edu.fiuba.algo3.modelo;

public class Edificio extends Terreno {
    private int vida;

    public Edificio(int vida) {
        this.vida = 1;
    }

    @Override
    public Terreno recibirDanio(int danio) {
        vida -= danio;

        if (estaDestruido()) {
            return new Suelo();
        }

        return this;
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
