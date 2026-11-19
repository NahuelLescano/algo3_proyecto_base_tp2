package edu.fiuba.algo3.modelo;

import java.util.List;

public abstract class Unidad {
    private int vidaActual;
    private Posicion posicion;
    private Desplazamiento desplazamiento;
    private Casilla casilla = CasillaInexistente.INSTANCIA; 
    private List<EstadoUnidad> estados;

    public Unidad(int vida){
        this.vidaActual = vida;
    }

    public Unidad(int vida, Desplazamiento desplazamiento){
        this.vidaActual = vida;
        this.desplazamiento = desplazamiento;
    }

    public void asignarDesplazamiento(Desplazamiento desplazamiento){
        this.desplazamiento = desplazamiento;
    }

    public void ubicarseEn(Casilla nueva){
        casilla.desocupar();
        casilla = nueva;
    }
 
    public Posicion posicion() {
        return casilla.getPosicion();
    }

    protected Casilla obtenerCasillaActual(){
        return this.casilla;
    }

    public void morir(){
        casilla.desocupar();
        casilla = CasillaInexistente.INSTANCIA;
    }

    public void recibirDanio(int danio) {
        if (danio < 0) {
            throw new IllegalArgumentException("El daño no puede ser negativo");
        }

        vidaActual -= danio;

        if (vidaActual < 0) {
            vidaActual = 0;
        }
    }

    public int getVidaActual() {
        return vidaActual;
    }

    public Posicion getPosicion() {
        return posicion;
    }

    void setPosicion(Posicion posicion) {
        this.posicion = posicion;
    }
    
    public void registrarMovimiento() {
    }

    public void sufrir(Efecto efecto){
        
    }

    public void noPudeMoverme(){
        
    }

    protected List<EstadoUnidad> obtenerEstadoActuales(){
        return this.estados;
    }

    protected void reemplazarEstados(List<EstadoUnidad> nuevosEstados){
        this.estados.clear();
        this.estados.addAll(nuevosEstados);
    }
}
