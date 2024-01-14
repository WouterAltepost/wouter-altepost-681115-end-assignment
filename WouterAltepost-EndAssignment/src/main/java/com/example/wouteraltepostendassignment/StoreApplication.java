package com.example.wouteraltepostendassignment;

import com.example.wouteraltepostendassignment.controller.LoginController;
import com.example.wouteraltepostendassignment.database.DataHandler;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class StoreApplication extends Application {
    private static Stage primaryStage;

    @Override
    public void start(Stage stage) throws IOException {
        primaryStage = stage; // Store the primaryStage reference
        DataHandler data = new DataHandler();
        FXMLLoader fxmlLoader = new FXMLLoader(StoreApplication.class.getResource("Login-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 310, 400);
        LoginController loginController = fxmlLoader.getController();
        loginController.setDatabase(data);
        stage.setTitle("Login");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

    public static Stage getPrimaryStage() {
        return primaryStage;
    }
}
