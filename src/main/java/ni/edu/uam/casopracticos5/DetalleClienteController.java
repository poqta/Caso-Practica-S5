package ni.edu.uam.casopracticos5;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import ni.edu.uam.casopracticos5.Model.Cliente;

import java.io.File;

public class DetalleClienteController {

    @FXML
    private Label lblNombres;

    @FXML
    private Label lblApellidos;

    @FXML
    private Label lblTipoCliente;

    @FXML
    private Label lblCiudad;

    @FXML
    private Label lblFechaNacimiento;

    @FXML
    private Label lblTipoSolicitud;

    @FXML
    private Label lblServiciosInteres;

    @FXML
    private ImageView imgFotografia;

    @FXML
    private Button btnRegresar;

    public void mostrarCliente(Cliente cliente) {

        lblNombres.setText(cliente.getNombres());
        lblApellidos.setText(cliente.getApellidos());
        lblTipoCliente.setText(cliente.getTipoCliente());
        lblCiudad.setText(cliente.getCiudad());

        if (cliente.getFechaNacimiento() != null) {
            lblFechaNacimiento.setText(
                    cliente.getFechaNacimiento().toString()
            );
        }

        lblTipoSolicitud.setText(cliente.getTipoSolicitud());
        lblServiciosInteres.setText(cliente.getServiciosInteres());

        if (cliente.getImagePath() != null &&
                !cliente.getImagePath().isEmpty()) {

            File archivo = new File(cliente.getImagePath());

            if (archivo.exists()) {
                imgFotografia.setImage(
                        new Image(archivo.toURI().toString())
                );
            }
        }
    }

    @FXML
    private void regresar() {
        Stage stage = (Stage) btnRegresar.getScene().getWindow();
        stage.close();
    }
}