module com.example.hangedman {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.hangedman to javafx.fxml;
    opens com.example.hangedman.controller to javafx.fxml;
    exports com.example.hangedman;
}