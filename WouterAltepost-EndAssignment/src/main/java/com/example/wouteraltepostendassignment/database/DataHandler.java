package com.example.wouteraltepostendassignment.database;

import com.example.wouteraltepostendassignment.model.Order;
import com.example.wouteraltepostendassignment.model.Product;
import com.example.wouteraltepostendassignment.model.User;

import java.io.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class DataHandler {
    private User user;
    private Product product;

    private ApplicationData appData;
    private final List<User> Users;
    private List<Product> products = new ArrayList<>();
    private List<Order> orders = new ArrayList<>();

    public DataHandler() {
        this.Users = new ArrayList<>();
        initializeUsers();
        initializeData();
        appData = new ApplicationData(Users, products, orders);
    }

    private void initializeData() {
        File file = new File("appdata.txt");
        if (file.exists()) {
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
                appData = (ApplicationData) ois.readObject();

                Users.clear();
                products.clear();
                orders.clear();

                Users.addAll(appData.getUsers());
                products.addAll(appData.getProducts());
                orders.addAll(appData.getOrders());
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        } else {
            User user = new User("John", "Enter123!", User.Role.Sales, "John", "Doe", "johndoe@gmail.com", +316234567);
            Users.add(new User("Jeff", "Manager123!", User.Role.Manager, "Jeff", "Troy", "jefftroy@gmail.com", +316123678));

            List<Product> products = new ArrayList<>();
            products.add(new Product(10, "Epiphone Les Paul", "String", 585.00, "Epiphone Electric Guitar"));
            products.add(new Product(10, "Gibson Supreme 1992", "String", 770.00, "Gibson Electric Guitar"));

            orders.add(new Order(LocalDateTime.now(), user, products));
        }
    }

    public void initializeUsers() {
        Users.add(new User("John", "Enter123!", User.Role.Sales, "John", "Doe", "johndoe@gmail.com", +316234567));
        Users.add(new User("Jeff", "Manager123!", User.Role.Manager, "Jeff", "Troy", "jefftroy@gmail.com@", +316123678));
    }

    public User validateUser(String username, String password) {
        return Users.stream()
                .filter(user -> user.getUsername().equals(username) && user.getPassword().equals(password))
                .findFirst()
                .orElse(null);
    }

    public List<Product> getProducts() {
        return products;
    }

    public void addProduct(Product product) {
        products.add(product);
        saveDataToFile();
    }

    public void addOrder(Order order) {
        orders.add(order);
        saveDataToFile();
    }

    public List<Order> getOrders() {
        return new ArrayList<>(orders);
    }

    public void deleteProduct(Product selectedProduct) {
        products.remove(selectedProduct);
        saveDataToFile();
    }

    public void updateProduct(Product updatedProduct) {
        int productIndex = -1;

        for (int i = 0; i < products.size(); i++) {
            if (products.get(i).getProductName().equals(updatedProduct.getProductName())) {
                productIndex = i;
                break;
            }
        }

        if (productIndex != -1) {
            products.set(productIndex, updatedProduct);
            saveDataToFile();
        }
    }

    public void saveDataToFile() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("appdata.txt"))) {
            oos.writeObject(appData);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
