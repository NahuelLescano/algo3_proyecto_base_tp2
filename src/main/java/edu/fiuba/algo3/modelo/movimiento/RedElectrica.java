package edu.fiuba.algo3.modelo.movimiento;

import edu.fiuba.algo3.modelo.excepciones.ValorInvalidoException;

public class RedElectrica {
    private int energia;

    public RedElectrica(int energiaInicial) {
        if (energiaInicial <= 0) {
            throw new ValorInvalidoException("La energia inicial debe ser positiva");
        }
        this.energia = energiaInicial;
    }

    public RedElectrica() {
        this.energia = 5;
    }

    public boolean estaAgotada() {
        return this.energia == 0;
    }

    public void descontar(int puntos) {
        if (puntos < 0) {
            throw new ValorInvalidoException("No se pueden descontar puntos negativos");
        }
        this.energia = Math.max(0, this.energia - puntos);
    }
}
