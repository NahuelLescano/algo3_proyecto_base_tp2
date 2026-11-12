package edu.fiuba.algo3.modelo.movimiento;

public class Casilla {
    private final Posicion posicion;
    private Terreno terreno;
    private EstadoCasilla estado;

    public Casilla(Posicion posicion, Terreno terreno) {
        this.posicion = posicion;
        this.terreno  = terreno;
        this.estado   = new CasillaLibre();
    }

    public boolean estaEnPosicion(posicion otra) {
        return this.posicion.equals(otra);
    }

    public Posicion calcularPosicionVecina(Direccion dir) {
        return this.posicion.sumar(dir);
    }

    public int distanciaA(Casilla otra) {
        return otra.distanciaDesde(this.posicion);
    }

    public int distanciaDesde(Posicion otraPosicion) {
        return this.posicion.distanciaManhattan(otraPosicion);
    }

    public void cambiarEstado(EstadoCasilla nuevoEstado) {
        this.estado = nuevoEstado;
    }

    public void setTerreno(Terreno nuevoTerreno) {
        this.terreno = nuevoTerreno;
    }

    public void ocupar(Unidad unidad) {
        this.terreno.validarPuedeAlojar();
        this.estado.alojar(this, unidad);
    }

    public void alojar(Unidad unidad) {
        this.estado.alojar(this, unidad);
    }

    public void desalojar() {
        this.estado.desalojar(this);
    }

    public void empujarOcupante(Direccion direccion, Tablero tablero) {
        this.estado.empujar(this, direccion, tablero);
    }

    public boolean detieneProyectilRecto() {
        return this.terreno.bloqueaDisparos() || this.estado.detieneProyectil();
    }

    public void empujarOcupanteHacia(Casilla destino, Tablero tablero) {
        this.estado.empujarHacia(this, destino, tablero);
    }

    public void validarEmpujeAdyacenteHacia(Casilla destino) {
        // Delega en Posicion para asegurar que el empuje sea exactamente de 1 casilla ortogonal
        this.posicion.siguienteEnLineaDesde(destino.posicion);
    }

    public void recibirImpactoEmpuje(Casilla origen, Unidad unidadEmpujada) {
        this.terreno.recibirImpactoEmpuje(this, origen, unidadEmpujada);
    }

    public void recibirDanio(int puntos) {
        this.terreno.recibirDanio(puntos, this);
        this.estado.recibirDanio(puntos);
    }

    public void recibirImpactoEmpuje(Casilla origen, Unidad unidadEmpujada, Direccion direccion, Tablero tablero) {
        this.terreno.recibirImpactoEmpuje(this, origen, unidadEmpujada, direccion, tablero)
    }

    public void resolverColisionObstaculo(Unidad unidadEmpujada) {
        this.estado.recibirImpactoEmpuje(this, this, unidadEmpujada, null, null);
    }

    public boolean transitablePara(Locomocion locomocion) {
        return this.estado.transitablePara(locomocion, this.terreno);
    }

    public void aplicarEfectoTerreno(Unidad unidad) {
        this.terreno.aplicarEfecto(unidad);
    }
}
