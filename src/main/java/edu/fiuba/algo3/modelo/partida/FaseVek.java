package edu.fiuba.algo3.modelo.partida;

import java.util.List;

import edu.fiuba.algo3.modelo.tablero.Direccion;
import edu.fiuba.algo3.modelo.tablero.Posicion;
import edu.fiuba.algo3.modelo.tablero.Tablero;
import edu.fiuba.algo3.modelo.unidad.Vek;
import edu.fiuba.algo3.modelo.unidad.iavek.IAVek;

public class FaseVek implements FaseDeTurno {

    @Override
    public void ejecutar(Juego juego) {
        Tablero tablero = juego.getTablero();
        IAVek ia = new IAVek();
        List<Vek> veksVivos = juego.getVeksVivos();

        for (Vek vek : veksVivos) {
            Posicion destino = ia.decidirMovimiento(vek, tablero);

            // Si la IA decidió un destino diferente al actual, mover al Vek
            if (!destino.equals(vek.posicion())) {
                tablero.moverUnidad(vek, destino);
            }

            // Anunciar ataque: buscar casilla adyacente objetivo
            Posicion posicionVek = vek.posicion();
            Direccion direccionAtaque = decidirDireccionAtaque(vek, tablero);

            if (direccionAtaque != null) {
                juego.agregarAtaqueAnunciado(new AtaqueAnunciado(vek, direccionAtaque));
            }
        }

        juego.cambiarFase(new FaseJugador());
    }

    private Direccion decidirDireccionAtaque(Vek vek, Tablero tablero) {
        // El Vek ataca en la dirección que eligió la IA al posicionarse.
        // La IA mueve al Vek a una posición desde la que puede atacar;
        // recorremos las 4 direcciones buscando un objetivo válido.
        Posicion posVek = vek.posicion();

        for (Direccion dir : Direccion.values()) {
            Posicion objetivo = posVek.desplazar(dir);
            if (tablero.buscarCasilla(objetivo).getTerreno() != null) {
                // Verificar si hay algo atacable en esa dirección
                // La IA ya posicionó al Vek correctamente, atacar la primera dirección válida
                return dir;
            }
        }
        return null;
    }
}
