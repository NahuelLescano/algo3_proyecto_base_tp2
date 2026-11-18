package edu.fiuba.algo3.modelo;

public class Punio implements Arma {

    private static final int DANIO = 2;

    @Override
    public ResultadoAtaque atacar(Tablero tablero, Posicion origen, Posicion objetivo) {

        if (!origen.esAdyacenteA(objetivo)) {
            return new ResultadoAtaque(0);
        }

        Direccion direccion = origen.direccionHacia(objetivo);

        return tablero.resolverImpacto(objetivo, direccion, DANIO);
    }
}
