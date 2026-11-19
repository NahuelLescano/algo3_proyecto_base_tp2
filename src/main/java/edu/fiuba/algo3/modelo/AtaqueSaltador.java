package edu.fiuba.algo3.modelo;

public class AtaqueSaltador implements AtaqueVek {

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
