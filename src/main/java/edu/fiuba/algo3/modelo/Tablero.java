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
        for (Casilla casilla : casillas) {
            if (casilla.getPosicion().equals(posicion)) {
                return true;
            }
        }
        return false;
    }


    public Casilla getCasilla(Posicion posicion) {
        for (Casilla casilla : casillas) {
            if (casilla.getPosicion().getFila() == posicion.getFila() && 
                casilla.getPosicion().getColumna() == posicion.getColumna()) {
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

    public List<Casilla> getCasillas() {
        return casillas;
    }
}