package ni.edu.uam.casopracticos5;

import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class ConsultasClientesController {

    @FXML
    private TableView<?> tblClientes;

    @FXML
    private TableColumn<?, ?> colNombreCompleto;

    @FXML
    private TableColumn<?, ?> colTipoCliente;

    @FXML
    private TableColumn<?, ?> colCiudad;

    @FXML
    private TableColumn<?, ?> colFechaNacimiento;

    @FXML
    private TableColumn<?, ?> colTipoSolicitud;

}