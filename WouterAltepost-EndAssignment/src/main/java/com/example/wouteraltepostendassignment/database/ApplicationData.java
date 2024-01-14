package com.example.wouteraltepostendassignment.database;

import com.example.wouteraltepostendassignment.model.Order;
import com.example.wouteraltepostendassignment.model.Product;
import com.example.wouteraltepostendassignment.model.User;

import java.io.Serializable;
import java.util.List;

public class ApplicationData implements Serializable {

    private List<User> users;
    private List<Product> products;
    private List<Order> orders;

    public ApplicationData(List<User> users, List<Product> products, List<Order> orders) {
        this.users = users;
        this.products = products;
        this.orders = orders;
    }

    public List<User> getUsers() {
        return users;
    }

    public List<Product> getProducts() {
        return products;
    }

    public List<Order> getOrders() {
        return orders;
    }
}

