package edu.fiuba.algo3.modelo.unidad;

import java.util.ArrayList;
import java.util.List;

import edu.fiuba.algo3.modelo.casilla.Casilla;
import edu.fiuba.algo3.modelo.casilla.CasillaInexistente;
import edu.fiuba.algo3.modelo.tablero.Posicion;
import edu.fiuba.algo3.modelo.terreno.efecto.Efecto;
import edu.fiuba.algo3.modelo.unidad.estado.*;

public abstract class Unidad {
    private int vidaActual;
    private Posicion posicion;
    protected Desplazamiento desplazamiento;
    private Casilla casilla = CasillaInexistente.INSTANCIA;
    private List<EstadoUnidad> estados = new ArrayList<>();
    private final EstadoUnidad sano = new Sano();
    private final EstadoUnidad contaminada = new Contaminada();

    public Unidad(int vida) {
        this.vidaActual = vida;
    }

    public Unidad(int vida, Desplazamiento desplazamiento) {
        this.vidaActual = vida;
        this.desplazamiento = desplazamiento;
        this.agregarEstado(new Sano());
    }

    public void asignarDesplazamiento(Desplazamiento desplazamiento) {
        this.desplazamiento = desplazamiento;
    }

    public void ubicarseEn(Casilla nueva) {
        casilla.desocupar();
        casilla = nueva;
    }

    public Posicion posicion() {
        return casilla.getPosicion();
    }

    protected Casilla obtenerCasillaActual() {
        return this.casilla;
    }

    public void morir() {
        casilla.desocupar();
        casilla = CasillaInexistente.INSTANCIA;
        vidaActual = 0;
    }

    public void recibirDanio(int danio) {
        if (danio < 0) {
            throw new IllegalArgumentException("El daño no puede ser negativo");
        }

        for (EstadoUnidad estado : estados) {
            danio = estado.modificar(danio);
        }
        vidaActual = Math.max(0, vidaActual - danio);
    }

    public int getVidaActual() {
        return vidaActual;
    }

    public void registrarMovimiento() {
    }

    public void sufrir(Efecto efecto){
        desplazamiento.afectar(efecto, this);
    }

    public void noPudeMoverme() {
    }

    protected List<EstadoUnidad> obtenerEstadosActuales() {
        return this.estados;
    }

    protected void reemplazarEstados(List<EstadoUnidad> nuevosEstados) {
        this.estados.clear();
        this.estados.addAll(nuevosEstados);
    }

    public void agregarEstado(EstadoUnidad estado){
        estados.add(estado);
    }

    public abstract void hundirse();

    public void contaminar() {
        if (estados.contains(contaminada)) return;
        quitarEstado(sano);
        agregarEstado(contaminada);
    }

    public void quitarEstado(EstadoUnidad estado) {
        estados.remove(estado);
    }

    public boolean puedeAtacar() {
        for (EstadoUnidad estado : estados) {
            if (!estado.permiteAtacar()) {
                return false;
            }
        }
        return true;
    }
}
