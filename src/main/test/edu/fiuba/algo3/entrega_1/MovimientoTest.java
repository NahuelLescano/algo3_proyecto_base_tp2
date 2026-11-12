package edu.fiuba.algo3.entrega_1;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import edu.fiuba.algo3.modelo.movimiento.*;
import edu.fiuba.algo3.modelo.excepciones.AccionYaRealizadaException;
import edu.fiuba.algo3.modelo.excepciones.CasillaBloqueadaException;
import edu.fiuba.algo3.modelo.excepciones.MovimientoInvalidoException;

import static org.junit.jupiter.api.Assertions.*;

public class MovimientoTest {

    private Tablero tablero;
    private MechCombate mech;

    @BeforeEach
    public void setUp() {
        tablero = new Tablero();
        mech = new MechCombate();
    }

    @Test
    public void test01MechSeMueveDentroDeSuRangoYActualizaOcupacionDeCasillas() {
        Posicion origen = new Posicion(2, 2);
        Posicion destino = new Posicion(2, 4); // distancia 2 <= rango 3

        tablero.colocarUnidad(origen, mech);
        tablero.moverUnidad(origen, destino);

        // El origen quedó libre: permite colocar otra unidad sin error
        assertDoesNotThrow(() -> tablero.colocarUnidad(origen, new MechCombate()));

        // El destino quedó ocupado: intentar colocar otra unidad allí lanza excepción
        assertThrows(CasillaBloqueadaException.class, () -> {
            tablero.colocarUnidad(destino, new MechCombate());
        });
    }

    @Test
    public void test02MechNoPuedeMoverseMasAllaDeSuRangoMaximo() {
        Posicion origen = new Posicion(1, 1);
        Posicion destino = new Posicion(1, 5); // distancia 4 > rango 3

        tablero.colocarUnidad(origen, mech);

        assertThrows(MovimientoInvalidoException.class, () -> {
            tablero.moverUnidad(origen, destino);
        });

        // Sigue en el origen: intentar moverlo a una posición válida funciona
        assertDoesNotThrow(() -> tablero.moverUnidad(origen, new Posicion(1, 2)));
    }

    @Test
    public void test03MechNoPuedeMoverseACasillaConMontana() {
        Posicion origen = new Posicion(3, 3);
        Posicion destino = new Posicion(3, 4);

        tablero.colocarTerreno(destino, new Montana());
        tablero.colocarUnidad(origen, mech);

        assertThrows(CasillaBloqueadaException.class, () -> {
            tablero.moverUnidad(origen, destino);
        });
    }

    @Test
    public void test04MechNoPuedeMoverseACasillaOcupadaPorOtraUnidad() {
        Posicion origen = new Posicion(0, 0);
        Posicion destino = new Posicion(0, 1);
        MechCombate obstaculo = new MechCombate();

        tablero.colocarUnidad(origen, mech);
        tablero.colocarUnidad(destino, obstaculo);

        assertThrows(CasillaBloqueadaException.class, () -> {
            tablero.moverUnidad(origen, destino);
        });
    }

    @Test
    public void test05MechNoPuedeMoverseDosVecesEnElMismoTurno() {
        Posicion origen = new Posicion(2, 2);
        Posicion intermedio = new Posicion(2, 3);
        Posicion destinoFinal = new Posicion(2, 4);

        tablero.colocarUnidad(origen, mech);
        tablero.moverUnidad(origen, intermedio);

        assertThrows(AccionYaRealizadaException.class, () -> {
            tablero.moverUnidad(intermedio, destinoFinal);
        });
    }

    @Test
    public void test06MechQueTerminaEnAguaNoPuedeAtacar() {
        Posicion origen = new Posicion(4, 4);
        Posicion casillaAgua = new Posicion(4, 5);

        tablero.colocarTerreno(casillaAgua, new Agua());
        tablero.colocarUnidad(origen, mech);

        assertTrue(mech.puedeAtacar());
        tablero.moverUnidad(origen, casillaAgua);
        assertFalse(mech.puedeAtacar());
    }

    @Test
    public void test07IntentarMoverDesdeCasillaVaciaLanzaExcepcion() {
        Posicion origenVacio = new Posicion(5, 5);
        Posicion destino = new Posicion(5, 6);

        assertThrows(MovimientoInvalidoException.class, () -> {
            tablero.moverUnidad(origenVacio, destino);
        });
    }

    @Test
    public void test08EmpujeHaciaCasillaLibreDesplazaUnidad() {
        Posicion origen = new Posicion(3, 3);
        Posicion destino = new Posicion(3, 4);

        tablero.colocarUnidad(origen, mech);
        tablero.empujar(origen, destino);

        assertDoesNotThrow(() -> tablero.colocarUnidad(origen, new MechCombate()));
        assertThrows(CasillaBloqueadaException.class, () -> {
            tablero.colocarUnidad(destino, new MechCombate());
        });
    }

    @Test
    public void test09EmpujeContraMontanaNoDesplazaUnidadYLeInfligeDanio() {
        Posicion origen = new Posicion(3, 3);
        Posicion casillaMontana = new Posicion(3, 4);
        Saltador vek = new Saltador(); // 2 de vida

        tablero.colocarTerreno(casillaMontana, new Montana());
        tablero.colocarUnidad(origen, vek);

        tablero.empujar(origen, casillaMontana);

        // No se desplazó: sigue ocupando origen y recibió 1 de daño (sobrevive con 1 vida)
        assertFalse(vek.estaDestruida());
        assertThrows(CasillaBloqueadaException.class, () -> {
            tablero.colocarUnidad(origen, new MechCombate());
        });
    }
}
