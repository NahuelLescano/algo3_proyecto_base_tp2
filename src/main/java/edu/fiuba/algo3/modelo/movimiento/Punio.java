package edu.fiuba.algo3.modelo.movimiento;

import edu.fiuba.algo3.modelo.excepciones.AtaqueInvalidoException;

public class Punio implements Arma {

    @Override
    public void disparar(Posicion origen, Posicion objetivo, Tablero tablero) {
        if (origen.distanciaManhattan(objetivo) != 1) {
            throw new AtaqueInvalidoException("El puño solo golpea casillas adyacentes");
        }

        tablero.obtenerCasilla(objetivo).recibirDanio(2);
        Posicion destinoEmpuje = objetivo.siguienteEnLineaDesde(origen);
        if (tablero.estaDentroDelTablero(destinoEmpuje)) {
            tablero.empujar(objetivo, destinoEmpuje);
        }
    }
}
