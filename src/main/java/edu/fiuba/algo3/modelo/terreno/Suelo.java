package edu.fiuba.algo3.modelo.terreno;

import edu.fiuba.algo3.modelo.unidad.Unidad;

public class Suelo implements Terreno {
    @Override
    public void recibir(Unidad unidad, Destino casilla) {
        casilla.alojar(unidad);
    }

    public void recibirEmpuje(Unidad empujada, Destino destino) {
        recibir(empujada, destino);
    }

    public Terreno recibirImpacto(int danio) {
        return this;
    }
}
