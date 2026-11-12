package edu.fiuba.algo3.modelo.movimiento;

public interface EstadoCasilla {
    void alojar(Casilla casilla, Unidad unidad);
    void desalojar(Casilla casilla);
    void moverHacia(Casilla origen, Casilla destino);
    void empujar(Casilla casilla, Direccion direccion, Tablero tablero);
    void recibirDanio(int puntos);
    void recibirImpactoEmpuje(Casilla destino, Casilla origen, Unidad unidadEmpujada);
    void empujarHacia(Casilla origen, Casilla destino, Tablero tablero);
    boolean transitablePara(Locomocion locomocion, Terreno terreno);
    boolean detieneProyectil();
}
