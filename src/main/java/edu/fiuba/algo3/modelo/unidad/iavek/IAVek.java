package edu.fiuba.algo3.modelo.unidad.iavek;

import edu.fiuba.algo3.modelo.tablero.Posicion;
import edu.fiuba.algo3.modelo.tablero.Tablero;
import edu.fiuba.algo3.modelo.unidad.Vek;

import java.util.List;

public class IAVek {

    private List<ReglaDeComportamiento> reglas;

    public IAVek() {
        reglas = List.of(
                new ReglaAtacarEdificio(),
                new ReglaAtacarMech(),
                new ReglaAcercarseEdificio()
        );
    }

    public Posicion decidirMovimiento(Vek vek, Tablero tablero) {

        for (ReglaDeComportamiento regla : reglas) {
            Posicion posicion = regla.ejecutar(vek, tablero);

            if (posicion != null) {
                return posicion;
            }
        }

        return vek.posicion();
    }
}