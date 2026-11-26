package edu.fiuba.algo3.vistas;

import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;

public class VistaCasilla extends StackPane {

    private static final double TAMANIO = 64;

    public VistaCasilla(String simbolo, Color fondo) {
        Rectangle rectangulo = new Rectangle(TAMANIO, TAMANIO, fondo);
        rectangulo.setStroke(Color.BLACK);

        Label etiqueta = new Label(simbolo);
        etiqueta.setFont(Font.font(18));

        getChildren().addAll(rectangulo, etiqueta);
    }
}
