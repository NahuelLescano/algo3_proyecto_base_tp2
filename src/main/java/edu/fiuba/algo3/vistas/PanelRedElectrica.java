package edu.fiuba.algo3.vistas;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;

public class PanelRedElectrica extends HBox {

    private static final int CAPACIDAD_MAXIMA = 5;

    public PanelRedElectrica() {
        setSpacing(6);
        setPadding(new Insets(10));
    }

    public void actualizar(int puntos) {
        int visibles = Math.max(0, puntos);
        getChildren().clear();

        Label titulo = new Label("⚡ Red Eléctrica: " + visibles + "/" + CAPACIDAD_MAXIMA);
        titulo.setFont(Font.font(16));
        getChildren().add(titulo);

        for (int i = 0; i < CAPACIDAD_MAXIMA; i++) {
            Rectangle celda = new Rectangle(20, 20);
            celda.setFill(i < visibles ? Color.web("#F2C14E") : Color.web("#EEEEEE"));
            celda.setStroke(Color.BLACK);
            getChildren().add(celda);
        }
    }
}