package edu.fiuba.algo3.modelo.unidad;

import edu.fiuba.algo3.modelo.arma.Canion;
import edu.fiuba.algo3.modelo.tablero.Posicion;

public class MechFactory {

    public Mech crearMechDeCombate(Posicion posicion) {
        return new MechDeCombate(posicion);
    }

    public Mech crearMechCanon(Posicion posicion) {
        return new MechCanion(posicion, new Canion());
    }

    public Mech crearMechArtillero(Posicion posicion) {
        return new MechArtilleria(posicion);
    }
}
