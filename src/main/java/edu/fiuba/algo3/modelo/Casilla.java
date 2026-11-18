package edu.fiuba.algo3.modelo;

public interface Casilla extends Destino {
    Terreno getTerreno();
    Posicion getPosicion();
    void ocupar(Unidad unidad);                 
    void recibirEmpuje(Unidad empujada);
    void empujarHacia(Casilla destino);
    void recibirImpacto(int danio);
    void desocupar();
}

/*
public class Casilla implements ObservadorEdificio, Destino {

    private Terreno terreno;
    private Posicion posicion;
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

    public void moverUnidad(Unidad unidad){
        this.estado.moverUnidad(unidad);
    }

    public void ocupar(Unidad unidad) {
        this.terreno.recibir(unidad, this);
    }

    public void cambiarEstado(EstadoCasilla nuevoEstado){
        this.estado = nuevoEstado;
    }

    @Override 
    public void alojar(Unidad unidad){
        this.estado = new CasillaOcupada(unidad);
    }

    public void recibirEmpuje(Unidad empujada){
        estado.recibirEmpuje(empujada,this);
    }

    void empujarSobreTerreno(Unidad empujada){
        terreno.recibirEmpuje(empujada, this);
    }

    void empujarHacia(Casilla destino){
        estado.empujarHacia(destino);
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

    void recibirUnidad(Unidad unidad){
        terreno.recibir(unidad, this);
    }


    // ==================================================
    // RELACIONADOS CON EL DAÑO Y EFECTOS 
    // ==================================================
    
    public void recibirImpacto(int danio){
        estado.recibirImpacto(danio, this);
    }

    @Override 
    public void enEdificioDestruido(){
        this.reemplazarTerreno(new Suelo());
    }

    void impactarTerreno(int danio){
        this.terreno = terreno.recibirImpacto(danio);
    }
    /*
    public void aplicarEfectoDelTerreno() {
        estado = estado.aplicarEfectoDelTerreno(terreno);
    }
    
    public Unidad aplicarDanio(int danio) {
        Unidad unidadAfectada = estado.obtenerUnidad();

        estado = estado.aplicarDanio(danio, terreno);

        if (estado.estaOcupada()) {
            return unidadAfectada;
        }

        return null;
    }
    
    // ==================================================
    // RELACIONADOS CON LOS DISPAROS
    // ==================================================
    // ==================================================
    // RELACIONADOS CON LA POSICIÓN 
    // ==================================================
    
        public Posicion getPosicion() {
        return posicion;
    }
/*     
    public boolean estaEnPosicion(Posicion posicion) {
        return this.posicion.equals(posicion);
    }
    */
