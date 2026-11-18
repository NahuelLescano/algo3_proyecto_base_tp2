package edu.fiuba.algo3.modelo;

import java.util.ArrayList;
import java.util.List;

public class ParserMapa {

    public List<Casilla> parsear(String[] mapa) {

        if (mapa == null || mapa.length == 0) {
            throw new IllegalArgumentException("El mapa no puede ser nulo o vacío");
        }

        List<Casilla> casillas = new ArrayList<>();

        int cantidadColumnas = mapa[0].length();

        for (int fila = 0; fila < mapa.length; fila++) {

            if (mapa[fila] == null || mapa[fila].length() != cantidadColumnas) {
                throw new IllegalArgumentException("El mapa debe ser rectangular");
            }

            for (int columna = 0; columna < mapa[fila].length(); columna++) {

                char caracter = mapa[fila].charAt(columna);

                Posicion posicion = new Posicion(fila, columna);
                Terreno terreno = convertirCaracterATerreno(caracter);

                casillas.add(new Casilla(terreno, posicion));
            }
        }

        return casillas;
    }

    private Terreno convertirCaracterATerreno(char caracter) {

        switch (caracter) {
            case '-': return new Suelo();
            case 'A': return new Agua();
            case 'M': return new Montania();
            case 'X': return new Acido();
            case 'E': return new Edificio(1);
            default:
                throw new IllegalArgumentException(
                    "Caracter de terreno inválido: " + caracter
                );
        }
    }
}
