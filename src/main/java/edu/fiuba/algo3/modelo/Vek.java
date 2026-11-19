package edu.fiuba.algo3.modelo;
public class Vek extends Unidad {
    private final int movimiento;
    private final AtaqueVek ataque;

    protected Vek(int vida, int movimiento, Desplazamiento desplazamiento, AtaqueVek ataque) {
        super(vida);
        this.movimiento = movimiento;
        this.ataque = ataque;
        asignarDesplazamiento(desplazamiento);
    }

    public int getMovimiento() {
        return movimiento;
    }

    protected void ejecutarAtaque(Tablero tablero, Posicion origen, Posicion objetivo) {
        this.ataque.atacar(tablero, origen, objetivo);
    }

    //public AtaqueAnunciado anunciarAtaque(Tablero tablero);
}