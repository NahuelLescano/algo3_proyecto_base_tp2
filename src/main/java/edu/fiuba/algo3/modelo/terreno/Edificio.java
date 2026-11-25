package edu.fiuba.algo3.modelo.terreno;

import java.util.ArrayList;
import java.util.List;

import edu.fiuba.algo3.modelo.casilla.Destino;
import edu.fiuba.algo3.modelo.estructura.Daniable;
import edu.fiuba.algo3.modelo.estructura.ObservadorEdificio;
import edu.fiuba.algo3.modelo.excepciones.CasillaOcupadaException;
import edu.fiuba.algo3.modelo.tablero.Direccion;
import edu.fiuba.algo3.modelo.tablero.Posicion;
import edu.fiuba.algo3.modelo.tablero.Tablero;
import edu.fiuba.algo3.modelo.unidad.Unidad;
import edu.fiuba.algo3.modelo.arma.Disparo;
public class Edificio implements Terreno, Daniable {
    private int vida;
    private List<ObservadorEdificio> observadores = new ArrayList<>();

    public Edificio(int vida) {
        this.vida = vida;
    }

    public void agregarObservador(ObservadorEdificio observador) {
        this.observadores.add(observador);
    }

    public void recibir(Unidad unidad, Destino casilla) {
        throw new CasillaOcupadaException("Casilla ocupada por terreno no transitable");
    }

    public void afectarMovimiento(Unidad unidad) {

    }

    public Terreno recibirImpacto(int danio) {
        this.recibirDanio(danio);
        if (this.vida <= 0) {
            return new Suelo();
        }
        return this;
    }

    public void recibirEmpuje(Unidad empujada, Destino destino) {
        empujada.recibirDanio(1);
        this.recibirDanio(1);
    }

    @Override
    public void recibirDanio(int danio) {
        this.vida -= danio;
        if (this.vida <= 0) {
            this.observadores.forEach(ObservadorEdificio::enEdificioDestruido);
        }
    }

     @Override
    public Posicion avanzarDisparoEnLinea(Posicion actual, Direccion direccion, Tablero tablero, Disparo disparo) {
        tablero.daniarEn(actual, disparo.danio());
        tablero.empujar(actual, direccion);
        return null;
    }

}
