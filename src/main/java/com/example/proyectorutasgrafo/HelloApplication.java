
package com.example.proyectorutasgrafo;

import com.example.proyectorutasgrafo.Vista.VentanaPrincipal;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class HelloApplication extends Application {

    @Override
    public void start(Stage stage) {

        VentanaPrincipal ventana = new VentanaPrincipal();

        Scene scene = new Scene(ventana, 1200, 750);

        stage.setTitle("Sistema de Rutas");
        stage.setScene(scene);

        stage.setOnCloseRequest(e -> {
            ventana.guardarAlSalir();
        });

        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
