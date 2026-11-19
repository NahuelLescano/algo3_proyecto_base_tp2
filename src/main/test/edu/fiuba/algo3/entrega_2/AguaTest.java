package edu.fiuba.algo3.entrega_2;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import edu.fiuba.algo3.modelo.*;

public class AguaTest {
    @Test
    public void vekTerrestreMuereAlSerEmpujadoAlAgua() {
        // Arrange
        String[] mapa = {
                "AAAAAAAA",
                "AAAAAAAA",
                "--------",
                "--------",
                "--------",
                "--------",
                "--------",
                "--------"
        };

        Tablero tablero = new Tablero(mapa);
        Luciernaga luciernaga = new Luciernaga(new Posicion(3,2));
        Posicion posicion = new Posicion(3,2);

        tablero.empujarUnidadEn(posicion, Direccion.NORTE);
        luciernaga.aplicarEfectoDelAgua();

        assertFalse(luciernaga.estaViva());
    }

    @Test
    public void vekAvisponNoMuereAlSerEmpujadoAlAgua() {
        // Arrange
        String[] mapa = {
                "AAAAAAAA",
                "AAAAAAAA",
                "--------",
                "--------",
                "--------",
                "--------",
                "--------",
                "--------"
        };

        Tablero tablero = new Tablero(mapa);
        Avispon avispon = new Avispon(new Posicion(3,2));
        Posicion posicion = new Posicion(3,2);

        tablero.empujarUnidadEn(posicion, Direccion.NORTE);
        avispon.aplicarEfectoDelAgua();

        assertTrue(avispon.estaViva());
    }

    @Test
    public void mechEnAguaNoPuedeAtacar() {
        String[] mapa = {
                "AAAAAAAA",
                "AAAAAAAA",
                "--------",
                "--------",
                "--------",
                "--------",
                "--------",
                "--------"
        };

        Tablero tablero = new Tablero(mapa);

        Mech mech = new Mech(5, new Posicion(1, 2), new Punio());

        tablero.colocarUnidad(mech);

        Posicion objetivo = new Posicion(2, 2);

        boolean pudoAtacar = mech.iniciarAtaque(tablero, objetivo);

        assertFalse(pudoAtacar);
    }


}