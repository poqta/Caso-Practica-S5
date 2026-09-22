package ni.edu.uam.casopracticos5;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.ToolBar;
import javafx.stage.Stage;

import java.io.IOException;

public class MenuPrincipalController {

    @FXML
    private MenuBar mnbPrincipal;

    @FXML
    private Menu mnuInicio;

    @FXML
    private Menu mnuRegistros;

    @FXML
    private Menu mnuConsultas;

    @FXML
    private Button btnRegistrarCliente;

    @FXML
    private Button btnConsultaClientes;

    @FXML
    private Button btnSolicitudes;

    @FXML
    private Button btnConsultas;

    @FXML
    private Button btnSalir;

    @FXML
    private ToolBar tbPrincipal;



    @FXML
    private void abrirRegistroCliente() {

        try {

            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource("registro-cliente.fxml")
            );

            Parent root = loader.load();

            Stage stage =
                    (Stage) mnbPrincipal.getScene().getWindow();

            stage.setScene(new Scene(root));
            stage.setTitle("Registro de Cliente");
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @FXML
    private void abrirConsultaClientes() {

        try {

            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource("consulta-clientes.fxml")
            );

            Parent root = loader.load();

            Stage stage =
                    (Stage) mnbPrincipal.getScene().getWindow();

            stage.setScene(new Scene(root));
            stage.setTitle("Consulta de Clientes");
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    @FXML
    private void regresarInicio() {

        try {

            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource("menu-principal.fxml")
            );

            Parent root = loader.load();

            Stage stage =
                    (Stage) mnbPrincipal.getScene().getWindow();

            stage.setScene(new Scene(root));
            stage.setTitle("Menú Principal");
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    @FXML
    private void salir() {

        Stage stage =
                (Stage) mnbPrincipal.getScene().getWindow();

        stage.close();
    }
}