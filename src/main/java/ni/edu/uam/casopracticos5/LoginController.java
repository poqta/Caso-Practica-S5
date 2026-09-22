package ni.edu.uam.casopracticos5;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginController {

    @FXML
    private TextField txtUsuario;

    @FXML
    private PasswordField pwdContrasena;

    @FXML
    private Button btnIniciarSesion;

    @FXML
    private Button btnSalir;


    @FXML
    private void iniciarSesion() {

        if (txtUsuario.getText().isEmpty() ||
                pwdContrasena.getText().isEmpty()) {

            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Advertencia");
            alert.setHeaderText(null);
            alert.setContentText("Debe completar todos los campos.");

            alert.showAndWait();

            return;
        }

        try {

            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource("menu-principal.fxml")
            );

            Parent root = loader.load();

            Stage stage = (Stage) btnIniciarSesion.getScene().getWindow();

            Scene scene = new Scene(root);

            stage.setScene(scene);
            stage.setTitle("Menú principal");
            stage.show();

        } catch (IOException e) {

            e.printStackTrace();
        }
    }


    @FXML
    private void salir() {

        Alert alert = new Alert(
                Alert.AlertType.CONFIRMATION,
                "¿Está seguro de que desea salir?",
                ButtonType.YES,
                ButtonType.NO
        );

        alert.setTitle("Confirmar salida");
        alert.setHeaderText(null);

        alert.showAndWait().ifPresent(respuesta -> {

            if (respuesta == ButtonType.YES) {
                Stage stage =
                        (Stage) btnSalir.getScene().getWindow();

                stage.close();
            }

        });
    }
}