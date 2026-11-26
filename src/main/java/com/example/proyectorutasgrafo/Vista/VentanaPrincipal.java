
package com.example.proyectorutasgrafo.Vista;

import com.example.proyectorutasgrafo.controlador.ControladorGrafo;
import com.example.proyectorutasgrafo.modelo.Conexion;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
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
                    getClass().getResource("/alex.mp3").toExternalForm()
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

        VBox panelIzquierdo = new VBox(8);

        panelIzquierdo.setPadding(new Insets(16));
        panelIzquierdo.setStyle("-fx-background-color:#0d1b2a;");

        Label lblControles = new Label("Controles");
        lblControles.setTextFill(Color.WHITE);
        lblControles.setFont(
                Font.font("System", FontWeight.BOLD, 15)
        );

        Button btnCargarCSV = crearBoton("Importar CSV");
        Button btnGuardar = crearBoton("Guardar cambios");
        Button btnGuardarComo = crearBoton("Guardar como");
        Button btnNuevo = crearBoton("Nuevo grafo");

        TextField txtOrigen = new TextField();
        txtOrigen.setPromptText("Origen");

        TextField txtDestino = new TextField();
        txtDestino.setPromptText("Destino");

        TextField txtPeso = new TextField();
        txtPeso.setPromptText("Peso");

        CheckBox chkNoDirigida = new CheckBox("No dirigida");
        chkNoDirigida.setTextFill(Color.WHITE);

        Button btnAgregar = crearBoton("Agregar");

        TextField txtElimOrigen = new TextField();
        txtElimOrigen.setPromptText("Origen");

        TextField txtElimDestino = new TextField();
        txtElimDestino.setPromptText("Destino");

        Button btnEliminar = crearBoton("Eliminar");

        TextField txtModOrigen = new TextField();
        txtModOrigen.setPromptText("Origen");

        TextField txtModDestino = new TextField();
        txtModDestino.setPromptText("Destino");

        TextField txtModPeso = new TextField();
        txtModPeso.setPromptText("Nuevo peso");

        Button btnModificarPeso = crearBoton("Modificar peso");

        TextField txtNombreActual = new TextField();
        txtNombreActual.setPromptText("Nombre actual");

        TextField txtNombreNuevo = new TextField();
        txtNombreNuevo.setPromptText("Nuevo nombre");

        Button btnModificarNombre = crearBoton("Modificar nombre");

        TextField txtInicio = new TextField();
        txtInicio.setPromptText("Origen");

        TextField txtDestinoDijkstra = new TextField();
        txtDestinoDijkstra.setPromptText("Destino");

        Button btnBFS = crearBoton("BFS");
        Button btnDFS = crearBoton("DFS");
        Button btnDijkstra = crearBoton("Dijkstra");

        Button btnQuick = crearBoton("QuickSort");
        Button btnShell = crearBoton("ShellSort");
        Button btnNombre = crearBoton("Ordenar nombre");
        Button btnTiempos = crearBoton("Medir tiempos");
        Button btnMostrar = crearBoton("Ver grafo");

        Button btnCompararArchivos =
                crearBoton("Comparar archivos CSV");

        Label lblAgregar = new Label("Agregar");
        Label lblEliminar = new Label("Eliminar");
        Label lblModificarPeso = new Label("Modificar peso");
        Label lblModificarNombre = new Label("Modificar nombre");
        Label lblAlgoritmos = new Label("Algoritmos");

        lblAgregar.setTextFill(Color.WHITE);
        lblEliminar.setTextFill(Color.WHITE);
        lblModificarPeso.setTextFill(Color.WHITE);
        lblModificarNombre.setTextFill(Color.WHITE);
        lblAlgoritmos.setTextFill(Color.WHITE);

        panelIzquierdo.getChildren().addAll(
                lblControles,
                btnCompararArchivos,
                btnCargarCSV, btnGuardar, btnGuardarComo, btnNuevo,
                lblAgregar, txtOrigen, txtDestino, txtPeso,
                chkNoDirigida, btnAgregar,
                lblEliminar, txtElimOrigen, txtElimDestino, btnEliminar,
                lblModificarPeso, txtModOrigen, txtModDestino,
                txtModPeso, btnModificarPeso,
                lblModificarNombre, txtNombreActual,
                txtNombreNuevo, btnModificarNombre,
                new Separator(),
                lblAlgoritmos, txtInicio, txtDestinoDijkstra,
                btnBFS, btnDFS, btnDijkstra,
                new Separator(),
                btnQuick, btnShell, btnNombre, btnTiempos, btnMostrar
        );

        ScrollPane scrollIzquierdo = new ScrollPane(panelIzquierdo);

        scrollIzquierdo.setFitToWidth(true);
        scrollIzquierdo.setPrefWidth(250);

        scrollIzquierdo.setStyle(
                "-fx-background:#0d1b2a;" +
                        "-fx-border-color:#415a77;" +
                        "-fx-border-width:2;" +
                        "-fx-border-radius:10;"
        );

        scrollIzquierdo.setVbarPolicy(
                ScrollPane.ScrollBarPolicy.AS_NEEDED
        );

        Label mensaje = new Label("Sistema de Rutas");
        mensaje.setTextFill(Color.WHITE);

        this.setTop(barraSuperior);
        this.setLeft(scrollIzquierdo);
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
