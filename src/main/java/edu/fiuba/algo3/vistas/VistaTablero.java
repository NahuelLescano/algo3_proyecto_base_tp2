package edu.fiuba.algo3.vistas;

import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;

public class VistaTablero extends GridPane {

    public VistaTablero(String[] mapa) {
        for (int fila = 0; fila < mapa.length; fila++) {
            for (int col = 0; col < mapa[fila].length(); col++) {
                char tipo = mapa[fila].charAt(col);
                String simbolo = (tipo == '-') ? "" : String.valueOf(tipo);
                add(new VistaCasilla(simbolo, colorPara(tipo)), col, fila);
            }
        }
    }

    private Color colorPara(char tipo) {
        switch (tipo) {
            case 'E': return Color.web("#F2C14E");
            case 'M': return Color.web("#9E9E9E");
            case 'A': return Color.web("#7EC8E3");
            case 'X': return Color.web("#A8E06C");
            default:  return Color.web("#EEEEEE");
        }
    }
}