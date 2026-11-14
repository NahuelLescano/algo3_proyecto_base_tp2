package edu.fiuba.algo3.modelo;
import java.util.ArrayList;
import java.util.List;

public class Tablero {
    private List<Casilla> casillas;

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
    
    public boolean colocarUnidad(Unidad unidad) {
        Casilla casilla = buscarCasilla(unidad.getPosicion());

        if (casilla == null || casilla.estaOcupada()) {
            return false;
        }

        casilla.ocupar(unidad);
        return true;
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

    public boolean estaDentroDelTablero(Posicion posicion) {
        return buscarCasilla(posicion) != null;
    }


    private Casilla buscarCasilla(Posicion posicion) {
        for (Casilla casilla : casillas) {
            if (casilla.getPosicion().equals(posicion)) {
                return casilla;
            }
        }
        return null;
    }

    public int getFilas() {
        return casillas.stream().mapToInt(c -> c.getPosicion().getFila()).max().orElse(-1) + 1;
    }

    public int getColumnas() {
        return casillas.stream().mapToInt(c -> c.getPosicion().getColumna()).max().orElse(-1) + 1;
    }

    public Terreno obtenerTerrenoEn(Posicion posicion) {
    for (Casilla casilla : casillas) {
        if (casilla.getPosicion().getFila() == posicion.getFila() && 
            casilla.getPosicion().getColumna() == posicion.getColumna()) {
            return casilla.getTerreno();
        }
    }
    return null;
}
    
    public boolean hayUnidad(Posicion posicion) {
        Casilla casilla = buscarCasilla(posicion);
        return casilla != null && casilla.estaOcupada();
    }
    
    public boolean bloqueaDisparos(Posicion posicion) {
        Casilla casilla = buscarCasilla(posicion);
        return casilla != null && casilla.bloqueaDisparos();
    }
    
    public boolean desplazarUnidad(Unidad unidad, Posicion destino) {
        Casilla origen = buscarCasilla(unidad.getPosicion());
        Casilla llegada = buscarCasilla(destino);

        if (origen == null || llegada == null) {
            return false;
        }

        if (origen.getUnidad() != unidad) {
            return false;
        }

        if (!llegada.puedeRecibirUnidad(unidad)) {
            return false;
        }

        origen.desocupar();
        llegada.ocupar(unidad);

        unidad.setPosicion(destino);

        llegada.aplicarEfectoDelTerreno();

        return true;
    }
    
    public void resolverImpacto(Posicion posicion, Direccion direccion, int danio) {

        Casilla casilla = buscarCasilla(posicion);

        if (casilla == null) {
            return;
        }

        if (casilla.estaOcupada()) {
            Unidad unidad = casilla.getUnidad();

            casilla.recibirDanio(danio);
            
            if (!unidad.estaViva()) {
                return;
            }
            
            empujarUnidad(unidad, direccion);
            return;
        }

        casilla.recibirDanio(danio);
    }
    
    public void empujarUnidad(Unidad unidad, Direccion direccion) {
    
        if (!unidad.estaViva()) {
            return;
        }
        
        Casilla origen = buscarCasilla(unidad.getPosicion());

        if (origen == null || origen.getUnidad() != unidad) {
            return;
        }

        // Calculo el destino
        
        int filaDestino = unidad.getPosicion().getFila()
                + direccion.getDesplazamientoEnFila();

        int columnaDestino = unidad.getPosicion().getColumna()
                + direccion.getDesplazamientoEnColumna();

        Posicion destino = new Posicion(filaDestino, columnaDestino);
        

        if (!estaDentroDelTablero(destino)) {
            return;
        }
        
        // Obtengo la casilla de destino

        Casilla llegada = buscarCasilla(destino);

        // Veo si el destino está ocupado por otra unidad, en tal caso el disparo llega a esta ella
        if (llegada.estaOcupada()) {
            origen.recibirDanio(1);
            llegada.recibirDanio(1);
            return;
        }

        // Si llego hasta acá es porque la casilla está dentro del tablero y no está ocupada, compruebo si el terreno permite el movimiento
        if (llegada.getTerreno().esTransitable()) {
            desplazarUnidad(unidad, destino);
            return;
        }

        origen.recibirDanio(1);
        llegada.recibirDanio(1);
    }
    

    public void daniarEn(Posicion posicion, int danio) {
        Casilla casilla = buscarCasilla(posicion);

        if (casilla == null) {
            return;
        }
        casilla.recibirDanio(danio);
    }

    public void empujarUnidadEn(Posicion posicion, Direccion direccion) {
        Casilla casilla = buscarCasilla(posicion);

        if (casilla == null || !casilla.estaOcupada()) {
            return;
        }

        empujarUnidad(casilla.getUnidad(), direccion);
    }

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

}
