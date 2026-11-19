package edu.fiuba.algo3.modelo.unidad.turno;

import edu.fiuba.algo3.modelo.casilla.Casilla;
import edu.fiuba.algo3.modelo.unidad.estado.EstadoUnidad;

import java.util.ArrayList;
import java.util.List;

public class MementoMech {
    private final Casilla casillaAnterior;
    private final List<EstadoUnidad> estadosAnteriores;

    public MementoMech(Casilla casilla, List<EstadoUnidad> estados) {
        this.casillaAnterior = casilla;
        this.estadosAnteriores = new ArrayList<>(estados);
    }

    public Casilla getCasillaAnterior() {
        return casillaAnterior;
    }

    public List<EstadoUnidad> getEstadosAnteriores() {
        return new ArrayList<>(estadosAnteriores);
    }
}
