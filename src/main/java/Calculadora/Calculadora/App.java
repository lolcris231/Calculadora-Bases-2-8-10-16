package Calculadora.Calculadora;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.util.List;
import java.util.ArrayList;

public class App extends Application {

	private HBox filaBotonesConversion;
	private TextField ingreso;
	private TextField resultado;  //resultado

	private final List<String> todasLasBases = List.of("DECIMAL", "BINARIO", "OCTAL", "HEXADECIMAL");

	public void start(Stage stage) throws Exception {

		//Texto superior
		Label etiqueta = new Label("Selecciona el tipo de ingreso");
		etiqueta.setStyle("-fx-font-size: 16px; -fx-font-weight: bold;");


		//Botones
		ToggleButton decimal = new ToggleButton("DECIMAL");
		ToggleButton binario = new ToggleButton("BINARIO");
		ToggleButton octal = new ToggleButton("OCTAL");
		ToggleButton hexadecimal = new ToggleButton("HEXADECIMAL");

		ToggleGroup grupoIngreso = new ToggleGroup();
		decimal.setToggleGroup(grupoIngreso);
		binario.setToggleGroup(grupoIngreso);
		octal.setToggleGroup(grupoIngreso);
		hexadecimal.setToggleGroup(grupoIngreso);


		//Fila de botones de tipo de ingreso
		HBox filaBotonesTipo = new HBox(10);
		filaBotonesTipo.getChildren().addAll(decimal, binario, octal, hexadecimal);
		filaBotonesTipo.setAlignment(javafx.geometry.Pos.CENTER);


		// Recuadro de ingreso
		ingreso = new TextField();
		ingreso.setPrefWidth(230);
		ingreso.setAlignment(javafx.geometry.Pos.CENTER);
		ingreso.setStyle("-fx-prompt-text-fill: rgba(0,0,0,0.4);");


		grupoIngreso.selectedToggleProperty().addListener((obs, oldVal, newVal) -> {
			if (newVal == null) return;
			String baseSeleccionada = ((ToggleButton) newVal).getText();
			actualizarPrompt(baseSeleccionada);
			ingreso.setText("");
			resultado.setText("");  // limpiar resultado anterior
			actualizarBotonesDestino(baseSeleccionada);
		});

		// Fila de botones Conversion
		filaBotonesConversion = new HBox(10);
		filaBotonesConversion.setAlignment(javafx.geometry.Pos.CENTER);

		
		// Resultado
		resultado = new TextField();
		resultado.setPrefWidth(300);
		resultado.setEditable(false);
		resultado.setAlignment(javafx.geometry.Pos.CENTER);
		resultado.setStyle("-fx-background-color: #f0f0f0; -fx-text-fill: #333;");

		//Ordenado de la app
		VBox filas = new VBox(15);
		filas.getChildren().addAll(etiqueta, filaBotonesTipo, ingreso, filaBotonesConversion, resultado);
		filas.setAlignment(javafx.geometry.Pos.CENTER);

		Scene scene = new Scene(filas, 600, 350);
		stage.setTitle("Calculadora de bases");
		stage.setScene(scene);
		stage.show();
	}

	private void actualizarPrompt(String base) {
		switch (base) {
		case "DECIMAL":
			ingreso.setPromptText("Ingrese números enteros positivos");
			break;
		case "BINARIO":
			ingreso.setPromptText("Ingrese únicamente 0 o 1");
			break;
		case "OCTAL":
			ingreso.setPromptText("Ingrese únicamente dígitos 0-7");
			break;
		case "HEXADECIMAL":
			ingreso.setPromptText("Ingrese 0-9, A-F (mayúsculas o minúsculas)");
			break;
		default:
			ingreso.setPromptText("");
		}
	}

	private void actualizarBotonesDestino(String baseSeleccionada) {
		filaBotonesConversion.getChildren().clear();

		List<String> otrasBases = new ArrayList<>(todasLasBases);
		otrasBases.remove(baseSeleccionada);

		for (String baseDestino : otrasBases) {
			Button boton = new Button("Convertir a " + baseDestino);
			boton.setPrefWidth(140);
			boton.setOnAction(e -> convertir(baseSeleccionada, baseDestino)); // Ejecuta el método convertir cuando es presionado
			filaBotonesConversion.getChildren().add(boton);
		}
	}

	private void convertir(String baseOrigen, String baseDestino){
		// Lee lo que escribe el usuario
		String texto = ingreso.getText().trim(); // .trim() elimina espacios accidentales

		if (texto.isEmpty()) {
			resultado.setText("Ingresa un número primero");
			return; 
		}

		// Se intenta hacer la conversión pero el usuario escribió un dato inválido
		try{
			int decimal = convertirADecimal(texto, baseOrigen);
			String resultadoConversion= convertirDesdeDecimal(decimal, baseDestino);
			resultado.setText(resultadoConversion); // Muestra el resultado
		}catch (NumberFormatException ex){
			resultado.setText("Entrada inválida para base " + baseOrigen);
		}
	}

	// Recibe la base origen y llama al método correspondiente para obtener un entero
	private int convertirADecimal(String texto, String base){
		switch (base) {
			case "DECIMAL": 	return Integer.parseInt(texto);
			case "BINARIO": 	return ConversionBinaria.convertirBinarioADecimal(texto);
			case "OCTAL": 		return Calculadora_Bases.convertirOctalADecimal(texto);
			case "HEXADECIMAL": return Calculadora_Hexadecimal.convertirHexadecimalADecimal(texto);
			default: throw new IllegalArgumentException("Base desconocida: " + base);
		}
	}

	// Recibe un entero y lo convierte a la base destino, devuelve un String
	private String convertirDesdeDecimal(int decimal, String base){
		switch (base) {
			case "DECIMAL": 	return String.valueOf(decimal);
			case "BINARIO": 	return ConversionBinaria.convertirDecimalABinario(decimal);
			case "OCTAL": 		return Calculadora_Bases.convertirDecimalAOctal(decimal);
			case "HEXADECIMAL": return Calculadora_Hexadecimal.convertirDecimalAHexadecimal(decimal);
			default: throw new IllegalArgumentException("Base desconocida: " + base);
		}
	}

	public static void main(String[] args) {
		launch(args);
	}
}