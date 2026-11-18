package edu.fiuba.algo3.modelo;
import java.util.ArrayList;
import java.util.List;

public class Tablero {

    private List<Casilla> casillas;
    
    // ==================================================
    // CONSTRUCTOR
    // ==================================================

    public Tablero(String[] mapa) {
        ParserMapa parser = new ParserMapa();
        this.casillas = parser.parsear(mapa);
    }
    
    // ==================================================
    // CONSULTAS SOBRE EL TABLERO
    // ==================================================
    
    public int getFilas() {
        return casillas.stream().mapToInt(c -> c.getPosicion().getFila()).max().orElse(-1) + 1;
    }

    public int getColumnas() {
        return casillas.stream().mapToInt(c -> c.getPosicion().getColumna()).max().orElse(-1) + 1;
    }
    
    public Terreno obtenerTerrenoEn(Posicion posicion) {
        Casilla casilla = buscarCasilla(posicion);

        if (casilla == null) {
            return null;
        }

        return casilla.getTerreno();
    } // Se utiliza en TestTablero
    
    public boolean detieneDisparoEn(Posicion posicion) {
        Casilla casilla = buscarCasilla(posicion);

        return casilla != null && casilla.detieneDisparo(); // Delega la consulta a Casilla
    } // Un método que usa Cañon
    
    public boolean estaDentroDelTablero(Posicion posicion) {
        return buscarCasilla(posicion) != null;
    } // Lo usa Cañon para comprobación de límites

    
    // ==================================================
    // BÚSQUEDA DE ELEMENTOS
    // ==================================================

    private Casilla buscarCasilla(Posicion posicion) {
        for (Casilla casilla : casillas) {
            if (casilla.estaEnPosicion(posicion)) {
                return casilla;
            }
        }
        return null;
    }  // Fundamental
    
    // ==================================================
    // COLOCACIÓN Y DESPLAZAMIENTO DE UNIDADES
    // ==================================================
    
    public boolean colocarUnidad(Unidad unidad) {
        Casilla casilla = buscarCasilla(unidad.getPosicion());

        if (casilla == null) {
            return false;
        }

        return casilla.ocupar(unidad);
    }  // Tablero se encarga de encontrar dónde colocarla, y Casilla se encarga de determinar si puede recibirla 
    
    // EL siguiente método ya involucra dos casillas
    public boolean desplazarUnidad(Unidad unidad, Posicion destino) {
        Casilla origen = buscarCasilla(unidad.getPosicion());
        Casilla llegada = buscarCasilla(destino);

        if (origen == null || llegada == null) {
            return false;
        }

        if (!origen.estaOcupadaPor(unidad)) {
            return false;
        }

        if (!llegada.puedeRecibirUnidad()) {
            return false;
        }

        origen.desocupar();
        
        if (!llegada.ocupar(unidad)) {
            origen.ocupar(unidad);
            return false;
        }
        
        unidad.setPosicion(destino);

        llegada.aplicarEfectoDelTerreno();

        return true;
    }  // Revisar
    
    public boolean moverUnidad(Unidad unidad, Posicion destino) {

        if (!unidad.puedeMoverse()) {
            return false;
        }

        boolean seMovio = desplazarUnidad(unidad, destino);

        if (seMovio) {
            unidad.registrarMovimiento();
        }

        return seMovio;
    } 
    
    // ==================================================
    // DAÑO, IMPACTOS Y EMPUJES
    // ==================================================
  
    public ResultadoAtaque resolverImpacto(
            Posicion posicion, Direccion direccion, int danio) {

        Casilla casilla = buscarCasilla(posicion);

        if (casilla == null) {
            return new ResultadoAtaque(0);
        }

        ResultadoDanio resultadoDanio = casilla.aplicarDanio(danio);

        ResultadoAtaque resultadoAtaque =
                new ResultadoAtaque(resultadoDanio.getDanioAlEdificio());

        Unidad sobreviviente = resultadoDanio.getUnidadSobreviviente();

        if (sobreviviente != null) {
            ResultadoAtaque resultadoEmpuje =
                    empujarUnidad(sobreviviente, direccion);

            resultadoAtaque = resultadoAtaque.combinar(resultadoEmpuje);
        }

        return resultadoAtaque;
    }
    
    public ResultadoAtaque daniarEn(Posicion posicion, int danio) {

        Casilla casilla = buscarCasilla(posicion);

        if (casilla == null) {
            return new ResultadoAtaque(0);
        }

        ResultadoDanio resultadoDanio = casilla.aplicarDanio(danio);

        return new ResultadoAtaque(resultadoDanio.getDanioAlEdificio());
    }
    
    public ResultadoAtaque empujarUnidad(Unidad unidad, Direccion direccion) {

        if (!unidad.estaViva()) {
            return new ResultadoAtaque(0);
        }

        Casilla origen = buscarCasilla(unidad.getPosicion());

        if (origen == null || !origen.estaOcupadaPor(unidad)) {
            return new ResultadoAtaque(0);
        }

        // Calcular el destino

        Posicion posicionActual = unidad.getPosicion();

        int filaDestino = posicionActual.getFila()
                + direccion.getDesplazamientoEnFila();

        int columnaDestino = posicionActual.getColumna()
                + direccion.getDesplazamientoEnColumna();

        Posicion destino = new Posicion(filaDestino, columnaDestino);

        // Obtener la casilla de destino

        Casilla llegada = buscarCasilla(destino);

        if (llegada == null) {
            return new ResultadoAtaque(0);
        }

        // Si el destino está ocupado, ambas unidades reciben daño.

        if (llegada.estaOcupada()) {

            ResultadoDanio resultadoOrigen = origen.aplicarDanio(1);
            ResultadoDanio resultadoLlegada = llegada.aplicarDanio(1);

            int danioTotal =
                    resultadoOrigen.getDanioAlEdificio()
                    + resultadoLlegada.getDanioAlEdificio();

            return new ResultadoAtaque(danioTotal);
        }

        // Si el terreno permite el desplazamiento, mover la unidad.

        if (llegada.puedeRecibirUnidad()) {
            desplazarUnidad(unidad, destino);
            return new ResultadoAtaque(0);
        }

        // Si el terreno bloquea el desplazamiento, ambas casillas reciben daño.

        ResultadoDanio resultadoOrigen = origen.aplicarDanio(1);
        ResultadoDanio resultadoLlegada = llegada.aplicarDanio(1);

        int danioTotal =
                resultadoOrigen.getDanioAlEdificio()
                + resultadoLlegada.getDanioAlEdificio();

        return new ResultadoAtaque(danioTotal);
    }
    
    
    public ResultadoAtaque empujarUnidadEn(
            Posicion posicion, Direccion direccion) {

        Casilla casilla = buscarCasilla(posicion);

        if (casilla == null || !casilla.estaOcupada()) {
            return new ResultadoAtaque(0);
        }

        return empujarUnidad(casilla.getUnidad(), direccion);
    }

}
