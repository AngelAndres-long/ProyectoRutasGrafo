package com.example.proyectorutasgrafo.Vista;

import com.example.proyectorutasgrafo.controlador.ControladorGrafo;
import com.example.proyectorutasgrafo.modelo.Conexion;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.layout.*;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

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

        try {
            Media media = new Media(
                    getClass().getResource("/musica/alex.mp3").toExternalForm()
            );

            mediaPlayer = new MediaPlayer(media);
            mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
            mediaPlayer.setVolume(0.35);
            mediaPlayer.play();

        } catch (Exception e) {
            System.out.println("No se pudo cargar la musica");
        }

        HBox barraSuperior = new HBox();

        barraSuperior.setPadding(new Insets(14, 22, 14, 22));
        barraSuperior.setAlignment(Pos.CENTER);
        barraSuperior.setStyle("-fx-background-color:#0d1b2a;");

        Label titulo = new Label("Sistema de Rutas");

        titulo.setFont(Font.font("System", FontWeight.BOLD, 22));
        titulo.setTextFill(Color.WHITE);

        Button btnSilenciar = new Button("🔊");

        btnSilenciar.setStyle(
                "-fx-background-color:#1b263b;" +
                        "-fx-text-fill:white;" +
                        "-fx-font-size:15;" +
                        "-fx-padding:6 12;" +
                        "-fx-background-radius:8;"
        );

        btnSilenciar.setOnAction(e -> {

            if (mediaPlayer != null) {

                if (silenciado) {
                    mediaPlayer.play();
                    btnSilenciar.setText("🔊");
                } else {
                    mediaPlayer.pause();
                    btnSilenciar.setText("🔇");
                }

                silenciado = !silenciado;
            }
        });

        Region izq = new Region();
        Region der = new Region();

        HBox.setHgrow(izq, Priority.ALWAYS);
        HBox.setHgrow(der, Priority.ALWAYS);

        barraSuperior.getChildren().addAll(
                izq, titulo, der, btnSilenciar
        );

        Label mensaje = new Label("Sistema de Rutas");

        this.setTop(barraSuperior);
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



