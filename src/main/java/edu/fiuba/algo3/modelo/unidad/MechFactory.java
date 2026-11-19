package edu.fiuba.algo3.modelo.unidad;

import edu.fiuba.algo3.modelo.arma.Artilleria;
import edu.fiuba.algo3.modelo.arma.Canion;
import edu.fiuba.algo3.modelo.arma.Punio;

public class MechFactory {

    public Mech crearMechDeCombate() {
        return new Mech(3, new Punio(), new Terrestre());
    }

    public Mech crearMechCanon() {
        return new Mech(3, new Canion(), new Terrestre());
    }

    public Mech crearMechArtillero() {
        return new Mech(2, new Artilleria(), new Terrestre());
    }
}
