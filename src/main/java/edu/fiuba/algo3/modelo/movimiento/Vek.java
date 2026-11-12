package edu.fiuba.algo3.modelo.movimiento;

public abstract class Vek extends Unidad {
    public Vek(int vida, int rangoMovimiento, Locomocion locomocion) {
        super(vida, rangoMovimiento, locomocion);
    }

    @Override
    public void reaccionarAguaTerrestre() {
        this.recibirDanio(999); // Vek terrestre muere cuando cae al agua
    }
}
