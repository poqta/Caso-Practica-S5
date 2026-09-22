package ni.edu.uam.casopracticos5;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import ni.edu.uam.casopracticos5.Model.Cliente;
import ni.edu.uam.casopracticos5.Model.RepositorioDatos;

import java.io.File;
import java.io.IOException;

public class RegistroClienteController {

    @FXML
    private TextField txtNombres;

    @FXML
    private TextField txtApellidos;

    @FXML
    private ComboBox<String> cmbTipoCliente;

    @FXML
    private ComboBox<String> cmbCiudad;

    @FXML
    private DatePicker dtpFechaNacimiento;

    @FXML
    private RadioButton rbSolicitudNueva;

    @FXML
    private RadioButton rbSolicitudRenovacion;

    private ToggleGroup tgTipoSolicitud;

    @FXML
    private CheckBox chkInternet;

    @FXML
    private CheckBox chkSoporte;

    @FXML
    private CheckBox chkMantenimiento;

    @FXML
    private Button btnImagen;

    @FXML
    private ImageView imgFotografia;

    @FXML
    private Button btnGuardar;

    @FXML
    private Button btnLimpiar;

    @FXML
    private Button btnRegresar;

    private String imagePath;


    @FXML
    private void initialize() {

        cmbTipoCliente.setItems(
                FXCollections.observableArrayList(
                        "Individual",
                        "Empresa",
                        "Institucional"
                )
        );

        cmbCiudad.setItems(
                FXCollections.observableArrayList(
                        "Managua",
                        "León",
                        "Masaya"
                )
        );

        tgTipoSolicitud = new ToggleGroup();

        rbSolicitudNueva.setToggleGroup(tgTipoSolicitud);
        rbSolicitudRenovacion.setToggleGroup(tgTipoSolicitud);
    }


    @FXML
    private void seleccionarImagen() {

        FileChooser fc = new FileChooser();

        fc.setTitle("Seleccionar fotografía");

        fc.getExtensionFilters().add(
                new FileChooser.ExtensionFilter(
                        "Imágenes",
                        "*.jpg",
                        "*.jpeg",
                        "*.png"
                )
        );

        File imagenSeleccionada =
                fc.showOpenDialog(btnImagen.getScene().getWindow());

        if (imagenSeleccionada != null) {

            imagePath = imagenSeleccionada.getAbsolutePath();

            Image imagen =
                    new Image(imagenSeleccionada.toURI().toString());

            imgFotografia.setImage(imagen);
        }
    }


    @FXML
    private void guardar() {

        if (!validarFormulario()) {
            return;
        }

        Cliente nuevoCliente = construirCliente();

        RepositorioDatos.clientes.add(nuevoCliente);

        System.out.println("Clientes registrados: " + RepositorioDatos.clientes.size());
        System.out.println("Cantidad en repositorio: "
                + RepositorioDatos.clientes.size());

        System.out.println("Nombre: "
                + nuevoCliente.getNombres());

        mostrarAlerta(
                Alert.AlertType.INFORMATION,
                "Registro exitoso",
                "Cliente guardado correctamente."
        );

        limpiarFormulario();
    }


    private Cliente construirCliente() {

        String tipoSolicitud = "";

        if (rbSolicitudNueva.isSelected()) {
            tipoSolicitud = "Nueva";
        } else if (rbSolicitudRenovacion.isSelected()) {
            tipoSolicitud = "Renovación";
        }

        String serviciosInteres = obtenerServiciosInteres();

        return new Cliente(
                txtNombres.getText().trim(),
                txtApellidos.getText().trim(),
                cmbTipoCliente.getValue(),
                cmbCiudad.getValue(),
                dtpFechaNacimiento.getValue(),
                tipoSolicitud,
                serviciosInteres,
                imagePath
        );
    }


    private String obtenerServiciosInteres() {

        StringBuilder servicios = new StringBuilder();

        if (chkInternet.isSelected()) {
            servicios.append("Internet, ");
        }

        if (chkSoporte.isSelected()) {
            servicios.append("Soporte, ");
        }

        if (chkMantenimiento.isSelected()) {
            servicios.append("Mantenimiento, ");
        }

        if (servicios.length() > 0) {
            servicios.setLength(servicios.length() - 2);
        }

        return servicios.toString();
    }


    private boolean validarFormulario() {

        if (txtNombres.getText().trim().isEmpty()
                || txtApellidos.getText().trim().isEmpty()) {

            mostrarAlerta(
                    Alert.AlertType.WARNING,
                    "Campos incompletos",
                    "Debe ingresar nombres y apellidos."
            );

            return false;
        }

        if (cmbTipoCliente.getValue() == null) {

            mostrarAlerta(
                    Alert.AlertType.WARNING,
                    "Tipo de cliente",
                    "Debe seleccionar el tipo de cliente."
            );

            return false;
        }

        if (cmbCiudad.getValue() == null) {

            mostrarAlerta(
                    Alert.AlertType.WARNING,
                    "Ciudad",
                    "Debe seleccionar una ciudad."
            );

            return false;
        }

        if (dtpFechaNacimiento.getValue() == null) {

            mostrarAlerta(
                    Alert.AlertType.WARNING,
                    "Fecha de nacimiento",
                    "Debe seleccionar la fecha de nacimiento."
            );

            return false;
        }

        if (tgTipoSolicitud.getSelectedToggle() == null) {

            mostrarAlerta(
                    Alert.AlertType.WARNING,
                    "Tipo de solicitud",
                    "Debe seleccionar el tipo de solicitud."
            );

            return false;
        }

        if (!chkInternet.isSelected()
                && !chkSoporte.isSelected()
                && !chkMantenimiento.isSelected()) {

            mostrarAlerta(
                    Alert.AlertType.WARNING,
                    "Servicios de interés",
                    "Debe seleccionar al menos un servicio."
            );

            return false;
        }

        return true;
    }


    @FXML
    private void limpiarFormulario() {

        txtNombres.clear();
        txtApellidos.clear();

        cmbTipoCliente.getSelectionModel().clearSelection();
        cmbCiudad.getSelectionModel().clearSelection();

        dtpFechaNacimiento.setValue(null);

        tgTipoSolicitud.selectToggle(null);

        chkInternet.setSelected(false);
        chkSoporte.setSelected(false);
        chkMantenimiento.setSelected(false);

        imgFotografia.setImage(null);

        imagePath = null;
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

    private void mostrarAlerta(
            Alert.AlertType tipo,
            String titulo,
            String mensaje) {

        Alert alert = new Alert(tipo);

        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);

        alert.showAndWait();
    }
}