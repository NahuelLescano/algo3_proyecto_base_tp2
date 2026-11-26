package edu.fiuba.algo3.vistas;

import edu.fiuba.algo3.SystemInfo;
import edu.fiuba.algo3.modelo.estructura.RedElectrica;
import edu.fiuba.algo3.modelo.partida.Juego;
import edu.fiuba.algo3.modelo.tablero.ParserTablero;
import edu.fiuba.algo3.modelo.tablero.Tablero;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 * JavaFX App
 */
public class App extends Application {

    @Override
    public void start(Stage stage) {
        String[] mapa = {
                "------E-",
                "-E--M---",
                "---AA---",
                "-M----E-",
                "---XX-MX",
                "-E----M-",
                "----A---",
                "--E-----"
        };

        ParserTablero parser = new ParserTablero();
        Tablero tablero = new Tablero(mapa, parser);
        RedElectrica redElectrica = new RedElectrica();
        Juego juego = new Juego(tablero, redElectrica);

        BorderPane raiz = new BorderPane();

        PanelRedElectrica panelRed = new PanelRedElectrica();
        panelRed.actualizar(juego.getRedElectrica());

        raiz.setTop(panelRed);
        raiz.setCenter(new VistaTablero(mapa));

        stage.setTitle("Into the Breach");
        stage.setScene(new Scene(raiz, 700, 700));
        stage.show();
    }
}