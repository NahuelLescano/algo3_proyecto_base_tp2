package edu.fiuba.algo3.modelo.tablero;

import java.util.ArrayList;
import java.util.Collections;

import java.util.List;
import java.util.Random;

public class GeneradorDeMapa {

    private static final int TAMANIO = 8;
    private static final int CANTIDAD_EDIFICIOS = 5;
    private static final int MAX_MONTANIAS = 6;
    private static final int MAX_AGUA = 6;
    private static final int MAX_ACIDO = 4;

    private final Random random;

    public GeneradorDeMapa(Random random) {
        this.random = random;
    }

    private int indicePosicion;

    public String[] generar() {
        char[][] grilla = grillaDeSuelo();
        List<Posicion> posiciones = posicionesMezcladas();
        indicePosicion = 0;

        colocar(grilla, posiciones, 'E', CANTIDAD_EDIFICIOS);
        colocar(grilla, posiciones, 'M', random.nextInt(MAX_MONTANIAS + 1));
        colocar(grilla, posiciones, 'A', random.nextInt(MAX_AGUA + 1));
        colocar(grilla, posiciones, 'X', random.nextInt(MAX_ACIDO + 1));

        return aFilas(grilla);
    }

    private void colocar(char[][] grilla, List<Posicion> posiciones, char simbolo, int cantidad) {
        int colocados = 0;
        while (colocados < cantidad && indicePosicion < posiciones.size()) {
            Posicion pos = posiciones.get(indicePosicion);
            indicePosicion++;
            grilla[pos.getFila()][pos.getColumna()] = simbolo;
            colocados++;
        }
    }


    private List<Posicion> posicionesMezcladas() {
        List<Posicion> posiciones = new ArrayList<>();
        for (int fila = 0; fila < TAMANIO; fila++) {
            for (int columna = 0; columna < TAMANIO; columna++) {
                posiciones.add(new Posicion(fila, columna));
            }
        }
        Collections.shuffle(posiciones, random);
        return posiciones;
    }

    private char[][] grillaDeSuelo() {
        char[][] grilla = new char[TAMANIO][TAMANIO];
        for (int fila = 0; fila < TAMANIO; fila++) {
            for (int columna = 0; columna < TAMANIO; columna++) {
                grilla[fila][columna] = '-';
            }
        }
        return grilla;
    }

    private String[] aFilas(char[][] grilla) {
        String[] filas = new String[TAMANIO];
        for (int fila = 0; fila < TAMANIO; fila++) {
            filas[fila] = new String(grilla[fila]);
        }
        return filas;
    }
}
