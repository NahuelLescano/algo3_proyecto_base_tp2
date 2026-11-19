package edu.fiuba.algo3.modelo.terreno;

import edu.fiuba.algo3.modelo.casilla.Destino;
import edu.fiuba.algo3.modelo.tablero.Direccion;
import edu.fiuba.algo3.modelo.tablero.Posicion;
import edu.fiuba.algo3.modelo.tablero.Tablero;
import edu.fiuba.algo3.modelo.unidad.Unidad;

public class Montania implements Terreno {

    public void recibir(Unidad unidad, Destino casilla) {
        unidad.noPudeMoverme();
    }

    public void recibirEmpuje(Unidad empujada, Destino destino) {
        empujada.recibirDanio(1);
    }

    public Terreno recibirImpacto(int danio) {
        return this;
    }

    @Override
    public Posicion avanzarDisparoEnLinea(Posicion actual, Direccion direccion, Tablero tablero, int danio) {
        tablero.daniarEn(actual, danio);
        tablero.empujar(actual, direccion);
        return null;
    }

}
