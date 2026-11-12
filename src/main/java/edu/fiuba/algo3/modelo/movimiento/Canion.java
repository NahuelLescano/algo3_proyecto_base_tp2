package edu.fiuba.algo3.modelo.movimiento;

import edu.fiuba.algo3.modelo.excepciones.AtaqueInvalidoException;

public class Canion implements Arma {
    @Override
    public void disparar(Posicion origen, Posicion objetivo, Tablero tablero) {
        Direccion dir = determinarDireccionOrtogonal(origen, objetivo);
        Posicion actual = origen.sumar(dir);

        while (tablero.estaDentroDelTablero(actual)) {
            Casilla casillaActual = tablero.obtenerCasilla(actual);
            if (casillaActual.detieneProyectilRecto()) {
                casillaActual.recibirDanio(1);
                Posicion destinoEmpuje = actual.sumar(dir);
                if (tablero.estaDentroDelTablero(destinoEmpuje)) {
                    tablero.empujar(actual, destinoEmpuje);
                }
                return;
            }
        }
    }

    private Direccion determinarDireccionOrtogonal(Posicion origen, Posicion destino) {
        if (origen.equals(destino)) {
            throw new AtaqueInvalidoException("No se puede disparar a la misma casilla.");
        }
        for (Direccion dir : Direccion.values()) {
            Posicion cursor = origen.sumar(dir);
            while (cursor.distanciaManhattan(origen) <= 8) {
                if (cursor.equals(destino)) {
                    return dir;
                }
                cursor = cursor.sumar(dir);
            }
        }
        throw new AtaqueInvalidoException("El cañón solo dispara en línea recta ortogonal.");
    }
}
