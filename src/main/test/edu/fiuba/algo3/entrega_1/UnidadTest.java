package edu.fiuba.algo3.entrega_1;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import  edu.fiuba.algo3.modelo.unidad.*;
class UnidadTest {

    class UnidadDePrueba extends Unidad {

        public UnidadDePrueba() {
            super(3);
        }

        @Override
        public void hundirse() {
            morir();
        }
    }   // Creo una unidad de prueba de la clase abstracta Unidad, está probando el comportamiento de Veks y Mechs

    @Test
    public void unidadComienzaConLaVidaIndicadaEnConstructor() {
        Unidad unidad = new UnidadDePrueba();

        assertEquals(3, unidad.getVidaActual());
    }

    @Test
    public void unidadPierdeVidaAlRecibirDanio() {
        Unidad unidad = new UnidadDePrueba();

        unidad.recibirDanio(1);

        assertEquals(2, unidad.getVidaActual());
    }

    @Test
    public void unidadPierdeTodaSuVidaAlRecibirDanioSuficiente() {
        Unidad unidad = new UnidadDePrueba();

        unidad.recibirDanio(3);

        assertEquals(0, unidad.getVidaActual());
    }

    @Test
    public void unidadNoTieneVidaNegativa() {
        Unidad unidad = new UnidadDePrueba();

        unidad.recibirDanio(5);

        assertEquals(0, unidad.getVidaActual());
    }

    @Test
    public void recibirDanioNegativoLanzaExcepcion() {
        Unidad unidad = new UnidadDePrueba();

        assertThrows(
            IllegalArgumentException.class,
            () -> unidad.recibirDanio(-1)
        );

        assertEquals(3, unidad.getVidaActual());
    }
}
