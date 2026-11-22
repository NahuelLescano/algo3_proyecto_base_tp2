package edu.fiuba.algo3.modelo.unidad;

import edu.fiuba.algo3.modelo.arma.vek.AtaqueVek;
import edu.fiuba.algo3.modelo.tablero.Posicion;
import edu.fiuba.algo3.modelo.tablero.Tablero;

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
}
