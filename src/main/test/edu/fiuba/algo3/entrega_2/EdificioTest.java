package edu.fiuba.algo3.entrega_2;

import edu.fiuba.algo3.modelo.*;
import edu.fiuba.algo3.modelo.casilla.Casilla;
import edu.fiuba.algo3.modelo.unidad.Vek;
import edu.fiuba.algo3.modelo.unidad.VekFactory;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class EdificioTest {

    private Tablero tableroCon(String[] mapa, RedElectrica red) {
        Tablero tablero = new Tablero(mapa, new ParserTablero());
        tablero.registrarObservadorEnEdificios(red);
        return tablero;
    }

    @Test
    public void unEdificioSeConvierteEnSueloAlRecibirDanio() {
        Tablero tablero = tableroCon(new String[]{ "E" },new RedElectrica());
        Posicion posicion = new Posicion(0, 0);

        tablero.buscarCasilla(posicion).recibirImpacto(1);

        Vek vek = new VekFactory().crearSaltador();
        tablero.colocarUnidad(vek, posicion);
        assertEquals(posicion, vek.posicion());
    }

    @Test
    public void unEdificioNoSeDestruyeSiRecibeCeroDanio() {
        RedElectrica red = new RedElectrica();
        Tablero tablero = tableroCon(new String[]{ "E" },red);
        tablero.buscarCasilla(new Posicion(0, 0)).recibirImpacto(0);

        assertEquals(5, red.getEnergia());
    }

    @Test
    public void daniarEdificioReduceEnergiaDeLaRedElectrica() {
        RedElectrica red = new RedElectrica();
        Tablero tablero = tableroCon(new String[]{ "--E--" },red);

        tablero.buscarCasilla(new Posicion(0, 2)).recibirImpacto(1);

        assertEquals(4, red.getEnergia());
    }

    @Test
    public void unEdificioDestruidoNoRestaRedElectricaUnaSegundaVez() {
        RedElectrica red = new RedElectrica();
        Tablero tablero = tableroCon(new String[]{ "E" },red);
        Casilla casilla = tablero.buscarCasilla(new Posicion(0, 0));

        casilla.recibirImpacto(1);
        casilla.recibirImpacto(1);       // ahora es Suelo

        assertEquals(4, red.getEnergia());
    }

    @Test
    public void empujarUnidadContraEdificioReduceLaRedElectricaYDaniaALaUnidad() {
        RedElectrica red = new RedElectrica();
        Tablero tablero = tableroCon(new String[]{
            "-----",
            "-----",
            "-----",
            "-----",
            "--E--"
        },red);
        Vek empujada = new VekFactory().crearSaltador();
        tablero.colocarUnidad(empujada, new Posicion(3, 2));

        tablero.empujar(new Posicion(3, 2), Direccion.SUR);

        assertEquals(4, red.getEnergia());
        assertEquals(1, empujada.getVidaActual());
        assertEquals(new Posicion(3, 2), empujada.posicion());   // no se movió
    }
    
    //@Disabled("Pendiente: reescribir Arma/Juego al modelo nuevo")
    //@Test
    //public void ataqueDeArtilleriaReduceLaRedElectricaPorTodosLosEdificiosDanados() { /* ... */ }
    
     
    //@Disabled("Pendiente: reescribir Arma/Juego al modelo nuevo")
    //@Test
    //public void artilleriaEmpujandoUnidadContraEdificioReduceLaRedElectrica() { /* ... */ }
    
}