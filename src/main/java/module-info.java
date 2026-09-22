module ni.edu.uam.casopracticos5 {
    requires javafx.controls;
    requires javafx.fxml;


    opens ni.edu.uam.casopracticos5 to javafx.fxml;
    exports ni.edu.uam.casopracticos5;
}