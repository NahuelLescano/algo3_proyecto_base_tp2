package edu.fiuba.algo3.modelo;

public interface EstadoCasilla {

    void ocupar(Unidad unidad, CasillaDelTablero casilla);
    void recibirImpacto(int danio, CasillaDelTablero casilla);
    void recibirEmpuje(Unidad unidad, CasillaDelTablero casilla);
    void empujarHacia(Casilla casilla);
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
