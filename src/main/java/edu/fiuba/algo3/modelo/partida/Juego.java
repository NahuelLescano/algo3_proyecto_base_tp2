package edu.fiuba.algo3.modelo.partida;

import java.util.ArrayList;
import java.util.List;

import edu.fiuba.algo3.modelo.casilla.Casilla;
import edu.fiuba.algo3.modelo.estructura.RedElectrica;
import edu.fiuba.algo3.modelo.tablero.Tablero;
import edu.fiuba.algo3.modelo.unidad.Mech;
import edu.fiuba.algo3.modelo.unidad.Vek;
import edu.fiuba.algo3.modelo.unidad.turno.ControladorDeshacer;

public class Juego {
    private static final int TURNOS_TOTALES = 5;

    private Tablero tablero;
    private RedElectrica redElectrica;
    private EstadoPartida estadoPartida;
    private FaseDeTurno faseActual;

    private List<Mech> mechs;
    private List<Vek> veks;
    private List<AtaqueAnunciado> ataquesAnunciados;
    private List<PuntoEmergencia> puntosEmergencia;

    private ControladorDeshacer controladorDeshacer;
    private int turnoActual;

    public Juego(Tablero tablero, RedElectrica redElectrica) {
        this.tablero = tablero;
        this.redElectrica = redElectrica;
        this.tablero.registrarObservadorEnEdificios(redElectrica);

        this.mechs = new ArrayList<>();
        this.veks = new ArrayList<>();
        this.ataquesAnunciados = new ArrayList<>();
        this.puntosEmergencia = new ArrayList<>();
        this.controladorDeshacer = new ControladorDeshacer();

        this.estadoPartida = new EnCurso();
        this.faseActual = new FaseVek();
        this.turnoActual = 1;
    }

    // --- Gestión de fases ---

    public void cambiarFase(FaseDeTurno nuevaFase) {
        this.faseActual = nuevaFase;
    }

    public void cambiarEstadoDePartida(EstadoPartida nuevoEstado) {
        this.estadoPartida = nuevoEstado;
    }

    public void ejecutarFaseActual() {
        if (estadoPartida.estaEnCurso()) {
            faseActual.ejecutar(this);
        }
    }

    public void finalizarFaseJugador() {
        estadoPartida.finalizarFaseJugador(this);
    }

    // --- Gestión de unidades ---

    public void agregarMech(Mech mech) {
        mechs.add(mech);
    }

    public void agregarVek(Vek vek) {
        veks.add(vek);
    }

    public List<Mech> getMechs() {
        return mechs;
    }

    public List<Vek> getVeks() {
        return veks;
    }

    public List<Vek> getVeksVivos() {
        List<Vek> vivos = new ArrayList<>();
        for (Vek vek : veks) {
            if (vek.getVidaActual() > 0) {
                vivos.add(vek);
            }
        }
        return vivos;
    }

    public List<Mech> getMechsVivos() {
        List<Mech> vivos = new ArrayList<>();
        for (Mech mech : mechs) {
            if (mech.getVidaActual() > 0) {
                vivos.add(mech);
            }
        }
        return vivos;
    }

    // --- Ataques anunciados ---

    public void agregarAtaqueAnunciado(AtaqueAnunciado ataque) {
        ataquesAnunciados.add(ataque);
    }

    public List<AtaqueAnunciado> getAtaquesAnunciados() {
        return ataquesAnunciados;
    }

    public void limpiarAtaquesAnunciados() {
        ataquesAnunciados.clear();
    }

    // --- Emergencia ---

    public void agregarPuntoEmergencia(PuntoEmergencia punto) {
        puntosEmergencia.add(punto);
    }

    public List<PuntoEmergencia> getPuntosEmergencia() {
        return puntosEmergencia;
    }

    public void limpiarPuntosEmergencia() {
        puntosEmergencia.clear();
    }

    // --- Turnos ---

    public int getTurnoActual() {
        return turnoActual;
    }

    public void avanzarTurno() {
        turnoActual++;
    }

    public boolean completoTodosLosTurnos() {
        return turnoActual > TURNOS_TOTALES;
    }

    // --- Mechs: turno y ataques ---

    public void reiniciarTurnosDeMechs() {
        for (Mech mech : mechs) {
            if (mech.getVidaActual() > 0) {
                mech.reiniciarTurno();
            }
        }
    }

    public void iniciarAtaque(Mech mech, Casilla objetivo) {
        mech.iniciarAtaque(objetivo, tablero);
    }

    // --- Acceso a componentes ---

    public Tablero getTablero() {
        return tablero;
    }

    public int getRedElectrica() {
        return redElectrica.getEnergia();
    }

    public ControladorDeshacer getControladorDeshacer() {
        return controladorDeshacer;
    }

    // --- Verificación de fin de partida ---

    public void verificarFinDePartida() {
        if (redElectrica.getEnergia() <= 0) {
            cambiarEstadoDePartida(new Derrota());
            return;
        }

        boolean todosMechsMuertos = true;
        for (Mech mech : mechs) {
            if (mech.getVidaActual() > 0) {
                todosMechsMuertos = false;
                break;
            }
        }
        if (todosMechsMuertos && !mechs.isEmpty()) {
            cambiarEstadoDePartida(new Derrota());
            return;
        }

        if (completoTodosLosTurnos()) {
            cambiarEstadoDePartida(new Victoria());
        }
    }

    public boolean estaEnCurso() {
        return estadoPartida.estaEnCurso();
    }
}
