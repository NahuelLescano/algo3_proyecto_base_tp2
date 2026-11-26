package edu.fiuba.algo3.modelo.arma;

import edu.fiuba.algo3.modelo.tablero.Direccion;
import edu.fiuba.algo3.modelo.tablero.Posicion;
import edu.fiuba.algo3.modelo.tablero.Tablero;
import edu.fiuba.algo3.modelo.unidad.*;
import edu.fiuba.algo3.modelo.unidad.efecto.Efecto;

public class Disparo {
    private final int danio;
    private final Efecto efecto;

    public Disparo(int danio, Efecto efecto) {
        this.danio = danio;
        this.efecto = efecto;
    }

    public void impactarA(Unidad unidad) {
        unidad.recibirDanio(danio);   
        efecto.aplicarEfecto(unidad);     
    }

    public int danio() { return danio; }

    public void resolverPostImpacto(Tablero tablero, Posicion posicion, Direccion direccion) {
        efecto.postImpacto(tablero, posicion, direccion);
    }
}
