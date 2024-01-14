package com.example.wouteraltepostendassignment.controller;

import com.example.wouteraltepostendassignment.database.DataHandler;
import com.example.wouteraltepostendassignment.model.Order;
import com.example.wouteraltepostendassignment.model.Product;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class OrderHistoryController {

    @FXML
    private TableColumn<Order, String> usernameColumn;
    @FXML
    private TableColumn<Order, Double> totalPriceColumn;
    @FXML
    private TableView<Order> orderHistoryTable;

    @FXML
    private TableView<Product> productHistoryTable;

    DataHandler database;

    public void setDatabase(DataHandler database) {
        this.database = database;
        loadOrderHistory();
        setupOrderTableClickListener();
    }

    private void loadOrderHistory() {
        orderHistoryTable.setItems(FXCollections.observableArrayList(database.getOrders()));
        orderHistoryTable.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);

        usernameColumn.setCellValueFactory(
                s -> new SimpleStringProperty(s.getValue().getUserName())
        );
        totalPriceColumn.setCellValueFactory(cellData -> new SimpleDoubleProperty(cellData.getValue().getProducts().stream()
                .mapToDouble(Product::getTotalPrice)
                .sum()).asObject());

    }

    private void setupOrderTableClickListener() {
        orderHistoryTable.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                showProductInformation(newValue);
            }
        });
    }

    private void showProductInformation(Order order) {
        productHistoryTable.setItems(FXCollections.observableArrayList(order.getProducts()));
    }
}
