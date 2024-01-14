package com.example.wouteraltepostendassignment.database;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

public class DeserializeAppData {
    public static void main(String[] args) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("appdata.txt"))) {
            ApplicationData appData = (ApplicationData) ois.readObject();

            // Print or inspect the content of appData
            System.out.println(appData.getUsers());
            System.out.println(appData.getProducts());
            System.out.println(appData.getOrders());
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}

