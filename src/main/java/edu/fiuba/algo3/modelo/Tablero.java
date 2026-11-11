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

    
    public boolean hayUnidad(Posicion posicion) {
        Casilla casilla = buscarCasilla(posicion);
        return casilla != null && casilla.estaOcupada();
    }
    
    public boolean bloqueaDisparos(Posicion posicion) {
        Casilla casilla = buscarCasilla(posicion);
        return casilla != null && casilla.getTerreno().bloqueaDisparos();
    }
    
    public boolean moverUnidad(Unidad unidad, Posicion destino) {
        Casilla origen = buscarCasilla(unidad.getPosicion());
        Casilla llegada = buscarCasilla(destino);

        if (origen == null || llegada == null) {
            return false;
        }

        if (origen.getUnidad() != unidad) {
            return false;
        }

        if (llegada.estaOcupada() || !llegada.getTerreno().esTransitable()) {
            return false;
        }  // El destino debe estar libre y ser transitable

        origen.desocupar();
        llegada.ocupar(unidad);

        unidad.setPosicion(destino);

        llegada.getTerreno().aplicarEfecto(unidad);   // Se aplica el efecto del terreno de destino
        
        retirarUnidadSiMurio(unidad);

        return true;
    }
    
    public void resolverImpacto(Posicion posicion, Direccion direccion, int danio) {

        Casilla casilla = buscarCasilla(posicion);

        if (casilla == null) {
            return;
        }

        if (casilla.estaOcupada()) {
            Unidad unidad = casilla.getUnidad();

            aplicarDanioAUnidad(unidad, danio);
            
            if (!unidad.estaViva()) {
                retirarUnidadSiMurio(unidad);
                return;
            }
            
            empujarUnidad(unidad, direccion);
            return;
        }

        Terreno terreno = casilla.getTerreno();
        terreno.recibirDanio(danio);
    }
    
    public void empujarUnidad(Unidad unidad, Direccion direccion) {
    
        // Si la unidad ya murió, no puedo empujarla
        if (!unidad.estaViva()) {
            retirarUnidadSiMurio(unidad);
            return;
        }
        
        // Evito empujar una unidad que no está en el tablero
        
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
        
        // Compruebo los límites, si el destino está fuera del tablero no se puede mover la unidad

        if (!estaDentroDelTablero(destino)) {
            return;
        }
        
        // Obtengo la casilla de destino

        Casilla llegada = buscarCasilla(destino);

        // Veo si el destino está ocupado por otra unidad, en tal caso el disparo llega a esta ella
        if (llegada.estaOcupada()) {
            Unidad otraUnidad = llegada.getUnidad();

            // Ambas reciben 1 de daño
            aplicarDanioAUnidad(unidad, 1);
            aplicarDanioAUnidad(otraUnidad, 1);

            return;
        }

        // Si llego hasta acá es porque la casilla está dentro del tablero y no está ocupada, compruebo si el terreno permite el movimiento
        if (llegada.getTerreno().esTransitable()) {
            moverUnidad(unidad, destino);
            return;
        }

        // Si llego hasta acá es porque la casilla está dentro del tablero, no está ocupada y el terreno no es transitable, entonces la unidad choca con un obstáculo, la unidad recibe daño y el terreno también (pero según las consignas sólo Tereno lo hace)
        aplicarDanioAUnidad(unidad, 1);
        
        llegada.getTerreno().recibirDanio(1);
    }
    
    private void retirarUnidadSiMurio(Unidad unidad) {
        if (!unidad.estaViva()) {
            Casilla casilla = buscarCasilla(unidad.getPosicion());

            if (casilla != null && casilla.getUnidad() == unidad) {
                casilla.desocupar();
            }
        }
    }
    
    private void aplicarDanioAUnidad(Unidad unidad, int danio) {
        unidad.recibirDanio(danio);
        retirarUnidadSiMurio(unidad);
    }
}
