package edu.fiuba.algo3.modelo;

public class RedElectrica implements ObservadorEdificio {

    private int energia;

    public RedElectrica(int energiaInicial) {
        if (energiaInicial < 0) {
            throw new IllegalArgumentException(
                "La energía inicial no puede ser negativa"
            );
        }

        this.energia = energiaInicial;
    }

    public void reducirEnergia(int cantidad) {
        if (cantidad < 0) {
            throw new IllegalArgumentException(
                "La cantidad a reducir no puede ser negativa"
            );
        }

        energia = Math.max(0, energia - cantidad);
    }

    @Override
    public void enEdificioDestruido() {
        reducirEnergia(1);
    }

    public int getEnergia() {
        return energia;
    }

    public boolean estaAgotada() {
        return energia == 0;
    }
}
