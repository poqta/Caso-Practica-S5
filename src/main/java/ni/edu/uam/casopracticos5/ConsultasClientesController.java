package ni.edu.uam.casopracticos5;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import ni.edu.uam.casopracticos5.Model.Cliente;
import ni.edu.uam.casopracticos5.Model.RepositorioDatos;

import java.io.IOException;
import java.time.LocalDate;

public class ConsultasClientesController {

    @FXML
    private TableView<Cliente> tblClientes;

    @FXML
    private TableColumn<Cliente, String> colNombreCompleto;

    @FXML
    private TableColumn<Cliente, String> colTipoCliente;

    @FXML
    private TableColumn<Cliente, String> colCiudad;

    @FXML
    private TableColumn<Cliente, LocalDate> colFechaNacimiento;

    @FXML
    private TableColumn<Cliente, String> colTipoSolicitud;

    @FXML
    private Button btnRegresar;

    @FXML
    private void initialize() {

        colNombreCompleto.setCellValueFactory(
                new PropertyValueFactory<>("nombres")
        );

        colTipoCliente.setCellValueFactory(
                new PropertyValueFactory<>("tipoCliente")
        );

        colCiudad.setCellValueFactory(
                new PropertyValueFactory<>("ciudad")
        );

        colFechaNacimiento.setCellValueFactory(
                new PropertyValueFactory<>("fechaNacimiento")
        );

        colTipoSolicitud.setCellValueFactory(
                new PropertyValueFactory<>("tipoSolicitud")
        );

        tblClientes.setItems(RepositorioDatos.clientes);
    }

    @FXML
    private void regresar() {

        try {

            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource("menu-principal.fxml")
            );

            Parent root = loader.load();

            Stage stage = (Stage) btnRegresar.getScene().getWindow();

            stage.setScene(new Scene(root));
            stage.setTitle("Menú Principal");
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void seleccionarCliente(MouseEvent event) {

        if (event.getClickCount() == 2) {

            Cliente clienteSeleccionado =
                    tblClientes.getSelectionModel().getSelectedItem();

            if (clienteSeleccionado != null) {

                try {

                    FXMLLoader loader = new FXMLLoader(
                            getClass().getResource("detalle-cliente.fxml")
                    );

                    Parent root = loader.load();

                    DetalleClienteController controller =
                            loader.getController();

                    controller.mostrarCliente(clienteSeleccionado);

                    Stage stage = new Stage();

                    stage.setTitle("Detalle del Cliente");
                    stage.setScene(new Scene(root));
                    stage.show();

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
}