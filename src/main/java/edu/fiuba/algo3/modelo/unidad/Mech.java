package edu.fiuba.algo3.modelo.unidad;

import edu.fiuba.algo3.modelo.*;
import edu.fiuba.algo3.modelo.arma.Arma;
import edu.fiuba.algo3.modelo.casilla.Casilla;

public class Mech extends Unidad {
    private Arma arma;
    private EstadoTurnoMech estadoTurno = new Disponible();

    public Mech(int vida, Arma arma, Desplazamiento desplazamiento){
        super(vida);
        this.arma = arma;
        asignarDesplazamiento(desplazamiento);
    }

    public void mover(Posicion destino, Tablero tablero, ControladorDeshacer controlador) {
        Casilla casillaDestino = tablero.buscarCasilla(destino);
        this.estadoTurno.mover(this, casillaDestino, tablero, controlador);
    }

    public void atacar(Posicion objetivo, Tablero tablero, ControladorDeshacer controlador) {
        Casilla casillaObjetivo = tablero.buscarCasilla(objetivo);
        this.estadoTurno.atacar(this, casillaObjetivo, tablero, controlador);
    }

    public void deshacerMovimiento(Tablero tablero) {
        this.estadoTurno.deshacerMovimiento(this, tablero);
    }

    public void reiniciarTurno() {
        this.estadoTurno = new Disponible();
    }
    
    public void cambiarEstadoTurno(EstadoTurnoMech nuevoEstado) {
        this.estadoTurno = nuevoEstado;
    }

    public MementoMech crearMemento() {
        return new MementoMech(obtenerCasillaActual(), obtenerEstadosActuales());
    }

    public void restaurarDesdeMemento(MementoMech memento, Tablero tablero) {
        obtenerCasillaActual().desocupar();
        Casilla casillaAnterior = memento.getCasillaAnterior();
        tablero.colocarUnidad(this, casillaAnterior.getPosicion());
        reemplazarEstados(memento.getEstadosAnteriores());
    }

    public void ejecutarDesplazamiento(Casilla destino, Tablero tablero) {
        tablero.moverUnidad(this, destino.getPosicion());
    }

    public void iniciarAtaque(Casilla objetivo, Tablero tablero) {
        Posicion objetivoDeAtaque = objetivo.getPosicion();
        this.arma.atacar(tablero, this.posicion(), objetivoDeAtaque);
    }
}