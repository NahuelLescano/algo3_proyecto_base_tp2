package edu.fiuba.algo3.modelo;

import java.util.ArrayList;
import java.util.List;

public class Edificio extends Terreno {
    private int vida;
    private final List<ObservadorEdificio> observadores = new ArrayList<>();
    private boolean destruccionNotificada = false;

    public Edificio(int vida) {
        this.vida = 1;
    }

    public void agregarObservador(ObservadorEdificio observador) {
        observadores.add(observador);
    }

    @Override
    public Terreno recibirDanio(int danio) {
        vida -= danio;

        if (estaDestruido()) {
            notificarDestruccion();
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
    
    private void notificarDestruccion() {
        if (destruccionNotificada) {
            return;
        }

        destruccionNotificada = true;

        for (ObservadorEdificio observador : observadores) {
            observador.enEdificioDestruido();
        }
    }

    @Override
    public void aplicarEfecto(Unidad unidad) {
    }
    
    @Override
    public int danioQueAfectaRedElectrica(int danio) {
        return Math.max(0, danio);
    }
    
}
