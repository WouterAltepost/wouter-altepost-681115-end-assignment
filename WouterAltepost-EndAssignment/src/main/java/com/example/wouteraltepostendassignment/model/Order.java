package com.example.wouteraltepostendassignment.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class Order implements Serializable {
    private LocalDateTime timestamp;
    private User user;
    private List<Product> products;


    public Order(LocalDateTime timestamp, User user, List<Product> products) {
        this.timestamp = timestamp;
        this.user = user;
        this.products = products;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public String getFormattedTimestamp() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        return timestamp.format(formatter);
    }

    public void setUser(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    public String getUserName() {
        return user.getFirstName();
    }

    public List<Product> getProducts() {
        return products;
    }

}
