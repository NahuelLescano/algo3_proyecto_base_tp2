package edu.fiuba.algo3.modelo.casilla;

import edu.fiuba.algo3.modelo.tablero.Direccion;
import edu.fiuba.algo3.modelo.tablero.Posicion;
import edu.fiuba.algo3.modelo.tablero.Tablero;
import edu.fiuba.algo3.modelo.terreno.Terreno;
import edu.fiuba.algo3.modelo.unidad.Unidad;

public class CasillaDelTablero implements Casilla {
    private Posicion posicion;
    private Terreno terreno;
    private EstadoCasilla estado;

    public CasillaDelTablero() {
        this.estado = new CasillaVacia();
    }

    public CasillaDelTablero(Terreno terreno, Posicion posicion) {
        this.terreno = terreno;
        this.posicion = posicion;
        this.estado = new CasillaVacia();
    }

    public Terreno getTerreno() {
        return this.terreno;
    }

    public Posicion getPosicion() {
        return posicion;
    }

    public void ocupar(Unidad unidad) {
        estado.ocupar(unidad, this);
    }

    public void recibirEmpuje(Unidad empujada) {
        estado.recibirEmpuje(empujada, this);
    }

    public void empujarHacia(Casilla destino) {
        estado.empujarHacia(destino);
    }

    public void recibirImpacto(int danio) {
        estado.recibirImpacto(danio, this);
    }

    public void desocupar() {
        estado = new CasillaVacia();
    }

    public void consultarTerreno(Unidad unidad) {
        terreno.recibir(unidad, this);
    }

    public void empujarSobreTerreno(Unidad unidad) {
        terreno.recibirEmpuje(unidad, this);
    }

    public void impactarTerreno(int danio) {
        terreno = terreno.recibirImpacto(danio);
    }

    @Override
    public void alojar(Unidad unidad) {
        estado = new CasillaOcupada(unidad);
        unidad.ubicarseEn(this);
    }

    @Override
    public Posicion avanzarDisparoEnLinea(Posicion actual, Direccion direccion, Tablero tablero, int danio) {
        return estado.avanzarDisparoEnLinea(this, actual, direccion, tablero, danio);
    }
}
