package edu.fiuba.algo3.modelo.casilla;

import edu.fiuba.algo3.modelo.excepciones.CasillaOcupadaException;
import edu.fiuba.algo3.modelo.tablero.Direccion;
import edu.fiuba.algo3.modelo.tablero.Posicion;
import edu.fiuba.algo3.modelo.tablero.Tablero;
import edu.fiuba.algo3.modelo.unidad.Unidad;
import edu.fiuba.algo3.modelo.arma.Disparo;
import edu.fiuba.algo3.modelo.unidad.Vek;
import java.util.List;
public class CasillaOcupada implements EstadoCasilla {

    private Unidad unidad;

    public CasillaOcupada(Unidad unidad) {
        this.unidad = unidad;
    }

    @Override
    public void ocupar(Unidad unidad, CasillaDelTablero casilla) {
        throw new CasillaOcupadaException("La casilla ya está ocupada.");
    }

    @Override
    public void recibirImpacto(int danio, CasillaDelTablero casilla) {
        unidad.recibirDanio(danio);
    }

    public void recibirEmpuje(Unidad empujada, CasillaDelTablero casilla) {
        empujada.recibirDanio(1);
        unidad.recibirDanio(1);
    }

    public void empujarHacia(Casilla destino) {
        destino.recibirEmpuje(unidad);
    }

    @Override
    public Posicion avanzarDisparoEnLinea(CasillaDelTablero casilla, Posicion actual, Direccion direccion, Tablero tablero, Disparo disparo) {
        disparo.impactarA(this.unidad);
        disparo.resolverPostImpacto(tablero, actual, direccion);
        return null;
    }

    public void recibirEmergencia(Vek vek, CasillaDelTablero casilla) {
        unidad.recibirDanio(1);
    }

    @Override
    public void agregarSiEsSueloLibreA(CasillaDelTablero casilla, List<Casilla> casillas) {
    }
}
