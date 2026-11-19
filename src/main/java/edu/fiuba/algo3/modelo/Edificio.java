package edu.fiuba.algo3.modelo;

import java.util.ArrayList;
import java.util.List;

public class Edificio implements Terreno, Daniable {
    private int vida;
    private List<ObservadorEdificio> observadores = new ArrayList<>();

    public Edificio(int vida) {
        this.vida = vida;
    }

    public void agregarObservador(ObservadorEdificio observador){
        this.observadores.add(observador);
    }

    public void recibir(Unidad unidad, Destino casilla){
        unidad.noPudeMoverme();
    }

    public void afectarMovimiento(Unidad unidad){

    }
    
    public Terreno recibirImpacto(int danio){
        this.recibirDanio(danio);
        if(this.vida <= 0){
            return new Suelo();
        }
        return this;
    }
    @Override
    public void recibirDanio(int danio) {
        this.vida -= danio;
        if (this.vida <= 0){
            this.observadores.forEach(ObservadorEdificio::enEdificioDestruido);
        }
    }

    public void recibirEmpuje(Unidad empujada, Destino destino){
        empujada.recibirDanio(1);
        this.recibirDanio(1);
    }

    @Override
    public Posicion avanzarDisparoEnLinea(Posicion actual, Direccion direccion, Tablero tablero, int danio) {
        tablero.daniarEn(actual, danio);
        tablero.empujar(actual, direccion);
        return null;
    }

}
