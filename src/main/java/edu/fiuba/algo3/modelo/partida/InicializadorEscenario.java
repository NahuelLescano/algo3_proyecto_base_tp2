package edu.fiuba.algo3.modelo.partida;

import java.util.ArrayList;
import java.util.List;

import edu.fiuba.algo3.modelo.tablero.Tablero;
import edu.fiuba.algo3.modelo.tablero.Posicion;
import edu.fiuba.algo3.modelo.unidad.Mech;
import edu.fiuba.algo3.modelo.unidad.MechFactory;

public class InicializadorEscenario {

    private Tablero tablero;
    private MechFactory fabrica;

    public InicializadorEscenario(Tablero tablero) {
        this.tablero = tablero;
        this.fabrica = new MechFactory();
    }

    public List<Mech> inicializar() {

        List<Mech> mechs = new ArrayList<>();

        Mech combate = fabrica.crearMechDeCombate();
        Mech canon = fabrica.crearMechCanon();
        Mech artillero = fabrica.crearMechArtillero();

        tablero.colocarUnidad(combate, new Posicion(0, 0));
        tablero.colocarUnidad(canon, new Posicion(0, 1));
        tablero.colocarUnidad(artillero, new Posicion(0, 2));

        mechs.add(combate);
        mechs.add(canon);
        mechs.add(artillero);

        return mechs;
    }
}
