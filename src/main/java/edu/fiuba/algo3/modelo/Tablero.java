package edu.fiuba.algo3.modelo;

import java.util.List;

public class Tablero {

    private List<Casilla> casillas;

    // ==================================================
    // CONSTRUCTOR
    // ==================================================

    public Tablero(String[] mapa) {
        ParserMapa parser = new ParserMapa();
        this.casillas = parser.parsear(mapa);
    }

    // ==================================================
    // CONSULTAS SOBRE EL TABLERO
    // ==================================================

    public int getFilas() {
        return casillas.stream()
                .mapToInt(c -> c.getPosicion().getFila())
                .max().orElse(-1) + 1;
    }

    public int getColumnas() {
        return casillas.stream()
                .mapToInt(c -> c.getPosicion().getColumna())
                .max().orElse(-1) + 1;
    }

    public Terreno obtenerTerrenoEn(Posicion posicion) {
        Casilla casilla = buscarCasilla(posicion);

        if (casilla == null) {
            return null;
        }

        return casilla.getTerreno();
    }

    public boolean detieneDisparoEn(Posicion posicion) {
        Casilla casilla = buscarCasilla(posicion);

        return casilla != null && casilla.detieneDisparo();
    }

    public boolean estaDentroDelTablero(Posicion posicion) {
        return buscarCasilla(posicion) != null;
    }

    // ==================================================
    // BÚSQUEDA DE ELEMENTOS
    // ==================================================

    private Casilla buscarCasilla(Posicion posicion) {
        for (Casilla casilla : casillas) {
            if (casilla.estaEnPosicion(posicion)) {
                return casilla;
            }
        }
        return null;
    }

    // ==================================================
    // COLOCACIÓN Y DESPLAZAMIENTO DE UNIDADES
    // ==================================================

    public boolean colocarUnidad(Unidad unidad) {
        Casilla casilla = buscarCasilla(unidad.getPosicion());

        if (casilla == null) {
            return false;
        }

        return casilla.ocupar(unidad);
    }

    public boolean desplazarUnidad(Unidad unidad, Posicion destino) {
        Casilla origen = buscarCasilla(unidad.getPosicion());
        Casilla llegada = buscarCasilla(destino);

        if (origen == null || llegada == null) {
            return false;
        }

        if (!origen.estaOcupadaPor(unidad)) {
            return false;
        }

        if (!llegada.puedeRecibirUnidad()) {
            return false;
        }

        origen.desocupar();

        if (!llegada.ocupar(unidad)) {
            origen.ocupar(unidad);
            return false;
        }

        unidad.setPosicion(destino);

        llegada.aplicarEfectoDelTerreno();

        return true;
    }

    public boolean moverUnidad(Unidad unidad, Posicion destino) {

        if (!unidad.puedeMoverse()) {
            return false;
        }

        boolean seMovio = desplazarUnidad(unidad, destino);

        if (seMovio) {
            unidad.registrarMovimiento();
        }

        return seMovio;
    }

    // ==================================================
    // DAÑO, IMPACTOS Y EMPUJES
    // ==================================================

    public void resolverImpacto(
            Posicion posicion, Direccion direccion, int danio) {

        Casilla casilla = buscarCasilla(posicion);

        if (casilla == null) {
            return;
        }

        Unidad sobreviviente = casilla.aplicarDanio(danio);

        if (sobreviviente != null) {
            empujarUnidad(sobreviviente, direccion);
        }
    }

    public void daniarEn(Posicion posicion, int danio) {

        Casilla casilla = buscarCasilla(posicion);

        if (casilla == null) {
            return;
        }

        casilla.aplicarDanio(danio);
    }

    public void empujarUnidad(Unidad unidad, Direccion direccion) {

        if (!unidad.estaViva()) {
            return;
        }

        Casilla origen = buscarCasilla(unidad.getPosicion());

        if (origen == null || !origen.estaOcupadaPor(unidad)) {
            return;
        }

        // Calcular el destino

        Posicion posicionActual = unidad.getPosicion();

        int filaDestino = posicionActual.getFila()
                + direccion.getDesplazamientoEnFila();

        int columnaDestino = posicionActual.getColumna()
                + direccion.getDesplazamientoEnColumna();

        Posicion destino = new Posicion(filaDestino, columnaDestino);

        // Obtener la casilla de destino

        Casilla llegada = buscarCasilla(destino);

        if (llegada == null) {
            return;
        }

        // Si el destino está ocupado, ambas unidades reciben daño.

        if (llegada.estaOcupada()) {

            origen.aplicarDanio(1);
            llegada.aplicarDanio(1);

            return;
        }

        // Si el terreno permite el desplazamiento, mover la unidad.

        if (llegada.puedeRecibirUnidad()) {
            desplazarUnidad(unidad, destino);
            return;
        }

        // Si el terreno bloquea el desplazamiento, ambas casillas reciben daño.

        origen.aplicarDanio(1);
        llegada.aplicarDanio(1);
    }

    public void empujarUnidadEn(
            Posicion posicion, Direccion direccion) {

        Casilla casilla = buscarCasilla(posicion);

        if (casilla == null || !casilla.estaOcupada()) {
            return;
        }

        empujarUnidad(casilla.getUnidad(), direccion);
    }

    public void registrarObservadorEnEdificios(
            ObservadorEdificio observador) {

        for (Casilla casilla : casillas) {
            Terreno terreno = casilla.getTerreno();

            if (terreno instanceof Edificio) {
                Edificio edificio = (Edificio) terreno;
                edificio.agregarObservador(observador);
            }
        }
    }

    public boolean estaEnAgua(Posicion posicion) {
        return obtenerTerrenoEn(posicion) instanceof Agua;
    }
}
