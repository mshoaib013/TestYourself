package com.example.darkshadow.testquiz;

public class User {
    private String password;
    private String username;

    public User(String password, String username) {
        this.username = username;
        this.password = password;
    }


    public User(){}

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
