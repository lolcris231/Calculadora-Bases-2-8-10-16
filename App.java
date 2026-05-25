package Calculadora.Calculadora;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class App extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        Label etiqueta1 = new Label("Selecciona el tipo de ingreso");
        etiqueta1.setStyle("-fx-font-size: 16px; -fx-font-weight: bold;");

        
        ToggleButton decimal = new ToggleButton("DECIMAL");
        ToggleButton binario = new ToggleButton("BINARIO");
        ToggleButton octal = new ToggleButton("OCTAL");
        ToggleButton hexadecimal = new ToggleButton("HEXADECIMAL");

        ToggleGroup grupo1 = new ToggleGroup();
        decimal.setToggleGroup(grupo1);
        binario.setToggleGroup(grupo1);
        octal.setToggleGroup(grupo1);
        hexadecimal.setToggleGroup(grupo1);

        HBox filaBotones = new HBox(10);
        filaBotones.getChildren().addAll(decimal, binario, octal, hexadecimal);
        filaBotones.setAlignment(javafx.geometry.Pos.CENTER);

        
        TextField ingreso = new TextField();
        ingreso.setPrefWidth(230);
        ingreso.setAlignment(javafx.geometry.Pos.CENTER);
        ingreso.setStyle("-fx-prompt-text-fill: rgba(0,0,0,0.4);");

        grupo1.selectedToggleProperty().addListener((seleccion, textInicial, textNuevo) -> {
            if (textNuevo == null) {
                ingreso.setText("");   
                ingreso.setPromptText("Selecciona una opción");
                return;
            }

            String textoBoton = ((ToggleButton) textNuevo).getText();

            switch (textoBoton) {
                case "DECIMAL":
                    ingreso.setPromptText("Ingrese números enteros positivos");
                    break;
                case "BINARIO":
                    ingreso.setPromptText("Ingrese únicamente números 0 o 1");
                    break;
                case "OCTAL":
                    ingreso.setPromptText("Ingrese únicamente números de 0 a 7");
                    break;
                case "HEXADECIMAL":
                    ingreso.setPromptText("Ingrese únicamente números enteros positivos y letras desde A hasta F");
                    break;
                default:
                    ingreso.setPromptText("Selección desconocida");
                    break;
            }
            ingreso.setText("");
        });

        grupo1.selectToggle(decimal);

        
        
        VBox filas = new VBox(15);
        filas.getChildren().addAll(etiqueta1, filaBotones, ingreso);
        filas.setAlignment(javafx.geometry.Pos.CENTER);

        Scene scene = new Scene(filas, 500, 250);
        stage.setTitle("Calculadora de bases");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}