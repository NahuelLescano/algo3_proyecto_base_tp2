package edu.fiuba.algo3.modelo;

public class MechFactory {

    public Mech crearMechDeCombate(Posicion posicion) {
        return new Mech(3, posicion, new Punio());
    }

    public Mech crearMechCanon(Posicion posicion) {
        return new Mech(3, posicion, new Canion());
    }

    public Mech crearMechArtillero(Posicion posicion) {
        return new Mech(2, posicion, new Artilleria());
    }
}
