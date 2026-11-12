package edu.fiuba.algo3.modelo.movimiento;

import edu.fiuba.algo3.modelo.excepciones.MovimientoInvalidoException;

public abstract class Unidad {
    private int vida;
    private final int rangoMovimiento;
    private final Locomocion locomocion;
    private EstadoSalud estadoSalud;
    private boolean puedeMoverseEnTurno;

    public Unidad(int vida, int rangoMovimiento, Locomocion locomocion) {
        this.vida                = vida;
        this.rangoMovimiento     = rangoMovimiento;
        this.locomocion          = locomocion;
        this.estadoSalud         = new EstadoNormal();
        this.puedeMoverseEnTurno = true;
    }

    public abstract void reaccionarAguaTerrestre();

    public void recibirEmpujeHacia(Casilla origen, Casilla destino, Tablero tablero) {
        origen.validarEmpujeAdyacenteHacia(destino);
        tablero.resolverDesplazamientoEmpuje(origen, destino, this);
    }

    public void transladarseA(Casilla origen, Casilla destino) {
        this.validadMovimiento(origen, destino);
        this.puedeMoverseEnTurno = false;
        origen.desalojar();
        destino.alojar(this);
    }

    public void recibirDanio(int danioBase) {
        int danioFinal = this.estadoSalud.calcularDanioEfectivo(danioBase);
        this.vida -= danioFinal;
    }

    public void recibirDanioPorColision(int danioBase) {
        this.recibirDanio(danioBase);
    }

    public void contaminar() {
        this.estadoSalud = this.estadoSalud.contaminar();
    }

    public void aplicarEfectoAgua() {
        this.locomocion.aplicarEfectoAgua(this);
    }

    public void aplicarEfectoAcido() {
        this.locomocion.aplicarEfectoAcido(this);
    }

    public boolean estaDestruida() {
        return this.vida <= 0;
    }

    public void reiniciarTurno() {
        this.puedeMoverseEnTurno = true;
    }

    private void validadMovimiento(Casilla origen, Casilla destino) {
        if (!this.puedeMoverseEnTurno) {
            throw new AccionYaRealizadaException("La unidad ya se movio en este turno")
        }
        if (!destino.transitablePara(this.locomocion)) {
            throw new CasillaBloqueadaException("La casilla esta bloqueada");
        }

        int distancia = origen.distanciaA(destino);
        if (distancia > this.rangoMovimiento || distancia <= 0) {
            throw new MovimientoInvalidoException("Movimiento fuera del rango permitido");
        }
    }
}
