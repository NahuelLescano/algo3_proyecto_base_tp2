package edu.fiuba.algo3.modelo.unidad;

import edu.fiuba.algo3.modelo.arma.Arma;
import edu.fiuba.algo3.modelo.casilla.Casilla;
import edu.fiuba.algo3.modelo.tablero.Posicion;
import edu.fiuba.algo3.modelo.tablero.Tablero;
import edu.fiuba.algo3.modelo.terreno.efecto.Efecto;
import edu.fiuba.algo3.modelo.unidad.estado.Sumergido;
import edu.fiuba.algo3.modelo.unidad.turno.ControladorDeshacer;
import edu.fiuba.algo3.modelo.unidad.turno.Disponible;
import edu.fiuba.algo3.modelo.unidad.turno.EstadoTurnoMech;
import edu.fiuba.algo3.modelo.unidad.turno.MementoMech;

public class Mech extends Unidad {
    private Arma arma;
    private EstadoTurnoMech estadoTurno;

    public Mech(int vida, Arma arma, Desplazamiento desplazamiento) {
        super(vida);
        this.arma = arma;
        this.estadoTurno = new Disponible();
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
        if (puedeAtacar()) {
            this.arma.atacar(tablero, this.posicion(), objetivoDeAtaque);
        }
    }

    @Override
    public void hundirse(){
        agregarEstado(new Sumergido());
    }
}
