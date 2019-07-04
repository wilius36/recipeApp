package com.example.recipeapp.Models;

import java.util.List;

public class RecepieFounderModel {

    private String id;
    private String email;
    private String password;
    private String username;
    private String userType;

    private List<String> favoriteIds;
    private List<String> creatorRecipesIds;

    public RecepieFounderModel(String id, String email, String password, String username,
                               String userType, List<String> favoriteIds, List<String> creatorRecipesIds) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.username = username;
        this.userType = userType;
        this.favoriteIds = favoriteIds;
        this.creatorRecipesIds = creatorRecipesIds;
    }

    public RecepieFounderModel() {
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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public List<String> getFavoriteIds() {
        return favoriteIds;
    }

    public void setFavoriteIds(List<String> favoriteIds) {
        this.favoriteIds = favoriteIds;
    }

    public List<String> getCreatorRecipesIds() {
        return creatorRecipesIds;
    }

    public void setCreatorRecipesIds(List<String> creatorRecipesIds) {
        this.creatorRecipesIds = creatorRecipesIds;
    }
}
