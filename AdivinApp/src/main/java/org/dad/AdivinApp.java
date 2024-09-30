package org.dad;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.Random;



public class AdivinApp extends Application {

  // Model

  // Vista
  private Label enunciadoLabel;
  private Button comprobarButton;
  private TextField numeroTextField;

  private Random random;
  private int numero_random;
  private int numero_intentos = 0;

  private Alert alertaInfo;
  private Alert alertaError;
  private Alert alertaFormato;


  @Override
  public void start(Stage primaryStage) throws Exception {
    // Inicializamos y configuramos los elementos

    enunciadoLabel = new Label("Introduce un número del 1 al 100");

    numeroTextField = new TextField();
    numeroTextField.setPrefColumnCount(10);

    comprobarButton = new Button("Comprobar");
    comprobarButton.setDefaultButton(true);
    comprobarButton.setOnAction(this::ComprobarButton);


    // Elegimos el numero random que hay que adivinar
    random = new Random();
    numero_random = 1;
    // = random.nextInt(100) + 1;

    //Configuracion alertas
    alertaInfo = new Alert(Alert.AlertType.INFORMATION);
    alertaInfo.setTitle("AdivinApp");
    alertaInfo.setHeaderText("¡Has ganado!");

    alertaError = new Alert(Alert.AlertType.WARNING);
    alertaError.setTitle("¡Has fallado!");

    alertaFormato = new Alert(Alert.AlertType.ERROR);
    alertaFormato.setTitle("Error");
    alertaFormato.setContentText("El número introducido no es válido.");

    // VBox para guardar los 3 elementos de la ventana
    VBox root = new VBox();
    root.setSpacing(10);
    root.setFillWidth(false);
    root.setAlignment(Pos.CENTER);
    root.getChildren().addAll(enunciadoLabel, numeroTextField, comprobarButton);

    Scene scene = new Scene(root, 440, 280);


    primaryStage.setTitle("AdivinApp");
    primaryStage.setScene(scene);
    primaryStage.show();
  }

  private void ComprobarButton(ActionEvent actionEvent) {
    numero_intentos++;
    if (numeroTextField.getText().matches("\\d+")) {
      int numero_intento = Integer.parseInt(numeroTextField.getText());
      if (numero_intento == numero_random) {
        alertaInfo.setContentText("Solo has necesitado " + numero_intentos + " intentos \n\nVuelve a jugar y hazlo mejor");
        numero_random = random.nextInt(100) + 1;
        alertaInfo.showAndWait();
      } else if(numero_intento > numero_random) {
        alertaError.setContentText("El numero a adivinar es menor que " + numero_intento + ".\n\nVuelve a intentarlo.");
        alertaError.showAndWait();
      } else if(numero_intento < numero_random) {
        alertaError.setContentText("El numero a adivinar es mayor que " + numero_intento + ".\n\nVuelve a intentarlo.");
        alertaError.showAndWait();
      }
    } else {
      alertaFormato.showAndWait();
    }
    }


}
