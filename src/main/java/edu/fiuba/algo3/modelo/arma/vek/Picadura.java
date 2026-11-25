package edu.fiuba.algo3.modelo.arma.vek;

import edu.fiuba.algo3.modelo.excepciones.AtaqueInvalidoException;
import edu.fiuba.algo3.modelo.tablero.Posicion;
import edu.fiuba.algo3.modelo.tablero.Tablero;

public class Picadura implements AtaqueVek {

    @Override
    public void atacar(Tablero tablero, Posicion origen, Posicion objetivo) {
        if (!origen.esAdyacenteA(objetivo)) {
            throw new AtaqueInvalidoException("Ataque invalido");
        }
         tablero.daniarEn(objetivo, 1);
    }
}
