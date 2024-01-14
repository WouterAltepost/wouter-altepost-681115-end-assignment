module com.example.wouteraltepostendassignment {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.wouteraltepostendassignment to javafx.fxml;
    exports com.example.wouteraltepostendassignment;
    exports com.example.wouteraltepostendassignment.model;
    opens com.example.wouteraltepostendassignment.model to javafx.fxml;
    exports com.example.wouteraltepostendassignment.controller;
    opens com.example.wouteraltepostendassignment.controller to javafx.fxml;
    exports com.example.wouteraltepostendassignment.interfaces;
    opens com.example.wouteraltepostendassignment.interfaces to javafx.fxml;
}
