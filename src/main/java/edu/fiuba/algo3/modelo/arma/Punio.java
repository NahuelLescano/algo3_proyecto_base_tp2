package edu.fiuba.algo3.modelo.arma;

import edu.fiuba.algo3.modelo.Direccion;
import edu.fiuba.algo3.modelo.Posicion;
import edu.fiuba.algo3.modelo.Tablero;
import edu.fiuba.algo3.modelo.exceptions.*;

public class Punio implements Arma {
    public void atacar(Tablero tablero, Posicion origen, Posicion objetivo) {
        if (!origen.esAdyacenteA(objetivo)) throw new AtaqueInvalidoException("Ataque invalido");
        Direccion dir = origen.direccionHacia(objetivo);
        tablero.daniarEn(objetivo, 2);
        tablero.empujar(objetivo, dir);
    }
}