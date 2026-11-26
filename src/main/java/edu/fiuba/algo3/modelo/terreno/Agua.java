package edu.fiuba.algo3.modelo.terreno;

import edu.fiuba.algo3.modelo.arma.Disparo;
import edu.fiuba.algo3.modelo.casilla.Destino;
import edu.fiuba.algo3.modelo.tablero.Direccion;
import edu.fiuba.algo3.modelo.tablero.Posicion;
import edu.fiuba.algo3.modelo.tablero.Tablero;
import edu.fiuba.algo3.modelo.terreno.efecto.EfectoAgua;
import edu.fiuba.algo3.modelo.unidad.Unidad;
import edu.fiuba.algo3.modelo.arma.Disparo;
public class Agua implements Terreno {
    public void recibir(Unidad unidad, Destino casilla) {
        casilla.alojar(unidad);
        unidad.sufrir(new EfectoAgua());
    }

    public void recibirEmpuje(Unidad empujada, Destino destino) {
        recibir(empujada, destino);
    }

    public Terreno recibirImpacto(int danio) {
        return this;
    }
    @Override
        public Posicion avanzarDisparoEnLinea(Posicion actual, Direccion direccion, Tablero tablero, Disparo disparo) {
            return actual.desplazar(direccion);
        }
        
    @Override
    public char obtenerSimbolo() {
        return 'A';
    }
}
