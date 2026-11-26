package edu.fiuba.algo3.modelo.terreno;

import edu.fiuba.algo3.modelo.casilla.Destino;
import edu.fiuba.algo3.modelo.excepciones.AccionInvalidaException;
import edu.fiuba.algo3.modelo.excepciones.CasillaOcupadaException;
import edu.fiuba.algo3.modelo.tablero.Direccion;
import edu.fiuba.algo3.modelo.tablero.Posicion;
import edu.fiuba.algo3.modelo.tablero.Tablero;
import edu.fiuba.algo3.modelo.unidad.Unidad;
import edu.fiuba.algo3.modelo.arma.Disparo;
public class Montania implements Terreno {

    public void recibir(Unidad unidad, Destino casilla) {
        unidad.noPudeMoverme();
        throw new CasillaOcupadaException("Casilla ocupada por terreno no transitable");
    }

    public void recibirEmpuje(Unidad empujada, Destino destino) {
        empujada.recibirDanio(1);
    }

    public Terreno recibirImpacto(int danio) {
        return this;
    }

    @Override
    public Posicion avanzarDisparoEnLinea(Posicion actual, Direccion direccion, Tablero tablero, Disparo disparo) {
        tablero.daniarEn(actual, disparo.danio());
        tablero.empujar(actual, direccion);
        return null;
    }
    
    @Override
    public char obtenerSimbolo() {
        return 'M';
    }

}
