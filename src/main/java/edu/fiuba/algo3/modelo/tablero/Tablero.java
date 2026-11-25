package edu.fiuba.algo3.modelo.tablero;

import java.util.ArrayList;
import java.util.List;

import edu.fiuba.algo3.modelo.casilla.Casilla;
import edu.fiuba.algo3.modelo.casilla.CasillaDelTablero;
import edu.fiuba.algo3.modelo.casilla.CasillaInexistente;
import edu.fiuba.algo3.modelo.estructura.ObservadorEdificio;
import edu.fiuba.algo3.modelo.excepciones.AccionInvalidaException;
import edu.fiuba.algo3.modelo.excepciones.AtaqueInvalidoException;
import edu.fiuba.algo3.modelo.terreno.Edificio;
import edu.fiuba.algo3.modelo.terreno.Terreno;
import edu.fiuba.algo3.modelo.unidad.Unidad;
import edu.fiuba.algo3.modelo.tablero.*;

public class Tablero {

    private List<Casilla> casillas;

    public Tablero(String[] mapa, ParserTablero parser) {
        if (mapa == null || mapa.length == 0) {
            throw new IllegalArgumentException("El mapa no puede ser nulo o vacío");
        }
        this.casillas = new ArrayList<>();
        for (int fila = 0; fila < mapa.length; fila++) {
            String linea = mapa[fila];
            for (int columna = 0; columna < linea.length(); columna++) {
                Terreno terreno = parser.convertirCaracterATerreno(linea.charAt(columna));
                casillas.add(new CasillaDelTablero(terreno, new Posicion(fila, columna)));
            }
        }
    }

    public Casilla buscarCasilla(Posicion posicion) {
        for (Casilla casilla : casillas) {
            if (casilla.getPosicion().equals(posicion)) {
                return casilla;
            }
        }
        return CasillaInexistente.INSTANCIA;
    }

    public void colocarUnidad(Unidad unidad, Posicion posicion) {
        if (posicion.getFila() > 8 || posicion.getColumna() > 8)  {
            throw new AccionInvalidaException("Casilla fuera del tablero");
        }
        buscarCasilla(posicion).ocupar(unidad);
    }

    public void daniarEn(Posicion posicion, int danio) {
        buscarCasilla(posicion).recibirImpacto(danio);
    }

    public void moverUnidad(Unidad unidad, Posicion posicion) {
        if (posicion.getFila() > 8 || posicion.getColumna() > 8)  {
            throw new AccionInvalidaException("Casilla fuera del tablero");
        }
        Casilla casilla = buscarCasilla(posicion);
        casilla.ocupar(unidad);
    }

    public void empujar(Posicion origen, Direccion direccion) {
        buscarCasilla(origen).empujarHacia(buscarCasilla(origen.desplazar(direccion)));
    }

    public void registrarObservadorEnEdificios(
            ObservadorEdificio observador) {

        for (Casilla casilla : casillas) {
            Terreno terreno = casilla.getTerreno();

            if (terreno instanceof Edificio) {
                Edificio edificio = (Edificio) terreno;
                edificio.agregarObservador(observador);
            }
        }
    }
}
