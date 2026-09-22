module ni.edu.uam.casopracticos5 {

    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;

    opens ni.edu.uam.casopracticos5 to javafx.fxml;
    opens ni.edu.uam.casopracticos5.Model to javafx.base;

    exports ni.edu.uam.casopracticos5;
    exports ni.edu.uam.casopracticos5.Model;
}