package edu.fiuba.algo3.modelo.movimiento;

public class Artilleria implements Arma {
    @Override
    public void disparar(Posicion origen, Posicion objetivo, Tablero tablero) {
        int distancia = origen.distanciaManhattan(objetivo);
        if (distancia < 2) {
            throw new AtaqueInvalidoException("La artilleria requiere un objetivo a 2 o mas casillas");
        }

        tablero.obtenerCasilla(objetivo).recibirDanio(1);

        for (Direccion dir : Direccion.values()) {
            Posicion adyacente = objetivo.sumar(dir);
            Posicion destinoEmpuje = adyacente.sumar(dir);
            if (tablero.estaDentroDelTablero(adyacente) && tablero.estaDentroDelTablero(destinoEmpuje)) {
                tablero.empujar(adyacente, destinoEmpuje);
            }
        }
    }
}
