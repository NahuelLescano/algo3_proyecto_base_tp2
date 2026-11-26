package edu.fiuba.algo3.modelo.arma;

import edu.fiuba.algo3.modelo.excepciones.AtaqueInvalidoException;
import edu.fiuba.algo3.modelo.tablero.Direccion;
import edu.fiuba.algo3.modelo.tablero.Posicion;
import edu.fiuba.algo3.modelo.tablero.Tablero;

public class Punio implements Arma {

    @Override
    public void atacar(Tablero tablero, Posicion origen, Posicion objetivo) {
        if (!origen.esAdyacenteA(objetivo)) throw new AtaqueInvalidoException("Ataque invalido");
        Direccion dir = origen.direccionHacia(objetivo);
        tablero.daniarEn(objetivo, 2);
        tablero.empujar(objetivo, dir);
    }
}
