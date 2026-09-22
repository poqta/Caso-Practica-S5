package ni.edu.uam.casopracticos5.Controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;

public class RegistroClienteController {
    @FXML
    private TextField txtNombres;
    @FXML
    private TextField txtApellidos;
    @FXML
    private ComboBox cmbTipoCliente;
    @FXML
    private ComboBox cmbCiudad;
    @FXML
    private DatePicker dtpFechaNacimiento;
    @FXML
    private RadioButton rbSolicitudNueva;
    @FXML
    private RadioButton rbSolicitudRenovacion;

    private ToggleGroup tgTipoSolicitud;
    @FXML
    private Button btnImagen;
    @FXML
    private ImageView imgFotografia;
    @FXML
    private Button btnGuardar;
    @FXML
    private Button btnLimpiar;
    @FXML
    private Button btbnCancelar;

    private ObservableList<Cliente> listaClientes = FXCollections.observableArrayList();

    @FXML
    private void Initialize() {
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
    private void seleccionarImagen(){
        FileChooser fc = new FileChooser();
        fc.setTitle("Seleccionar Imagen");
        fc.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Imágenes JPG", "*.jpg")
        );
        File imagenSeleccionada =fc.showOpenDialog(btnImagen.getScene().getWindow());

        if (imagenSeleccionada != null) {
            Image imagen = new Image(imagenSeleccionada.toURI().toString());
            imgFotografia.setImage(imagen);
        }
    }

    @FXML
    private void guardar() {
        if (!validarFormulario()) {
            return;
        }

        Cliente nuevoCliente = construirCliente();
        listaClientes.add(nuevoCliente);

        registroClientes.add(txtNombres.getText());

        mostrarAlerta(Alert.AlertType.INFORMATION, "Éxito", "La información ha sido guardada correctamente.");
        limpiarFormulario();
    }

    @FXML
    private void cancelar() {
        Stage stage = (Stage) txtNombres.getScene().getWindow();
        stage.close();
    }

    private Cliente construirCliente(){
        return new Cliente(
                txtNombres.getText().trim(),
                txtApellidos.getText().trim(),
                cmbTipoCliente.getValue(),
                cmbCiudad.getValue(),
                dtp

        );
    }

    private boolean validarFormulario(){
        if (txtNombres.getText().trim().isEmpty() || txtApellidos.getText().trim().isEmpty()) {
            mostrarAlerta(
                    Alert.AlertType.WARNING,
                    "Campos incompletos",
                    "Debe completar todos los campos"
            );
            return false;
        }

        if (cmbTipoCliente.getValue() == null) {
            mostrarAlerta(
                    Alert.AlertType.WARNING,
                    "Ningún tipo seleccionado",
                    "Debe seleccionar una opción"
            );
            return false;
        }
        return true;
    }

    private void mostrarAlerta(Alert.AlertType type, String Titulo, String mensaje){
        Alert alert = new Alert(type);
        alert.setTitle(Titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }

    @FXML
    private void limpiarFormulario(){
        txtNombres.clear();
        txtApellidos.clear();
        cmbCiudad.getSelectionModel().clearSelection();
        cmbTipoCliente.getSelectionModel().clearSelection();
    }
}
