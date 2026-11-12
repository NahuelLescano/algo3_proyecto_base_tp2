package edu.fiuba.algo3.modelo.movimiento;

import java.util.ArrayList;
import java.util.List;

public class Tablero {
    private final List<Casilla> casillas;

    public Tablero() {
        this.casillas = new ArrayList<>();
    }

    public Casilla obtenerCasilla(Posicion pos) {
        return this.casillas.stream()
                .filter(casilla -> casilla.estaEnPosicion(pos))
                .findFirst()
                .orElseThrow(() -> new PosicionFueraDeRangoException("Posicion fuera de los limites"));
    }

    public void colocarTerreno(Posicion pos, Terreno t) {
        obtenerCasilla(pos).setTerreno(t);
    }

    public void colocarUnidad(Posicion pos, Unidad u) {
        obtenerCasilla(pos).ocupar(u);
    }

    public void moverUnidad(Posicion origen, Posicion destino) {
        Casilla cOrigen = this.obtenerCasilla(origen);
        Casilla cDestino = this.obtenerCasilla(destino);
        cOrigen.moverOcupanteA(cDestino);
    }

    public void empujar(Posicion origen, Posicion destino) {
        Casilla casillaOrigen = obtenerCasilla(origen);
        Casilla casillaDestino = obtenerCasilla(destino);
        casillaOrigen.empujarOcupanteHacia(casillaDestino, this);
    }

    public void resolverDesplazamientoEmpuje(Casilla origen, Casilla destino, Unidad unidadEmpujada) {
        destino.recibirImpactoEmpuje(origen, unidadEmpujada);
    }

    public void resolverDesplazamientoEmpuje(Casilla origen, Direccion direccion, Unidad unidad) {
        Posicion destinoPos = origen.calcularPosicionVecina(direccion);
        try {
            Casilla destinoCasilla = obtenerCasilla(destinoPos);
            destinoCasilla.recibirImpactoEmpuje(origen, unidad, direccion, this);
        } catch (PosicionFueraDeRangoException e) {
            // Si la casilla destino está fuera del tablero, la unidad no se mueve.
        }
    }

    public boolean estaDentroDelTablero(Posicion pos) {
        for (Casilla c : this.casillas) {
            if (c.estaEnPosicion(pos)) {
                return true;
            }
        }
        return false;
    }
}
