package edu.fiuba.algo3.modelo;

import edu.fiuba.algo3.modelo.exceptions.*;
import edu.fiuba.algo3.modelo.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

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