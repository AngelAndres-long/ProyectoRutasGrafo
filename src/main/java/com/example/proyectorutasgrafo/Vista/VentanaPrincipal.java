package com.example.proyectorutasgrafo.Vista;

import com.example.proyectorutasgrafo.controlador.ControladorGrafo;
import com.example.proyectorutasgrafo.modelo.Conexion;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.media.MediaPlayer;

public class VentanaPrincipal extends BorderPane {
    private ControladorGrafo controlador;
    private TextArea areaInfo;
    private TableView<Conexion> tabla;
    private Pane areaDibujo;
    private MediaPlayer mediaPlayer;
    private boolean silenciado = false;

    public VentanaPrincipal() {

        controlador = new ControladorGrafo();

        areaInfo = new TextArea();
        tabla = new TableView<>();
        areaDibujo = new Pane();

        Label mensaje = new Label("Sistema de Rutas");
        this.setCenter(mensaje);

        this.setStyle("-fx-background-color:#0d1b2a;");

    }

    public void guardarAlSalir() {
        controlador.guardarAlSalir();
    }

    private Button crearBoton(String texto) {
        Button btn = new Button(texto);
        btn.setMaxWidth(Double.MAX_VALUE);
        btn.setStyle(
                "-fx-background-color:#415a77;" +
                        "-fx-text-fill:white;" +
                        "-fx-padding:8;" +
                        "-fx-background-radius:6;"
        );

        return btn;
    }
}
