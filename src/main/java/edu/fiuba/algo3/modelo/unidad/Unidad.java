package edu.fiuba.algo3.modelo.unidad;

import edu.fiuba.algo3.modelo.excepciones.CasillaBloqueadaException;
import edu.fiuba.algo3.modelo.tablero.Posicion;
import edu.fiuba.algo3.modelo.tablero.Tablero;

public abstract class Unidad {
    private int vidaActual;
    private Posicion posicion;

    public Unidad(int vida, Posicion posicion) {
        this.vidaActual = vida;
        this.posicion = posicion;
    }

    public void mover(Tablero tablero, Posicion destino) {
        if (!tablero.desplazarUnidad(this, destino)) {
            throw new CasillaBloqueadaException("La unidad no puede moverse a la posicion indicada");
        }
    }

    public void recibirDanio(int danio) {
        if (danio < 0) {
            // TODO: no tenemos nuestra propia excepcion?
            throw new IllegalArgumentException("El daño no puede ser negativo");
        }

        vidaActual -= danio;

        if (vidaActual < 0) {
            vidaActual = 0;
        }
    }

    public int getVidaActual() {
        return vidaActual;
    }

    public Posicion getPosicion() {
        return posicion;
    }

    public void setPosicion(Posicion posicion) {
        this.posicion = posicion;
    }

    public void actualizarPosicion(Posicion posicion) {
        setPosicion(posicion);
    }

    public boolean estaViva() {
        return vidaActual > 0;
    }

}
