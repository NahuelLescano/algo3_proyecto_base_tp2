package edu.fiuba.algo3.modelo;

public class Casilla {

    private Terreno terreno;
    private final Posicion posicion;
    private EstadoCasilla estado;

    // ==================================================
    // CONSTRUCTOR
    // ==================================================

    public Casilla(Terreno terreno, Posicion posicion) {
        this.terreno = terreno;
        this.posicion = posicion;
        this.estado = new CasillaVacia();
    }

    // ==================================================
    // RELACIONADOS CON LA OCUPACIÓN
    // ==================================================

    public boolean ocupar(Unidad unidad) {
        if (estado.estaOcupada()) {
            return false;
        }

        estado = estado.ocupar(unidad);
        return true;
    }

    public void desocupar() {
        estado = estado.desocupar();
    }

    public boolean estaOcupada() {
        return estado.estaOcupada();
    }

    public boolean estaOcupadaPor(Unidad unidad) {
        return estado.estaOcupadaPor(unidad);
    }

    public Unidad getUnidad() {
        return estado.obtenerUnidad();
    }

    public boolean puedeRecibirUnidad() {
        return !estado.estaOcupada() && terreno.esTransitable();
    }

    // ==================================================
    // RELACIONADOS CON EL TERRENO
    // ==================================================

    public Terreno getTerreno() {
        return terreno;
    }

    public void reemplazarTerreno(Terreno nuevoTerreno) {
        this.terreno = nuevoTerreno;
    }

    public boolean bloqueaDisparos() {
        return terreno.bloqueaDisparos();
    }

    // ==================================================
    // RELACIONADOS CON EL DAÑO Y EFECTOS
    // ==================================================

    public void aplicarEfectoDelTerreno() {
        estado = estado.aplicarEfectoDelTerreno(terreno);
    }

    public Unidad aplicarDanio(int danio) {
        Unidad unidadAfectada = estado.obtenerUnidad();

        if (unidadAfectada == null) {
            terreno = terreno.recibirDanio(danio);
        }

        estado = estado.aplicarDanio(danio);

        if (estado.estaOcupada()) {
            return unidadAfectada;
        }

        return null;
    }

    // ==================================================
    // RELACIONADOS CON LOS DISPAROS
    // ==================================================

    public boolean detieneDisparo() {
        return estado.detieneDisparo() || terreno.bloqueaDisparos();
    }

    // ==================================================
    // RELACIONADOS CON LA POSICIÓN
    // ==================================================

    public Posicion getPosicion() {
        return posicion;
    }

    public boolean estaEnPosicion(Posicion posicion) {
        return this.posicion.equals(posicion);
    }
}
