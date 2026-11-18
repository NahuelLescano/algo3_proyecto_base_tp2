package edu.fiuba.algo3.modelo;
import java.util.ArrayList;
import java.util.List;

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
            casillas.add(new Casilla(terreno, new Posicion(fila, columna)));
        }
    }
}

    public Casilla buscarCasilla(Posicion p) {
        if (p.getFila() <  0 || p.getFila() >=  8 || p.getColumna() >= 8 || p.getColumna() < 0) 
            throw new IllegalArgumentException("Casilla inexistente");
        int indice = p.getFila() * 8 + p.getColumna();
        return casillas.get(indice);
    }


    /*
    private List<Casilla> casillas;
    
    // ==================================================
    // CONSTRUCTOR
    // ==================================================

    public Tablero(String[] mapa) {
        if (mapa == null || mapa.length == 0) {
            throw new IllegalArgumentException("El mapa no puede ser nulo o vacío");
        }

        this.casillas = new ArrayList<>();

        for (int fila = 0; fila < mapa.length; fila++) {
            String linea = mapa[fila];

            for (int columna = 0; columna < linea.length(); columna++) {
                char caracter = linea.charAt(columna);

                Posicion posicion = new Posicion(fila, columna);
                Terreno terreno = convertirCaracterATerreno(caracter);
                Casilla casilla = new Casilla(terreno, posicion);
                this.casillas.add(casilla);
            }
        }

    }
    
    private Terreno convertirCaracterATerreno(char caracter) {
        switch (caracter) {
            case '-':
                return new Suelo();
            case 'A':
                return new Agua();
            case 'M':
                return new Montania();
            case 'X':
                return new Acido();
            case 'E':
                return new Edificio(1);
            default:
                throw new IllegalArgumentException("Caracter de terreno inválido: " + caracter);
        }
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
    
    public void resolverImpacto(Posicion posicion, Direccion direccion, int danio) {
        Casilla casilla = buscarCasilla(posicion);

        if (casilla == null) {
            return;
        }

        Unidad sobreviviente = casilla.aplicarDanio(danio);

        if (sobreviviente != null) {
            empujarUnidad(sobreviviente, direccion);
        }
    }  // resolverImpacto encuentra la casilla alcanzada, aplica el daño, determina si una unidad sobrevivió y si corresponde la empuja hacia una dirección
    
    public void daniarEn(Posicion posicion, int danio) {
        Casilla casilla = buscarCasilla(posicion);

        if (casilla == null) {
            return;
        }
        casilla.aplicarDanio(danio);
    } // La diferencia con resolverImpacto() es que no intenta empujar a ninguna unidad.
    
    public void empujarUnidad(Unidad unidad, Direccion direccion) {
    
        if (!unidad.estaViva()) {
            return;
        }
        
        Casilla origen = buscarCasilla(unidad.getPosicion());

        if (origen == null || !origen.estaOcupadaPor(unidad)) {
            return;
        }

        // Calculo el destino

        Posicion posicionActual = unidad.getPosicion();

        int filaDestino = posicionActual.getFila()
                + direccion.getDesplazamientoEnFila();

        int columnaDestino = posicionActual.getColumna()
                + direccion.getDesplazamientoEnColumna();

        Posicion destino = new Posicion(filaDestino, columnaDestino);

        // Obtengo la casilla de destino
        
        Casilla llegada = buscarCasilla(destino);

        if (llegada == null) {
            return;
        }

        // Veo si el destino está ocupado por otra unidad, en tal caso el disparo llega a esta ella
        if (llegada.estaOcupada()) {
            origen.aplicarDanio(1);
            llegada.aplicarDanio(1);
            return;
        }

        // Si llego hasta acá es porque la casilla está dentro del tablero y no está ocupada, compruebo si el terreno permite el movimiento
        if (llegada.puedeRecibirUnidad()) {
            desplazarUnidad(unidad, destino);
            return;
        }

        origen.aplicarDanio(1);
        llegada.aplicarDanio(1);
    }
    
    public void empujarUnidadEn(Posicion posicion, Direccion direccion) {
        Casilla casilla = buscarCasilla(posicion);

        if (casilla == null || !casilla.estaOcupada()) {
            return;
        }

        empujarUnidad(casilla.getUnidad(), direccion);
    }  // Este método busca qué unidad hay en esa posición y la empuja, pasandole como parámetro a empujarUnidad

    */
}