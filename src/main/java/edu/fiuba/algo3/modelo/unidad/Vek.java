package edu.fiuba.algo3.modelo.unidad;

import edu.fiuba.algo3.modelo.arma.vek.AtaqueVek;
import edu.fiuba.algo3.modelo.tablero.Posicion;
import edu.fiuba.algo3.modelo.tablero.Tablero;
import edu.fiuba.algo3.modelo.terreno.efecto.Efecto;
import edu.fiuba.algo3.modelo.unidad.iavek.IAVek;

public class Vek extends Unidad {
    private final int movimiento;
    private final AtaqueVek ataque;
    private IAVek ia;

    protected Vek(int vida, int movimiento, Desplazamiento desplazamiento, AtaqueVek ataque) {
        super(vida);
        this.movimiento = movimiento;
        this.ataque = ataque;
        asignarDesplazamiento(desplazamiento);
        this.ia = new IAVek();
    }

    public int getMovimiento() {
        return movimiento;
    }

    public void ejecutarAtaque(Tablero tablero, Posicion origen, Posicion objetivo) {
        this.ataque.atacar(tablero, origen, objetivo);
    }

    @Override
    public void hundirse() { morir(); }

}
