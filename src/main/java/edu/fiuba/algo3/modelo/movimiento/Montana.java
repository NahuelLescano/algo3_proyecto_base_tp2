package edu.fiuba.algo3.modelo.movimiento;

import edu.fiuba.algo3.modelo.excepciones.CasillaBloqueadaException;

public class Montana extends Terreno {

    @Override
    public boolean esTransitable() {
        return false;
    }

    @Override
    public boolean transitablePara(Locomocion l) {
        return false;
    }

    @Override
    public boolean bloqueaDisparos() {
        return true;
    }

    @Override
    public void aplicarEfecto(Unidad u) {}

    @Override
    public void recibirDanio(int puntos, Casilla casilla) {}

    @Override
    public void validarPuedeAlojar() {
        throw new CasillaBloqueadaException("No se puede collocar una unidad sobre una montaña");
    }

    @Override
    public void recibirImpactoEmpuje(Casilla destino, Casilla origen, Unidad unidadEmpujada) {
        unidadEmpujada.recibirDanioPorColision(1);
    }
}
