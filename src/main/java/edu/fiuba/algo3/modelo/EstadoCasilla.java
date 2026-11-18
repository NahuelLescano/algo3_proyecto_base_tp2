package edu.fiuba.algo3.modelo;

public interface EstadoCasilla {

    void ocupar(Unidad unidad, Casilla casilla);
    void recibirImpacto(int danio, Casilla casilla);
    /*
    EstadoCasilla desocupar();
    
    boolean estaOcupada();

    boolean estaOcupadaPor(Unidad unidad);

    Unidad obtenerUnidad();

    EstadoCasilla aplicarDanio(int danio, Terreno terreno);

    EstadoCasilla aplicarEfectoDelTerreno(Terreno terreno);

    boolean detieneDisparo();
 */
    }
