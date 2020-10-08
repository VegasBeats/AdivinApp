package adivinApp;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class AdivinApp extends Application {
	
	private VBox root;
	private HBox hboxLabel;
	private int intentos=1;
	private int numero_correcto=(int) Math.round(1+(Math.random()*100));
	private TextField numeroAintroducir;
	private Button comprobarButton;
	private Label introduceNumeroLabel;
	private Alert tipoDeAlerta;
	private int numero_introducido;
	public void start(Stage primaryStage) throws Exception {

		
		
		
		introduceNumeroLabel = new Label();
		introduceNumeroLabel.setText("introduzca un número del 1 al 100");
		introduceNumeroLabel.setLayoutX(20);
		introduceNumeroLabel.setLayoutY(20);
		introduceNumeroLabel.setAlignment(Pos.BASELINE_CENTER);

		numeroAintroducir = new TextField();
		numeroAintroducir.setPromptText("introduzca aquí el número");
		numeroAintroducir.setAlignment(Pos.BASELINE_CENTER);
		numeroAintroducir.setPrefWidth(80);
		numeroAintroducir.setMaxWidth(240);
		

		comprobarButton = new Button();
		comprobarButton.setText("Comprobar");
		comprobarButton.setOnAction(e -> condicionante(numeroAintroducir, numero_correcto));
		comprobarButton.setAlignment(Pos.BASELINE_CENTER);

		
		HBox hboxLabel=new HBox(introduceNumeroLabel);
		hboxLabel.setAlignment(Pos.BASELINE_CENTER);
		
		HBox hboxTextField = new HBox(numeroAintroducir);
		hboxTextField.setAlignment(Pos.BASELINE_CENTER);
		
		HBox hboxButton = new HBox(comprobarButton);
		hboxButton.setAlignment(Pos.BASELINE_CENTER);
		
		VBox root = new VBox(hboxLabel, numeroAintroducir, comprobarButton);
		root.setSpacing(5);
		root.setAlignment(Pos.CENTER);

		Scene scene = new Scene(root, 300, 200);

		primaryStage.setTitle("AdivinApp");
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	public void condicionante(TextField numero_introducido, int numero_correcto) {
		try {
			PruebaAerrores();
			int numero = Integer.parseInt(numero_introducido.getText());
			Alert alerta_aux;
			if (numero==numero_correcto) {
				alerta_aux=new Alert(AlertType.INFORMATION);
				alerta_aux.setHeaderText("¡Has Ganado!");
				alerta_aux.setContentText("Solo has necesitado "+this.intentos+" intentos");
				alerta_aux.showAndWait();
			}
			else if (numero>numero_correcto) {
				alerta_aux=new Alert(AlertType.WARNING);
				alerta_aux.setHeaderText("!Has Fallado!");
				alerta_aux.setContentText("El número ha adivinar es menor que "+numero+"/n"+
				"/n"+
				"Vuelve a intentarlo");
				alerta_aux.showAndWait();
				this.intentos++;
			}
			else if (numero<numero_correcto){
				alerta_aux=new Alert(AlertType.WARNING);
				alerta_aux.setHeaderText("!Has Fallado!");
				alerta_aux.setContentText("El número ha adivinar es mayor que "+numero+"/n"+
				"/n"+
				"Vuelve a intentarlo");
				alerta_aux.showAndWait();
				this.intentos++;
			}
		}
		catch (Exception e) {
			
		}
	}

	public void PruebaAerrores () {
		try {
			if (numeroAintroducir.getText()!="") {
				Integer.parseInt(numeroAintroducir.getText());
			}	
		}
		
		catch (Exception e) {
			tipoDeAlerta=new Alert(AlertType.ERROR);
			tipoDeAlerta.setHeaderText("Error");
			tipoDeAlerta.setContentText("El número introducido no es válido");
			tipoDeAlerta.showAndWait();
		}
	}
	public static void main(String args[]) {
		launch(args);
	}
}
