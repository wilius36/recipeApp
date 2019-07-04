package com.example.recipeapp.Models;

import java.util.List;

public class RecepieModel {

    private String id;
    private String mealType;
    private String title;
    private String description;
    private double sumOfRating;
    private int totalPeopleRatedCount;
    private String factureDuration;
    private String factureDificulty;
    private String postDate;

    private List<IngredientsModel> ingredients;

    public RecepieModel(String id, String mealType, String title, String description,
                        double sumOfRating, int totalPeopleRatedCount, String factureDuration,
                        String factureDificulty, String postDate, List<IngredientsModel> ingredients) {
        this.id = id;
        this.mealType = mealType;
        this.title = title;
        this.description = description;
        this.sumOfRating = sumOfRating;
        this.totalPeopleRatedCount = totalPeopleRatedCount;
        this.factureDuration = factureDuration;
        this.factureDificulty = factureDificulty;
        this.postDate = postDate;
        this.ingredients = ingredients;
    }

    public RecepieModel() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMealType() {
        return mealType;
    }

    public void setMealType(String mealType) {
        this.mealType = mealType;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getSumOfRating() {
        return sumOfRating;
    }

    public void setSumOfRating(double sumOfRating) {
        this.sumOfRating = sumOfRating;
    }

    public int getTotalPeopleRatedCount() {
        return totalPeopleRatedCount;
    }

    public void setTotalPeopleRatedCount(int totalPeopleRatedCount) {
        this.totalPeopleRatedCount = totalPeopleRatedCount;
    }

    public String getFactureDuration() {
        return factureDuration;
    }

    public void setFactureDuration(String factureDuration) {
        this.factureDuration = factureDuration;
    }

    public String getFactureDificulty() {
        return factureDificulty;
    }

    public void setFactureDificulty(String factureDificulty) {
        this.factureDificulty = factureDificulty;
    }

    public String getPostDate() {
        return postDate;
    }

    public void setPostDate(String postDate) {
        this.postDate = postDate;
    }

    public List<IngredientsModel> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<IngredientsModel> ingredients) {
        this.ingredients = ingredients;
    }
}
