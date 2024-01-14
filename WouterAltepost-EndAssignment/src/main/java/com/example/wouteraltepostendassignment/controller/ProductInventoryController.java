package com.example.wouteraltepostendassignment.controller;

import com.example.wouteraltepostendassignment.database.DataHandler;
import com.example.wouteraltepostendassignment.interfaces.ProductSelectedListener;
import com.example.wouteraltepostendassignment.model.Product;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class ProductInventoryController {
    @FXML
    private TextField stock;
    @FXML
    private TextField productName;
    @FXML
    private TextField category;
    @FXML
    private TextField price;
    @FXML
    private TextField description;
    @FXML
    private Label errorMessage;

    @FXML
    private Button deleteProductBtn;
    @FXML
    private Button editProductBtn;
    @FXML
    private Button addProductBtn;
    @FXML
    private TableView<Product> productTable;

    DataHandler database;
    ProductSelectedListener listener;

    private ObservableList<Product> products;

    public void setDatabase(DataHandler database) {
        this.database = database;
        loadProducts();
    }

    public void loadProducts() {
        products = FXCollections.observableArrayList(database.getProducts());
        productTable.setItems(products);
    }

    public void handleMouseAction(Event event) {
        Product selectedProduct = productTable.getSelectionModel().getSelectedItem();
        if (selectedProduct != null) {
            stock.setText(String.valueOf(selectedProduct.getStock()));
            productName.setText(selectedProduct.getProductName());
            description.setText(selectedProduct.getDescription());
            category.setText(selectedProduct.getCategory());
            price.setText(String.valueOf(selectedProduct.getPrice()));
        }
    }

    public void deleteProductBtn(Event event) {
        Product deleteProduct = productTable.getSelectionModel().getSelectedItem();
        if (deleteProduct != null) {
            products.removeAll(deleteProduct);
            database.deleteProduct(deleteProduct);
        }
    }

    public void addProductBtn(Event event) {
        try {
            int newStock = Integer.parseInt(stock.getText());
            double newPrice = Double.parseDouble(price.getText());
            Product newProduct = new Product(newStock, productName.getText(), category.getText(), newPrice, description.getText());
            database.addProduct(newProduct);
            products.add(newProduct);
            loadProducts();
            clearFields(event);
        } catch (NumberFormatException e) {
            errorMessage.setText("Invalid Input. Please enter valid values for stock and price.");
        }
    }

    public void editProductBtn(Event event) {
        Product selectedProduct = productTable.getSelectionModel().getSelectedItem();

        if (selectedProduct != null) {
            try {
                int newStock = Integer.parseInt(stock.getText());
                double newPrice = Double.parseDouble(price.getText());

                selectedProduct.setStock(newStock);
                selectedProduct.setProductName(productName.getText());
                selectedProduct.setCategory(category.getText());
                selectedProduct.setPrice(newPrice);
                selectedProduct.setDescription(description.getText());

                database.updateProduct(selectedProduct);
                int selectedIndex = products.indexOf(selectedProduct);
                products.set(selectedIndex, selectedProduct);
                clearFields(event);
            } catch (NumberFormatException e) {
            }
        }
    }

    public void clearFields(Event event) {
        stock.setText("");
        productName.setText("");
        category.setText("");
        price.setText("");
        description.setText("");
    }
}
