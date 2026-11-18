package edu.fiuba.algo3.modelo.arma;

import edu.fiuba.algo3.modelo.tablero.Direccion;
import edu.fiuba.algo3.modelo.tablero.Posicion;
import edu.fiuba.algo3.modelo.tablero.Tablero;

public class Punio implements Arma {

    private static final int DANIO = 2;

    @Override
    public void atacar(Tablero tablero, Posicion origen, Posicion objetivo) {
    
        if (!origen.esAdyacenteA(objetivo)) {
           return;
        }

        Direccion direccion = origen.direccionHacia(objetivo);

        tablero.resolverImpacto(objetivo, direccion, DANIO);
    }
}
