package edu.fiuba.algo3.modelo.movimiento;

import edu.fiuba.algo3.modelo.excepciones.CasillaBloqueadaException;

public class CasillaOcupada implements EstadoCasilla {
    private final Unidad ocupante;

    public CasillaOcupada(Unidad ocupante) {
        this.ocupante = ocupante;
    }

    @Override
    public void alojar(Casilla casilla, Unidad unidad) {
        throw new CasillaBloqueadaException("La casilla ya esta ocupad por otra unidad");
    }

    @Override
    public void desalojar(Casilla casilla) {
        casilla.cambiarEstado(new CasillaLibre());
    }

    @Override
    public void moverHacia(Casilla origen, Casilla destino) {
        this.ocupante.trasladarseA(origen, destino);
    }

    @Override
    public void empujar(Casilla casilla, Direccion direccion, Tablero tablero) {
        this.ocupante.recibirEmpuje(casilla, direccion, tablero);
    }

    @Override
    public void recibirDanio(int puntos) {
        this.ocupante.recibirDanio(puntos);
    }

    @Override
    public boolean transitablePara(Locomocion locomocion, Terreno terreno) {
        return false;
    }

    @Override
    public void recibirImpactoEmpuje(Casilla destino, Casilla origen, Unidad unidadEmpujada) {
        unidadEmpujada.recibirDanioPorColision(1);
        this.ocupante.recibirDanioPorColision(1);
    }

    @Override
    public boolean detieneProyectil() {
        return true;
    }

    @Override
    public Unidad obtenerUnidad() {
        return this.ocupante;
    }

    @Override
    public void empujarHacia(Casilla origen, Casilla destino, Tablero tablero) {
        this.ocupante.recibirEmpujeHacia(origen, destino, tablero);
    }
}
