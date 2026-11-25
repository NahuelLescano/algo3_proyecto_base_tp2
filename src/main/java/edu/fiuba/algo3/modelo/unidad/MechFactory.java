package edu.fiuba.algo3.modelo.unidad;

import edu.fiuba.algo3.modelo.arma.Artilleria;
import edu.fiuba.algo3.modelo.arma.Canion;
import edu.fiuba.algo3.modelo.arma.Disparo;
import edu.fiuba.algo3.modelo.arma.Punio;
import edu.fiuba.algo3.modelo.unidad.efecto.EfectoEmpuje;

public class MechFactory {

    public Mech crearMechDeCombate() {
        return new Mech(3, new Punio(), new Terrestre());
    }

    public Mech crearMechCanon() {
        Canion canion = new Canion(new Disparo(1, new EfectoEmpuje()));
        return new Mech(3, canion, new Terrestre());
    }

    public Mech crearMechArtillero() {
        return new Mech(2, new Artilleria(), new Terrestre());
    }
}
