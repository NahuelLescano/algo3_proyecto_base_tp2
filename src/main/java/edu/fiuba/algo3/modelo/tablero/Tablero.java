package edu.fiuba.algo3.modelo.tablero;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import edu.fiuba.algo3.modelo.casilla.Casilla;
import edu.fiuba.algo3.modelo.casilla.CasillaDelTablero;
import edu.fiuba.algo3.modelo.casilla.CasillaInexistente;
import edu.fiuba.algo3.modelo.estructura.ObservadorEdificio;
import edu.fiuba.algo3.modelo.excepciones.AccionInvalidaException;
import edu.fiuba.algo3.modelo.excepciones.AtaqueInvalidoException;
import edu.fiuba.algo3.modelo.terreno.Edificio;
import edu.fiuba.algo3.modelo.terreno.Terreno;
import edu.fiuba.algo3.modelo.unidad.Unidad;

public class Tablero {

    private Map<Posicion, Casilla> casillas;

    public Tablero(String[] mapa, ParserTablero parser) {
        if (mapa == null || mapa.length == 0) {
            throw new IllegalArgumentException("El mapa no puede ser nulo o vacío");
        }
        this.casillas = new HashMap<>();
        for (int fila = 0; fila < mapa.length; fila++) {
            String linea = mapa[fila];
            for (int columna = 0; columna < linea.length(); columna++) {
                Posicion posicion = new Posicion(fila, columna);
                Terreno terreno = parser.convertirCaracterATerreno(linea.charAt(columna));
                casillas.put(posicion, new CasillaDelTablero(terreno, posicion));
            }
        }
    }

    public Casilla buscarCasilla(Posicion posicion) {
        return casillas.getOrDefault(posicion, CasillaInexistente.INSTANCIA);
    }


    private Casilla buscarCasillaExistente(Posicion posicion) {
        Casilla casilla = buscarCasilla(posicion);

        if (casilla == CasillaInexistente.INSTANCIA) {
            throw new AccionInvalidaException("Casilla fuera del tablero");
        }

        return casilla;
    }

    public void colocarUnidad(Unidad unidad, Posicion posicion) {
        buscarCasillaExistente(posicion).ocupar(unidad);
    }

    public void daniarEn(Posicion posicion, int danio) {
        buscarCasillaExistente(posicion).recibirImpacto(danio);
    }

    public void moverUnidad(Unidad unidad, Posicion posicion) {
        buscarCasillaExistente(posicion).ocupar(unidad);
    }

    public void empujar(Posicion origen, Direccion direccion) {
        Casilla casillaOrigen = buscarCasillaExistente(origen);
        Casilla casillaDestino = buscarCasillaExistente(origen.desplazar(direccion));

        casillaOrigen.empujarHacia(casillaDestino);
    }

    public void registrarObservadorEnEdificios(
            ObservadorEdificio observador) {

        for (Casilla casilla : casillas.values()) {
            Terreno terreno = casilla.getTerreno();

            if (terreno instanceof Edificio) {
                Edificio edificio = (Edificio) terreno;
                edificio.agregarObservador(observador);
            }
        }
    }    
    // public void emergerEn(Posicion posicion, Unidad vek) {
    //    buscarCasillaExistente(posicion).recibirEmergencia(vek);
    // }
}
