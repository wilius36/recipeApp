package com.example.recipeapp.Models;

import java.util.List;

public class UserModel {

    private String id;
    private String email;
    private String password;
    private String userType;
    private String username;

    private List<String> favoriteIds;

    public UserModel(String id, String email, String password, String userType, String username, List<String> favoriteIds) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.userType = userType;
        this.username = username;
        this.favoriteIds = favoriteIds;
    }

    public UserModel() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public List<String> getFavoriteIds() {
        return favoriteIds;
    }

    public void setFavoriteIds(List<String> favoriteIds) {
        this.favoriteIds = favoriteIds;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
