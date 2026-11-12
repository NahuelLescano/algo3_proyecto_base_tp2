package edu.fiuba.algo3.modelo.movimiento;

import edu.fiuba.algo3.modelo.excepciones.MovimientoInvalidoException;

public class CasillaLibre implements EstadoCasilla {

    @Override
    public void alojar(Casilla casilla, Unidad unidad) {
        casilla.cambiarEstado(new CasillaOcupada(unidad));
        casilla.aplicarEfectoTerreno(unidad);
    }

    @Override
    public void desalojar(Casilla casilla) {
        throw new CasillaVaciaException("La casilla ya se encuentra vacia.");
    }

    @Override
    public void moverHacia(Casilla origen, Casilla destino) {
        throw new MovimientoInvalidoException("No hay ninguna unidad en la casilla de origen para mover");
    }

    @Override
    public void empujar(Casilla casilla, Direccion direccion, Tablero tablero) {
        // No hay unidad para empujar
    }

    @Override
    public void recibirDanio(int puntos) {
        // El danio en casilla vacia impacta solo al terreno
    }

    @Override
    public boolean transitablePara(Locomocion locomocion, Terreno terreno) {
        return terreno.transitablePara(locomocion);
    }

    @Override
    public void recibirImpactoEmpuje(Casilla destino, Casilla origen, Unidad unidadEmpujada) {
        origen.desalojar();
        destino.alojar(unidadEmpujada);
    }

    @Override
    public boolean detieneProyectil() {
        return false;
    }

    @Override
    public void empujarHacia(Casilla origen, Casilla destino, Tablero tablero) {
        // No hay unidad para empujar
    }
}
