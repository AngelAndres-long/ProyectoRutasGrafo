
package com.example.proyectorutasgrafo.Vista;

import com.example.proyectorutasgrafo.controlador.ControladorGrafo;
import com.example.proyectorutasgrafo.modelo.Conexion;
import com.example.proyectorutasgrafo.modelo.Ubicacion;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.util.List;

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

        VBox panelDerecho = new VBox(10);
        panelDerecho.setPadding(new Insets(16));
        panelDerecho.setPrefWidth(200);
        panelDerecho.setStyle(
                "-fx-background-color:#0d1b2a;" +
                        "-fx-border-color:#415a77;" +
                        "-fx-border-width:2;" +
                        "-fx-border-radius:10;" +
                        "-fx-background-radius:10;"
        );

        Label lblInfo = new Label("Información");
        lblInfo.setTextFill(Color.WHITE);
        lblInfo.setFont(
                Font.font("System", FontWeight.BOLD, 14)
        );

        areaInfo.setEditable(false);
        areaInfo.setStyle(
                "-fx-control-inner-background:#0d1b2a;" +
                        "-fx-text-fill:#e0e0e0;"
        );

        ScrollPane scrollInfo = new ScrollPane(areaInfo);
        scrollInfo.setFitToWidth(true);
        scrollInfo.setPrefHeight(180);
        scrollInfo.setStyle("-fx-background:#0d1b2a;");

        Label lblExtra = new Label("Representación");
        lblExtra.setTextFill(Color.WHITE);
        lblExtra.setFont(
                Font.font("System", FontWeight.BOLD, 14)
        );

        Button btnLista = crearBoton("Lista adyacencia");
        Button btnMatriz = crearBoton("Matriz adyacencia");

        panelDerecho.getChildren().addAll(
                lblInfo, scrollInfo, lblExtra, btnLista, btnMatriz
        );

        areaDibujo.setStyle("-fx-background-color:#0d1b2a;");
        areaDibujo.setPrefHeight(320);
        areaDibujo.setMinHeight(280);
        areaDibujo.setMaxHeight(320);

        Rectangle clip = new Rectangle();
        clip.widthProperty().bind(areaDibujo.widthProperty());
        clip.heightProperty().bind(areaDibujo.heightProperty());
        areaDibujo.setClip(clip);

        TableColumn<Conexion, String> colOrigen =
                new TableColumn<>("Origen");

        colOrigen.setCellValueFactory(c ->
                new javafx.beans.property.SimpleStringProperty(
                        c.getValue().getOrigen().getNombre()
                )
        );

        TableColumn<Conexion, String> colDestino =
                new TableColumn<>("Destino");

        colDestino.setCellValueFactory(c ->
                new javafx.beans.property.SimpleStringProperty(
                        c.getValue().getDestino().getNombre()
                )
        );

        TableColumn<Conexion, Integer> colPeso =
                new TableColumn<>("Peso");

        colPeso.setCellValueFactory(c ->
                new javafx.beans.property.SimpleObjectProperty<>(
                        c.getValue().getPeso()
                )
        );

        tabla.getColumns().addAll(
                colOrigen, colDestino, colPeso
        );

        tabla.setColumnResizePolicy(
                TableView.CONSTRAINED_RESIZE_POLICY
        );

        VBox panelCentral = new VBox(6);
        panelCentral.setPadding(new Insets(6));

        panelCentral.getChildren().addAll(
                areaDibujo, tabla
        );

        VBox.setVgrow(tabla, Priority.ALWAYS);

        btnCargarCSV.setOnAction(e -> {

            FileChooser fc = new FileChooser();
            fc.setTitle("Seleccionar CSV");

            fc.getExtensionFilters().add(
                    new FileChooser.ExtensionFilter("CSV", "*.csv")
            );

            File archivo = fc.showOpenDialog(getScene().getWindow());

            if (archivo != null) {
                controlador.cargarDesdeArchivo(archivo);
                actualizar();
            }
        });

        btnGuardar.setOnAction(e -> {

            controlador.guardarCambios();

            abrirVentanaTexto(
                    "Guardado",
                    "Cambios guardados"
            );
        });

        btnGuardarComo.setOnAction(e -> {
            FileChooser fc = new FileChooser();
            fc.setTitle("Guardar CSV");
            fc.getExtensionFilters().add(new FileChooser.ExtensionFilter("CSV", "*.csv"));

            fc.setInitialFileName("conexiones.csv");

            File archivo = fc.showSaveDialog(getScene().getWindow());

            if (archivo != null) {
                controlador.guardarComo(archivo);
                abrirVentanaTexto("Guardado","Archivo guardado en:\n" + archivo.getAbsolutePath());
            }
        });

        btnNuevo.setOnAction(e -> {

            controlador.nuevoGrafo();
            actualizar();
        });

        btnAgregar.setOnAction(e -> {
            try {
                String o = txtOrigen.getText().trim();
                String d = txtDestino.getText().trim();
                int p = Integer.parseInt(txtPeso.getText().trim());

                if (!o.isEmpty() && !d.isEmpty() && !o.equals(d) && p >= 0) {

                    if (chkNoDirigida.isSelected()) {
                        controlador.agregarConexionNoDirigida(o, d, p);
                    } else {
                        controlador.agregarConexion(o, d, p);
                    }

                    actualizar();

                    txtOrigen.clear();
                    txtDestino.clear();
                    txtPeso.clear();
                }

            } catch (Exception ex) {
                abrirVentanaTexto("Error", "Verifica los datos de la conexión.");
            }
        });

        btnEliminar.setOnAction(e -> {

            String o = txtElimOrigen.getText().trim();
            String d = txtElimDestino.getText().trim();

            if (!o.isEmpty() && !d.isEmpty()) {

                controlador.eliminarConexion(o, d);
                actualizar();

                txtElimOrigen.clear();
                txtElimDestino.clear();
            }
        });

        btnModificarPeso.setOnAction(e -> {
            try {
                String o = txtModOrigen.getText().trim();
                String d = txtModDestino.getText().trim();
                int p = Integer.parseInt(txtModPeso.getText().trim());

                if (!o.isEmpty() && !d.isEmpty() && p >= 0) {

                    controlador.modificarPeso(o, d, p);
                    actualizar();

                    txtModOrigen.clear();
                    txtModDestino.clear();
                    txtModPeso.clear();
                }

            } catch (Exception ex) {
                abrirVentanaTexto("Error", "Verifica los datos del peso.");
            }
        });

        btnModificarNombre.setOnAction(e -> {

            String actual = txtNombreActual.getText().trim();
            String nuevo = txtNombreNuevo.getText().trim();

            if (!actual.isEmpty() && !nuevo.isEmpty()) {

                controlador.modificarNombre(actual, nuevo);
                actualizar();

                txtNombreActual.clear();
                txtNombreNuevo.clear();
            }
        });

        btnBFS.setOnAction(e -> {
            String inicio = txtInicio.getText().trim();
            if (inicio.isEmpty()) {
                abrirVentanaTexto("BFS", "Ingresa un vértice de origen.");
                return;
            }

            List<Ubicacion> resultado = controlador.recorrerBFS(inicio);

            if (resultado != null)
                abrirVentanaTexto("BFS","Recorrido BFS desde " + inicio + ":\n\n" + resultado);

        });

        btnDFS.setOnAction(e -> {

            String inicio = txtInicio.getText().trim();

            if (inicio.isEmpty()) {
                abrirVentanaTexto("DFS", "Ingresa un vértice de origen.");
                return;
            }

            List<Ubicacion> resultado = controlador.recorrerDFS(inicio);

            if (resultado != null) {
                abrirVentanaTexto(
                        "DFS",
                        "Recorrido DFS desde " + inicio + ":\n\n" + resultado
                );
            }
        });

        btnDijkstra.setOnAction(e -> {

            String origen = txtInicio.getText().trim();
            String destino = txtDestinoDijkstra.getText().trim();

            if (origen.isEmpty() || destino.isEmpty()) {
                abrirVentanaTexto(
                        "Dijkstra",
                        "Ingresa un vértice de origen y uno de destino."
                );
                return;
            }

            String resultado = controlador.obtenerRutaDijkstra(
                    origen, destino
            );

            abrirVentanaTexto("Dijkstra", resultado);
        });

        btnLista.setOnAction(e -> {

            String resultado = controlador.obtenerListaAdyacenciaTexto();

            abrirVentanaTexto(
                    "Lista de adyacencia",
                    resultado
            );
        });

        btnMatriz.setOnAction(e -> {

            String resultado = controlador.obtenerMatriz();

            abrirVentanaTexto(
                    "Matriz de adyacencia",
                    resultado
            );
        });


        actualizar();

        this.setTop(barraSuperior);
        this.setLeft(scrollIzquierdo);
        this.setRight(panelDerecho);
        this.setCenter(panelCentral);

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

    private void actualizar() {

        var grafo = controlador.getGrafo();

        StringBuilder sb = new StringBuilder();

        sb.append("Vértices: ")
                .append(grafo.getNumeroVertices())
                .append("\n");

        sb.append("Aristas: ")
                .append(grafo.getNumeroAristas())
                .append("\n\n");

        for (Ubicacion u : grafo.getVertices()) {
            sb.append("• ")
                    .append(u.getNombre())
                    .append("\n");
        }

        areaInfo.setText(sb.toString());

        tabla.getItems().setAll(
                grafo.getConexiones()
        );
    }

    private void abrirVentanaTexto(String titulo, String contenido) {

        Stage stage = new Stage();
        stage.setTitle(titulo);

        TextArea area = new TextArea(contenido);
        area.setEditable(false);

        area.setStyle(
                "-fx-control-inner-background:#0d1b2a;" +
                        "-fx-text-fill:#e0e0e0;" +
                        "-fx-font-family:monospace;"
        );

        stage.setScene(new Scene(area, 620, 480));
        stage.show();
    }
}
