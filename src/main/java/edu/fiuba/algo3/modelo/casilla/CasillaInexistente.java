package edu.fiuba.algo3.modelo.casilla;

import edu.fiuba.algo3.modelo.tablero.Direccion;
import edu.fiuba.algo3.modelo.tablero.Posicion;
import edu.fiuba.algo3.modelo.tablero.Tablero;
import edu.fiuba.algo3.modelo.terreno.Terreno;
import edu.fiuba.algo3.modelo.unidad.Unidad;

public class CasillaInexistente implements Casilla {
    public static final CasillaInexistente INSTANCIA = new CasillaInexistente();

    public Posicion getPosicion() {
        return new Posicion(-1, -1);
    }

    public void ocupar(Unidad unidad) {
        unidad.noPudeMoverme();
    }

    public void recibirEmpuje(Unidad empujada) {

    }

    public void empujarHacia(Casilla destino) {

    }

    public void recibirImpacto(int danio) {

    }

    public void desocupar() {

    }

    public void alojar(Unidad unidad) {

    }

    public Terreno getTerreno() {
        return null;
    }

    @Override
    public Posicion avanzarDisparoEnLinea(Posicion actual, Direccion direccion, Tablero tablero, int danio) {
        return null;
    }
}
