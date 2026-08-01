/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.tresenrayajuego;
import javafx.application.Application; 
import javafx.scene.Scene; 
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 *
 * @author pycca
 */
public class App extends Application {
    
    @Override
    public void start(Stage stage) {
        Label mensaje = new Label("JavaFX funciona correctamente");

        StackPane contenedor = new StackPane(mensaje);
        Scene escena = new Scene(contenedor, 500, 300);

        stage.setTitle("Tres en Raya");
        stage.setScene(escena);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
