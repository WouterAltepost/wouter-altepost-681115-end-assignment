package com.example.wouteraltepostendassignment.model;

import java.io.Serializable;

public class Product implements Serializable {
    private static final long serialVersionUID = 1L;

    private String productName;
    private double price;

    private double totalPrice;

    private String category;
    private int quantity;

    private String description;
    private int stock;


    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }


    public Product(int quantity, String productName, String category, double totalPrice) {
        this.quantity = quantity;
        this.productName = productName;
        this.category = category;
        this.price = totalPrice;
    }

    public Product(int stock, String productName, String category, double price, String description) {
        this.stock = stock;
        this.productName = productName;
        this.category = category;
        this.price = price;
        this.description = description;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }


    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setNewStock(int quantity) {
        if (stock < quantity) {
            throw new IllegalArgumentException("Not enough stock");
        } else {
            stock -= quantity;
        }
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int quantity) {
        stock = quantity;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getTotalPrice() {
        return price * quantity;
    }


}
