package edu.fiuba.algo3.modelo.tablero;

import java.util.ArrayList;
import java.util.List;

import edu.fiuba.algo3.modelo.excepciones.IntoTheBreachExcepcion;
import edu.fiuba.algo3.modelo.terreno.Terreno;
import edu.fiuba.algo3.modelo.unidad.Unidad;

public class Tablero {
    private final List<Casilla> casillas;
    private final int filas;
    private final int columnas;

    public Tablero(String[] mapa) {
        this(mapa, new ParserTablero());
    }

    public Tablero(String[] mapa, ParserTablero parser) {
        if (mapa == null || mapa.length == 0 || mapa[0] == null || mapa[0].isEmpty()) {
            throw new IllegalArgumentException("El mapa no puede ser nulo o vacío");
        }

        this.filas = mapa.length;
        this.columnas = mapa[0].length();
        this.casillas = new ArrayList<>();

        for (int fila = 0; fila < filas; fila++) {
            String linea = mapa[fila];

            if (linea.length() != columnas) {
                throw new IllegalArgumentException("El mapa debe ser rectangular");
            }

            for (int columna = 0; columna < columnas; columna++) {
                Terreno terreno = parser.convertirCaracterATerreno(linea.charAt(columna));
                Casilla casilla = new Casilla(terreno, new Posicion(fila, columna));
                this.casillas.add(casilla);
            }
        }
    }

    public Casilla buscarCasilla(Posicion posicion) {
        Casilla casilla = buscarCasillaOFuera(posicion);

        if (casilla == null) {
            throw new IllegalArgumentException(
                    "Casilla inexistente: fila " + posicion.getFila() +
                            ", columna " + posicion.getColumna());
        }

        return casilla;
    }

    private Casilla buscarCasillaOFuera(Posicion posicion) {
        if (!estaDentroDelTablero(posicion)) {
            return null;
        }

        return casillas.get(posicion.getFila() * columnas + posicion.getColumna());
    }

    public boolean estaDentroDelTablero(Posicion posicion) {
        return posicion != null
                && posicion.getFila() >= 0
                && posicion.getFila() < filas
                && posicion.getColumna() >= 0
                && posicion.getColumna() < columnas;
    }

    public int getFilas() {
        return filas;
    }

    public int getColumnas() {
        return columnas;
    }

    public Terreno obtenerTerrenoEn(Posicion posicion) {
        Casilla casilla = buscarCasillaOFuera(posicion);
        return casilla == null ? null : casilla.getTerreno();
    }

    public boolean hayUnidad(Posicion posicion) {
        Casilla casilla = buscarCasillaOFuera(posicion);
        return casilla != null && casilla.estaOcupada();
    }

    public Unidad obtenerUnidad(Posicion posicion) {
        Casilla casilla = buscarCasillaOFuera(posicion);
        return casilla == null ? null : casilla.getUnidad();
    }

    public boolean bloqueaDisparos(Posicion posicion) {
        Casilla casilla = buscarCasillaOFuera(posicion);
        return casilla != null && casilla.getTerreno().bloqueaDisparos();
    }

    public boolean detieneDisparoEn(Posicion posicion) {
        Casilla casilla = buscarCasillaOFuera(posicion);

        return casilla != null
                && (casilla.estaOcupada() || casilla.getTerreno().bloqueaDisparos());
    }

    // ==================================================
    // COLOCACIÓN Y MOVIMIENTO
    // ==================================================

    public boolean colocarUnidad(Unidad unidad) {
        if (unidad == null || unidad.getPosicion() == null) {
            return false;
        }

        return colocarUnidad(unidad, unidad.getPosicion());
    }

    public boolean colocarUnidad(Unidad unidad, Posicion posicion) {
        Casilla casilla = buscarCasillaOFuera(posicion);

        if (casilla == null || casilla.estaOcupada()) {
            return false;
        }

        unidad.actualizarPosicion(posicion);
        casilla.ocupar(unidad);
        return true;
    }

    public boolean moverUnidad(Unidad unidad, Posicion destino) {
        try {
            unidad.mover(this, destino);
            return true;
        } catch (IntoTheBreachExcepcion e) {
            return false;
        }
    }

