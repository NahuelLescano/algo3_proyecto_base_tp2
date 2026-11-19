package edu.fiuba.algo3.modelo;

public class AtaqueAvispon implements AtaqueVek {

    private static final int DANIO = 1;

    @Override
    public void atacar(Tablero tablero, Posicion origen, Posicion objetivo) {

        if (!origen.esAdyacenteA(objetivo)) {
            return;
        }

        Unidad unidad = tablero.obtenerUnidadEn(objetivo);

        if (unidad == null) {
            return;
        }

        tablero.daniarEn(objetivo, DANIO);
    }
}
