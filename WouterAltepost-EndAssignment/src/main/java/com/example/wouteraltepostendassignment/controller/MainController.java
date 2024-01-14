package com.example.wouteraltepostendassignment.controller;

import com.example.wouteraltepostendassignment.database.DataHandler;
import com.example.wouteraltepostendassignment.model.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.layout.BorderPane; // Add this line
import javafx.stage.Stage;
import javafx.scene.Scene;

import java.io.IOException;

public class MainController {
    @FXML
    public Button createOrderButton;
    @FXML
    public Button productInventoryButton;
    @FXML
    public Button orderHistoryButton;

    public Button logOutButton;

    public VBox DisplayNew;
    @FXML
    private Button dashboardButton;

    private DataHandler database;
    private User users;

    public void goToDashboard(ActionEvent actionEvent) {
        loaderView("/com/example/wouteraltepostendassignment/Dashboard-view.fxml");
    }

    public void goToCreateOrder(ActionEvent actionEvent) {
        loaderView("/com/example/wouteraltepostendassignment/CreateOrder-view.fxml");
    }

    public void goToProductInventory(ActionEvent actionEvent) {
        loaderView("/com/example/wouteraltepostendassignment/ProductInventory-view.fxml");

    }

    public void goToOrderHistory(ActionEvent actionEvent) {
        loaderView("/com/example/wouteraltepostendassignment/OrderHistory-view.fxml");

    }

    public void goToLogOut(ActionEvent actionEvent) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/example/wouteraltepostendassignment/Login-view.fxml"));
            BorderPane loginViewPane = fxmlLoader.load();

            Stage stage = new Stage();
            stage.setScene(new Scene(loginViewPane));

            Stage currentStage = (Stage) logOutButton.getScene().getWindow();
            currentStage.close();

            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setUser(User user) {
        this.users = user;
        allowBasedOnRole();

    }

    public void setDatabase(DataHandler database) {
        this.database = database;
    }

    private void allowBasedOnRole() {

        if (User.Role.Sales.equals(users.getRole())) {
            dashboardButton.setDisable(false);
            createOrderButton.setDisable(false);
            productInventoryButton.setDisable(true);
            orderHistoryButton.setDisable(false);
        } else if (User.Role.Manager.equals(users.getRole())) {
            dashboardButton.setDisable(false);
            createOrderButton.setDisable(true);
            productInventoryButton.setDisable(false);
            orderHistoryButton.setDisable(false);
        }
    }

    public void loaderView(String Path) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(Path));
            VBox navigationView = fxmlLoader.load();
            Object controller = fxmlLoader.getController();
            if (controller instanceof DashboardController) {
                DashboardController dashboardButtonController = (DashboardController) controller;
                dashboardButtonController.setUser(users);
                dashboardButtonController.setDatabase(database);
            } else if (controller instanceof CreateOrderController) {
                CreateOrderController createOrderBtn = (CreateOrderController) controller;
                createOrderBtn.setDatabase(database);
            } else if (controller instanceof ProductInventoryController) {
                ProductInventoryController productInventoryButtonController = (ProductInventoryController) controller;
                productInventoryButtonController.setDatabase(database);

            } else {
                OrderHistoryController orderHistoryButtonController = (OrderHistoryController) controller;
                orderHistoryButtonController.setDatabase(database);
            }
            DisplayNew.getChildren().setAll(navigationView);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
