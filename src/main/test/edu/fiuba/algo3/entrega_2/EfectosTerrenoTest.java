package edu.fiuba.algo3.entrega_2;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import edu.fiuba.algo3.modelo.unidad.Mech;
import edu.fiuba.algo3.modelo.unidad.MechFactory;
import edu.fiuba.algo3.modelo.unidad.Vek;
import edu.fiuba.algo3.modelo.unidad.VekFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import edu.fiuba.algo3.modelo.terreno.efecto.EfectoAcido;
import edu.fiuba.algo3.modelo.terreno.efecto.EfectoAgua;

class EfectosTerrenoTest {

    private VekFactory vekFactory;
    private MechFactory mechFactory;

    @BeforeEach
    void setUp() {
        vekFactory = new VekFactory();
        mechFactory = new MechFactory();
    }

    @Test
    void unaUnidadTerrestreSobreAcidoRecibeElDobleDeDanio() {
        Mech mech = mechFactory.crearMechDeCombate();

        mech.sufrir(new EfectoAcido());
        mech.recibirDanio(1);

        assertEquals(1, mech.getVidaActual());
    }

    @Test
    void unaUnidadTerrestreSinContaminarRecibeDanioNormal() {
        Mech mech = mechFactory.crearMechDeCombate();

        mech.recibirDanio(1);

        assertEquals(2, mech.getVidaActual());
    }

    @Test
    void contaminarseDosVecesNoAcumulaElMultiplicador() {
        Mech mech = mechFactory.crearMechDeCombate();

        mech.sufrir(new EfectoAcido());
        mech.sufrir(new EfectoAcido());
        mech.recibirDanio(1);

        assertEquals(1, mech.getVidaActual());
    }

    @Test
    void unVekTerrestreSobreAcidoRecibeElDobleDeDanio() {
        Vek luciernaga = vekFactory.crearLuciernaga();

        luciernaga.sufrir(new EfectoAcido());
        luciernaga.recibirDanio(1);

        assertEquals(1, luciernaga.getVidaActual());
    }

    @Test
    void elAvisponNoSeContaminaConAcido() {
        Vek avispon = vekFactory.crearAvispon();

        avispon.sufrir(new EfectoAcido());
        avispon.recibirDanio(1);

        assertEquals(1, avispon.getVidaActual());
    }


    @Test
    void unVekTerrestreEnAguaMuere() {
        Vek saltador = vekFactory.crearSaltador();

        saltador.sufrir(new EfectoAgua());

        assertEquals(0, saltador.getVidaActual());
    }

    @Test
    void elAvisponNoMuereEnAgua() {
        Vek avispon = vekFactory.crearAvispon();
        int vidaInicial = avispon.getVidaActual();

        avispon.sufrir(new EfectoAgua());

        assertEquals(vidaInicial, avispon.getVidaActual());
    }

    @Test
    void unMechEnAguaNoPuedeAtacar() {
        Mech mech = mechFactory.crearMechCanon();

        mech.sufrir(new EfectoAgua());

        assertFalse(mech.puedeAtacar());
    }

    @Test
    void unMechEnAguaNoMuere() {
        Mech mech = mechFactory.crearMechDeCombate();
        int vidaInicial = mech.getVidaActual();

        mech.sufrir(new EfectoAgua());

        assertEquals(vidaInicial, mech.getVidaActual());
    }

    @Test
    void unMechFueraDelAguaPuedeAtacar() {
        Mech mech = mechFactory.crearMechArtillero();

        assertTrue(mech.puedeAtacar());
    }

    @Test
    void unMechContaminadoYEnAguaNoPuedeAtacarYRecibeElDobleDeDanio() {
        Mech mech = mechFactory.crearMechDeCombate();

        mech.sufrir(new EfectoAcido());
        mech.sufrir(new EfectoAgua());
        mech.recibirDanio(1);

        assertFalse(mech.puedeAtacar());
        assertEquals(1, mech.getVidaActual());
    }
}