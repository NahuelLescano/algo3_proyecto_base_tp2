package edu.fiuba.algo3.modelo.arma.vek;

import edu.fiuba.algo3.modelo.casilla.Casilla;
import edu.fiuba.algo3.modelo.casilla.CasillaInexistente;
import edu.fiuba.algo3.modelo.excepciones.AtaqueInvalidoException;
import edu.fiuba.algo3.modelo.tablero.Direccion;
import edu.fiuba.algo3.modelo.tablero.Posicion;
import edu.fiuba.algo3.modelo.tablero.Tablero;
import edu.fiuba.algo3.modelo.unidad.efecto.*;
import edu.fiuba.algo3.modelo.arma.*;
public class EscupirAcido implements AtaqueVek {
    private final Disparo disparo = new Disparo(1, new EfectoAcido());

    @Override
    public void atacar(Tablero tablero, Posicion origen, Posicion objetivo) {
        if (origen.equals(objetivo)) {
            throw new AtaqueInvalidoException("Ataque invalido");
        }
        Direccion direccion = origen.direccionHacia(objetivo);
        Posicion actual = origen.desplazar(direccion);

        while (actual != null) {
            actual = tablero.buscarCasilla(actual)
                            .avanzarDisparoEnLinea(actual, direccion, tablero, disparo);
        }
    }
}
