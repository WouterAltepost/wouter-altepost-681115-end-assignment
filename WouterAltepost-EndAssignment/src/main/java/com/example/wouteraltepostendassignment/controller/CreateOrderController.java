package com.example.wouteraltepostendassignment.controller;


import com.example.wouteraltepostendassignment.database.DataHandler;
import com.example.wouteraltepostendassignment.interfaces.ProductSelectedListener;
import com.example.wouteraltepostendassignment.model.Order;
import com.example.wouteraltepostendassignment.model.Product;
import com.example.wouteraltepostendassignment.model.User;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class CreateOrderController {

    public Button addProductBtn;
    public Button deleteProductBtn;
    public Button createProductBtn;
    @FXML
    private Label successfulLabel;
    @FXML
    private Label errorLabel;
    @FXML
    private TableColumn<Product, Double> totalPriceColumn;
    @FXML
    private TableColumn<Product, Integer> quantityColumn;
    @FXML
    private TextField firstName;
    @FXML
    private TextField lastName;
    @FXML
    private TextField email;
    @FXML
    private TextField phone;
    @FXML
    private TableView productTable;

    Product product;

    DataHandler database;
    ProductSelectedListener listener;
    public List<Product> products = new ArrayList<>();
    public List<Order> orders = new ArrayList<>();

    public void initialize() {
        productTable.setItems(FXCollections.observableList(products));

        quantityColumn.setCellValueFactory(
                s -> new SimpleIntegerProperty(s.getValue().getQuantity()).asObject());
        totalPriceColumn.setCellValueFactory(
                s -> new SimpleDoubleProperty(s.getValue().getTotalPrice()).asObject());
    }

    public void setDatabase(DataHandler database) {
        this.database = database;
    }

    public void setListener(ProductSelectedListener listener) {
        this.listener = listener;
    }

    public void deleteProductBtn(ActionEvent actionEvent) {
        Product product = (Product) productTable.getSelectionModel().getSelectedItem();
        products.remove(product);
        updateTableView();
    }

    public void createProductBtn(ActionEvent actionEvent) {
        String userEmail = email.getText();
        String userPhone = phone.getText();
        if (!isValidEmail(userEmail)) {
            setError("Email must contain '@' and end with '.com'.");
            return;
        }
        if (!isValidPhoneNumber(userPhone)) {
            setError("Please enter a valid phone number with 9 digits.");
            return;
        }


        errorLabel.setText("");
        User user = new User(
                firstName.getText(),
                lastName.getText(),
                userEmail,
                Integer.parseInt(userPhone)
        );
        Order order = new Order(LocalDateTime.now(), user, products);
        database.addOrder(order);
        successfulLabel.setText("Order created successfully!");
    }

    public void addProductBtn(ActionEvent actionEvent) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/wouteraltepostendassignment/AddOrder-view.fxml"));
        try {
            Parent root = loader.load();
            AddOrderController controller = loader.getController();
            controller.setDatabase(database);
            controller.setListener(product -> {
                products.add(product);
                updateTableView();
            });
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("App product to the order");
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void updateTableView() {
        ObservableList<Product> observableProducts = FXCollections.observableArrayList(products);
        productTable.setItems(observableProducts);
    }

    private void setError(String errorMessage) {
        errorLabel.setText(errorMessage);
    }

    private boolean isValidEmail(String email) {
        return email.matches(".*@.*\\.com$");
    }

    private boolean isValidPhoneNumber(String phoneNumber) {
        return phoneNumber.length() == 9 && phoneNumber.chars().allMatch(Character::isDigit);
    }
}

