package edu.fiuba.algo3.modelo.arma;

import edu.fiuba.algo3.modelo.excepciones.AtaqueInvalidoException;
import edu.fiuba.algo3.modelo.tablero.Direccion;
import edu.fiuba.algo3.modelo.tablero.Posicion;
import edu.fiuba.algo3.modelo.tablero.Tablero;
import edu.fiuba.algo3.modelo.unidad.efecto.*;

public class Canion implements Arma {

    private Disparo disparo; 

    public Canion(Disparo disparo){
        this.disparo = disparo;
    }

    @Override
    public void atacar(Tablero tablero, Posicion origen, Posicion objetivo) {
        if (origen.equals(objetivo)) {
            throw new AtaqueInvalidoException("Ataque invalido");
        }

        Direccion direccion = origen.direccionHacia(objetivo);
        Posicion actual = origen.desplazar(direccion);

        while (actual != null) {
            actual = tablero.buscarCasilla(actual)
                            .avanzarDisparoEnLinea(actual, direccion, tablero, disparo);
        }
    }
}