    public boolean desplazarUnidad(Unidad unidad, Posicion destino) {
        return this.trasladar(unidad, destino, true);
    }

    public boolean revertirDesplazamiento(Unidad unidad, Posicion destino) {
        return this.trasladar(unidad, destino, false);
    }

    private boolean trasladar(Unidad unidad, Posicion destino, boolean aplicarEfectoDelTerreno) {
        Casilla origen = buscarCasillaOFuera(unidad.getPosicion());
        Casilla llegada = buscarCasillaOFuera(destino);

        if (origen == null || llegada == null) {
            return false;
        }

        if (origen.getUnidad() != unidad) {
            return false;
        }

        if (llegada.estaOcupada() || !llegada.getTerreno().esTransitable()) {
            return false;
        }

        origen.desocupar();
        llegada.ocupar(unidad);
        unidad.actualizarPosicion(destino);

        if (aplicarEfectoDelTerreno) {
            llegada.getTerreno().aplicarEfecto(unidad);
        }

        retirarUnidadSiMurio(unidad);
        return true;
    }

    // ==================================================
    // DAÑO, IMPACTOS Y EMPUJES
    // ==================================================

    public void resolverImpacto(Posicion posicion, Direccion direccion, int danio) {
        Casilla casilla = buscarCasillaOFuera(posicion);

        if (casilla == null) {
            return;
        }

        if (casilla.estaOcupada()) {
            Unidad unidad = casilla.getUnidad();

            aplicarDanioAUnidad(unidad, danio);

            if (!unidad.estaViva()) {
                return;
            }

            empujarUnidad(unidad, direccion);
            return;
        }

        casilla.getTerreno().recibirDanio(danio);
    }

    public void daniarEn(Posicion posicion, int danio) {
        Casilla casilla = buscarCasillaOFuera(posicion);

        if (casilla == null) {
            return;
        }

        if (casilla.estaOcupada()) {
            aplicarDanioAUnidad(casilla.getUnidad(), danio);
        } else {
            casilla.getTerreno().recibirDanio(danio);
        }
    }

    public void empujar(Unidad unidad, Direccion direccion) {
        empujarUnidad(unidad, direccion);
    }

    public void empujarUnidad(Unidad unidad, Direccion direccion) {
        if (unidad == null || !unidad.estaViva()) {
            retirarUnidadSiMurio(unidad);
            return;
        }

        Casilla origen = buscarCasillaOFuera(unidad.getPosicion());

        if (origen == null || origen.getUnidad() != unidad) {
            return;
        }

        Posicion destino = unidad.getPosicion().desplazar(direccion);

        if (!estaDentroDelTablero(destino)) {
            return;
        }

        Casilla llegada = buscarCasillaOFuera(destino);

        if (llegada.estaOcupada()) {
            Unidad otraUnidad = llegada.getUnidad();

            aplicarDanioAUnidad(unidad, 1);
            aplicarDanioAUnidad(otraUnidad, 1);
            return;
        }

        if (llegada.getTerreno().esTransitable()) {
            desplazarUnidad(unidad, destino);
            return;
        }

        aplicarDanioAUnidad(unidad, 1);
        llegada.getTerreno().recibirDanio(1);
    }

    public void empujarUnidadEn(Posicion posicion, Direccion direccion) {
        Casilla casilla = buscarCasillaOFuera(posicion);

        if (casilla == null || !casilla.estaOcupada()) {
            return;
        }

        empujarUnidad(casilla.getUnidad(), direccion);
    }

    private void retirarUnidadSiMurio(Unidad unidad) {
        if (unidad == null || unidad.estaViva()) {
            return;
        }

        Casilla casilla = buscarCasillaOFuera(unidad.getPosicion());

        if (casilla != null && casilla.getUnidad() == unidad) {
            casilla.desocupar();
        }
    }

    private void aplicarDanioAUnidad(Unidad unidad, int danio) {
        unidad.recibirDanio(danio);
        retirarUnidadSiMurio(unidad);
    }
}
