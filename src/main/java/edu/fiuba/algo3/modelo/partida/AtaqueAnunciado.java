package edu.fiuba.algo3.modelo.partida;

import edu.fiuba.algo3.modelo.tablero.Direccion;
import edu.fiuba.algo3.modelo.tablero.Posicion;
import edu.fiuba.algo3.modelo.tablero.Tablero;
import edu.fiuba.algo3.modelo.unidad.Vek;

public class AtaqueAnunciado {
    private final Vek vek;
    private final Direccion direccion;

    public AtaqueAnunciado(Vek vek, Direccion direccion) {
        this.vek = vek;
        this.direccion = direccion;
    }

    public void ejecutar(Tablero tablero) {
        if (vek.getVidaActual() <= 0) {
            return;
        }

        Posicion origen = vek.posicion();
        Posicion objetivo = origen.desplazar(direccion);
        vek.ejecutarAtaque(tablero, origen, objetivo);
    }

    public Posicion obtenerCasillaObjetivo(Tablero tablero) {
        return vek.posicion().desplazar(direccion);
    }
}
