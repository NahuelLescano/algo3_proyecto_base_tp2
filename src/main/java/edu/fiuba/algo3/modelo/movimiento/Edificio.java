package edu.fiuba.algo3.modelo.movimiento;

import edu.fiuba.algo3.modelo.excepciones.CasillaBloqueadaException;

public class Edificio extends Terreno {
    private final RedElectrica redElectrica;
    private int vida;

    public Edificio(RedElectrica redElectrica) {
        this.redElectrica = redElectrica;
        this.vida = 1;
    }

    @Override
    public boolean esTransitable() {
        return false;
    }

    @Override
    public boolean bloqueaDisparos() {
        return true;
    }

    @Override
    public boolean transitablePara(Locomocion l) {
        return false;
    }

    @Override
    public void aplicarEfecto(Unidad u) {}

    @Override
    public void recibirDanio(int puntos, Casilla casilla) {
        this.vida -= puntos;
        this.redElectrica.descontar(puntos);
        if (this.vida <= 0) {
            casilla.setTerreno(new Suelo());
        }
    }

    @Override
    public void validarPuedeAlojar() {
        throw new CasillaBloqueadaException("No se puede colocar una unidad sobre un edificio");
    }

    @Override
    public void recibirImpactoEmpuje(Casilla destino, Casilla origen, Unidad unidadEmpujada) {
        unidadEmpujada.recibirDanioPorColision(1);
        recibirDanio(1, destino);
    }
}
