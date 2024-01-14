package com.example.wouteraltepostendassignment.model;

import java.io.Serializable;

public class User implements Serializable {
    public enum Role {
        Sales, Manager
    }

    private String username;
    private String password;
    private Role role;
    private String firstName;
    private String lastName;
    private String email;
    private int phone;

    public User(String firstName, String lastName, String email, int phone) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phone = phone;
    }

    public User(String username, String password, Role role, String firstName, String lastName, String email, int phone) {
        this.username = username;
        this.password = password;
        this.role = role;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phone = phone;
    }

    public String getFirstName() {
        return firstName;
    }


    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public Role getRole() {
        return role;
    }


}
