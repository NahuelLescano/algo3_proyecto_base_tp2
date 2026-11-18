package edu.fiuba.algo3.modelo;

import java.util.ArrayList;
import java.util.List;

public class Edificio implements Terreno, Daniable {
    private int vida;
    private List<ObservadorEdificio> observadores = new ArrayList<>();

    public Edificio(int vida) {
        this.vida = vida;
    }

    public void agregarObserevador(ObservadorEdificio observador){
        this.observadores.add(observador);
    }

    public void recibir(Unidad unidad, Casilla casilla){
        unidad.noPudeMoverme();
    }

    public void afectarMovimiento(Unidad unidad){

    }
    
    public Terreno recibirImpacto(int danio){
        return new Suelo();
    }
    @Override
    public void recibirDanio(int danio) {
        this.vida -= danio;
        if (this.vida <= 0){
            this.observadores.forEach(ObservadorEdificio::enEdificioDestruido);
        }
    }
    /*
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
     */


}
