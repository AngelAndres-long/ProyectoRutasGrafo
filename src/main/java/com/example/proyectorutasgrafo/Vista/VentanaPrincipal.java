
package com.example.proyectorutasgrafo.Vista;

import com.example.proyectorutasgrafo.algoritmos.MedidorRendimiento;
import com.example.proyectorutasgrafo.controlador.ControladorGrafo;
import com.example.proyectorutasgrafo.modelo.*;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.TextAlignment;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.Map;

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
        areaDibujo.setOnMouseClicked(e -> abrirVentanaGrafo(null, null));

        areaDibujo.widthProperty().addListener((obs, anterior, nuevo) -> {
            if (nuevo.doubleValue() > 0) {
                double escala = Math.min(0.50, nuevo.doubleValue() / 1100.0);
                dibujarGrafoEn(areaDibujo, escala, null, null);
            }
        });

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

            if (resultado != null) {
                abrirVentanaTexto("BFS", "Recorrido BFS desde " + inicio + ":\n\n" + resultado);
                abrirVentanaGrafo(resultado, null);
            }
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
                abrirVentanaGrafo(resultado, null);
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


        btnQuick.setOnAction(e ->
                tabla.getItems().setAll(
                        controlador.ordenarPorPesoQuick()
                )
        );

        btnShell.setOnAction(e ->
                tabla.getItems().setAll(
                        controlador.ordenarPorPesoShell()
                )
        );

        btnNombre.setOnAction(e ->
                tabla.getItems().setAll(
                        controlador.ordenarPorNombreQuick()
                )
        );

        btnTiempos.setOnAction(e -> {

            if (controlador.getGrafo().getConexiones().isEmpty()) {
                abrirVentanaTexto(
                        "Tiempos",
                        "No hay conexiones para analizar"
                );
                return;
            }

            StringBuilder sb = new StringBuilder(
                    "Tiempos de ejecución:\n\n"
            );

            long t1, t2;

            String primerVertice = controlador.getGrafo()
                    .getVertices()
                    .iterator()
                    .next()
                    .getNombre();

            t1 = System.nanoTime();
            controlador.recorrerBFS(primerVertice);
            t2 = System.nanoTime();

            sb.append("BFS: ")
                    .append(String.format("%.3f", (t2 - t1) / 1_000_000.0))
                    .append(" ms\n");

            t1 = System.nanoTime();
            controlador.recorrerDFS(primerVertice);
            t2 = System.nanoTime();

            sb.append("DFS: ")
                    .append(String.format("%.3f", (t2 - t1) / 1_000_000.0))
                    .append(" ms\n");

            t1 = System.nanoTime();
            controlador.calcularDijkstra(primerVertice);
            t2 = System.nanoTime();

            sb.append("Dijkstra: ")
                    .append(String.format("%.3f", (t2 - t1) / 1_000_000.0))
                    .append(" ms\n\n");

            double tiempoQuick = MedidorRendimiento.medirQuickSort(
                    controlador.getGrafo().getConexiones()
            );

            double tiempoShell = MedidorRendimiento.medirShellSort(
                    controlador.getGrafo().getConexiones()
            );

            int conexiones = controlador.getGrafo().getNumeroAristas();

            List<ResultadosPrueba> resultados = new ArrayList<>();

            resultados.add(new ResultadosPrueba(
                    "QuickSort", "Peso", tiempoQuick, conexiones
            ));

            resultados.add(new ResultadosPrueba(
                    "Shell Sort", "Peso", tiempoShell, conexiones
            ));

            CategoryAxis ejeX = new CategoryAxis();
            NumberAxis ejeY = new NumberAxis();

            ejeX.setLabel("Algoritmo");
            ejeY.setLabel("Tiempo promedio (ms)");

            BarChart<String, Number> grafica =
                    new BarChart<>(ejeX, ejeY);

            grafica.setTitle("Comparación de algoritmos de ordenamiento");
            grafica.setLegendVisible(false);
            grafica.setAnimated(false);

            XYChart.Series<String, Number> serie = new XYChart.Series<>();

            for (ResultadosPrueba resultado : resultados) {

                serie.getData().add(
                        new XYChart.Data<>(
                                resultado.getAlgoritmo(),
                                resultado.getTiempo()
                        )
                );

                sb.append(resultado.getAlgoritmo())
                        .append(" (promedio): ")
                        .append(String.format("%.6f", resultado.getTiempo()))
                        .append(" ms\n");
            }

            grafica.getData().add(serie);

            sb.append("\nConexiones analizadas: ")
                    .append(conexiones)
                    .append("\n");

            sb.append("Mediciones por ordenamiento: 15\n");

            TextArea detalles = new TextArea(sb.toString());
            detalles.setEditable(false);
            detalles.setPrefRowCount(8);

            VBox contenido = new VBox(10, grafica, detalles);
            contenido.setPadding(new Insets(12));

            VBox.setVgrow(grafica, Priority.ALWAYS);

            Stage ventana = new Stage();
            ventana.setTitle("Análisis empírico de algoritmos");
            ventana.setScene(new Scene(contenido, 950, 750));
            ventana.show();
        });


        btnCompararArchivos.setOnAction(event -> {

            FileChooser fc = new FileChooser();
            fc.setTitle("Seleccionar archivos CSV para comparar");

            fc.getExtensionFilters().add(
                    new FileChooser.ExtensionFilter("Archivos CSV", "*.csv")
            );

            List<File> archivos = fc.showOpenMultipleDialog(
                    getScene().getWindow()
            );

            if (archivos == null || archivos.isEmpty()) {
                return;
            }

            StringBuilder sb = new StringBuilder();

            sb.append("COMPARACIÓN DE ORDENAMIENTOS\n\n");
            sb.append("15 mediciones por algoritmo (promedio en ms).\n\n");

            List<ResultadosPrueba> resultadosArchivos = new ArrayList<>();

            CategoryAxis ejeX = new CategoryAxis();
            NumberAxis ejeY = new NumberAxis();

            ejeX.setLabel("Archivos CSV");
            ejeY.setLabel("Tiempo promedio (ms)");

            BarChart<String, Number> grafica =
                    new BarChart<>(ejeX, ejeY);

            grafica.setTitle("Comparación de rendimiento entre archivos");
            grafica.setAnimated(false);
            grafica.setCategoryGap(20);

            XYChart.Series<String, Number> serieQuick =
                    new XYChart.Series<>();

            serieQuick.setName("QuickSort");

            XYChart.Series<String, Number> serieShell =
                    new XYChart.Series<>();

            serieShell.setName("ShellSort");

            int numeroArchivo = 1;

            for (File archivo : archivos) {

                try {

                    Grafo grafoPrueba = LectorCSV.cargarDesdeArchivo(archivo);

                    int vertices = grafoPrueba.getNumeroVertices();
                    int conexiones = grafoPrueba.getNumeroAristas();

                    sb.append("Archivo: ")
                            .append(archivo.getName())
                            .append("\n");

                    sb.append("Vértices: ")
                            .append(vertices)
                            .append("\n");

                    sb.append("Conexiones: ")
                            .append(conexiones)
                            .append("\n");

                    if (conexiones == 0) {
                        sb.append("No hay conexiones válidas para analizar.\n\n");
                        continue;
                    }

                    double tiempoQuick = MedidorRendimiento.medirQuickSort(
                            grafoPrueba.getConexiones()
                    );

                    double tiempoShell = MedidorRendimiento.medirShellSort(
                            grafoPrueba.getConexiones()
                    );

                    ResultadosPrueba resultadoQuick = new ResultadosPrueba("QuickSort","Peso",tiempoQuick,conexiones
                    );

                    ResultadosPrueba resultadoShell = new ResultadosPrueba("ShellSort", "Peso", tiempoShell,conexiones);

                    resultadosArchivos.add(resultadoQuick);
                    resultadosArchivos.add(resultadoShell);

                    String etiqueta = numeroArchivo + ": " + archivo.getName();

                    serieQuick.getData().add(
                            new XYChart.Data<>(
                                    etiqueta,
                                    resultadoQuick.getTiempo()
                            )
                    );

                    serieShell.getData().add(
                            new XYChart.Data<>(
                                    etiqueta,
                                    resultadoShell.getTiempo()
                            )
                    );

                    sb.append(String.format(
                            "QuickSort: %.6f ms\n",
                            resultadoQuick.getTiempo()
                    ));

                    sb.append(String.format(
                            "ShellSort: %.6f ms\n\n",
                            resultadoShell.getTiempo()
                    ));

                } catch (Exception ex) {

                    sb.append("Error al analizar ")
                            .append(archivo.getName())
                            .append(": ")
                            .append(ex.getMessage())
                            .append("\n\n");
                }

                numeroArchivo++;
            }

            if (resultadosArchivos.isEmpty()) {
                abrirVentanaTexto(
                        "Comparación de archivos CSV",
                        sb.toString()
                );
                return;
            }

            grafica.getData().addAll(serieQuick, serieShell);

            TextArea detalles = new TextArea(sb.toString());
            detalles.setEditable(false);
            detalles.setPrefHeight(200);

            VBox contenido = new VBox(10, grafica, detalles);
            contenido.setPadding(new Insets(12));

            VBox.setVgrow(grafica, Priority.ALWAYS);

            Stage ventana = new Stage();
            ventana.setTitle("Comparación de archivos CSV");

            ventana.setScene(new Scene(contenido, 1000, 750));
            ventana.show();
        });



        btnMostrar.setOnAction(e -> abrirVentanaGrafo(null, null));

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

        double ancho = areaDibujo.getWidth() > 0 ? areaDibujo.getWidth() : 500;
        double escala = Math.min(0.50, ancho / 1100.0);
        dibujarGrafoEn(areaDibujo, escala, null, null);
    }

    private void abrirVentanaGrafo(List<Ubicacion> visitados,
                                   Map<Ubicacion, Integer> distancias) {
        Stage stage = new Stage();
        stage.setTitle(visitados != null ? "Recorrido del grafo" : "Grafo");

        Pane pane = new Pane();
        pane.setStyle("-fx-background-color:#0d1b2a;");
        pane.setPrefSize(1800, 2450);

        dibujarGrafoEn(pane, 1.2, visitados, distancias);

        ScrollPane scroll = new ScrollPane(pane);
        scroll.setPannable(true);
        scroll.setHbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
        scroll.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);

        stage.setScene(new Scene(scroll, 1100, 750));
        stage.show();
    }

    private void dibujarGrafoEn(Pane pane,
                                double escala,
                                List<Ubicacion> visitados,
                                Map<Ubicacion, Integer> distancias) {
        pane.getChildren().clear();

        try {
            Image fondo = new Image(
                    getClass().getResource("/fondo.gif").toExternalForm()
            );
            ImageView bg = new ImageView(fondo);
            bg.fitWidthProperty().bind(pane.widthProperty());
            bg.fitHeightProperty().bind(pane.heightProperty());
            bg.setPreserveRatio(false);
            pane.getChildren().add(bg);
        } catch (Exception ex) {
            pane.setStyle("-fx-background-color:#0d1b2a;");
        }

        Grafo grafo = controlador.getGrafo();
        List<Ubicacion> lista = new ArrayList<>(grafo.getVertices());
        int limite = Math.min(lista.size(), 50);
        int columnas = 5;

        Map<String, double[]> posiciones = new HashMap<>();
        double espaciadoX = 250 * escala;
        double espaciadoY = 185 * escala;

        for (int i = 0; i < limite; i++) {
            Ubicacion u = lista.get(i);
            int fila = i / columnas;
            int columna = i % columnas;
            double x = 160 * escala + columna * espaciadoX;
            double y = 150 * escala + fila * espaciadoY;
            posiciones.put(u.getNombre(), new double[]{x, y});
        }

        for (Conexion cn : grafo.getConexiones()) {
            double[] origen = posiciones.get(cn.getOrigen().getNombre());
            double[] destino = posiciones.get(cn.getDestino().getNombre());

            if (origen == null || destino == null) {
                continue;
            }

            double ox = origen[0];
            double oy = origen[1];
            double dx = destino[0];
            double dy = destino[1];

            Line linea = new Line(ox, oy, dx, dy);
            linea.setStroke(Color.web("#cccccc"));
            linea.setStrokeWidth(Math.max(1, 1.5 * escala));
            pane.getChildren().add(linea);

            double mx = (ox + dx) / 2;
            double my = (oy + dy) / 2;
            double angulo = Math.atan2(dy - oy, dx - ox);
            double offsetX = Math.sin(angulo) * 18;
            double offsetY = -Math.cos(angulo) * 18;

            Label peso = new Label(String.valueOf(cn.getPeso()));
            peso.setLayoutX(mx + offsetX - 12);
            peso.setLayoutY(my + offsetY - 6);
            peso.setTextFill(Color.WHITE);
            peso.setStyle(
                    "-fx-background-color:#0d1b2a;" +
                            "-fx-padding:1 4;" +
                            "-fx-background-radius:3;" +
                            "-fx-font-size:10;"
            );
            pane.getChildren().add(peso);
        }

        for (int i = 0; i < limite; i++) {
            Ubicacion u = lista.get(i);
            double[] posicion = posiciones.get(u.getNombre());
            double x = posicion[0];
            double y = posicion[1];

            int orden = -1;
            if (visitados != null) {
                for (int j = 0; j < visitados.size(); j++) {
                    if (visitados.get(j).getNombre().equals(u.getNombre())) {
                        orden = j + 1;
                        break;
                    }
                }
            }

            double radio = 28 * escala;
            StackPane nodo = new StackPane();
            nodo.setLayoutX(x - radio);
            nodo.setLayoutY(y - radio);

            Circle circulo = new Circle(radio);
            circulo.setFill(orden > 0 ? Color.web("#e63946") : Color.web("#457b9d"));
            circulo.setStroke(Color.WHITE);
            circulo.setStrokeWidth(2);

            String texto = u.getNombre();
            if (texto.length() > 8) {
                texto = texto.substring(0, 6) + "..";
            }
            if (orden > 0) {
                texto = orden + "\n" + texto;
            }

            Label etiqueta = new Label(texto);
            etiqueta.setTextFill(Color.WHITE);
            etiqueta.setFont(Font.font("System", FontWeight.BOLD, Math.max(8, 10 * escala)));
            etiqueta.setAlignment(Pos.CENTER);
            etiqueta.setTextAlignment(TextAlignment.CENTER);
            nodo.getChildren().addAll(circulo, etiqueta);
            pane.getChildren().add(nodo);
        }
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