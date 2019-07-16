package com.example.recipeapp.Models;

import java.util.List;

public class RecipeModel {

    private String id;
    private String creatorId;
    private String mealType;
    private String title;
    private String shortDescription;
    private int sumOfRating;
    private int totalPeopleRatedCount;
    private String factureDescription;
    private String factureDificulty;
    private long postDate;

    private boolean userRatedRecipe;
    private List<String> usersWhoRatedRecipeId;
    private List<IngredientsModel> ingredients;

    //Kas ivertino, recepto id, kiek ivertino, suma ivertinimo


    public RecipeModel(String id, String creatorId, String mealType, String title, String shortDescription,
                       int sumOfRating, int totalPeopleRatedCount, String factureDescription,
                       String factureDificulty, long postDate, boolean userRatedRecipe,
                       List<String> usersWhoRatedRecipeId, List<IngredientsModel> ingredients) {
        this.id = id;
        this.creatorId = creatorId;
        this.mealType = mealType;
        this.title = title;
        this.shortDescription = shortDescription;
        this.sumOfRating = sumOfRating;
        this.totalPeopleRatedCount = totalPeopleRatedCount;
        this.factureDescription = factureDescription;
        this.factureDificulty = factureDificulty;
        this.postDate = postDate;
        this.userRatedRecipe = userRatedRecipe;
        this.usersWhoRatedRecipeId = usersWhoRatedRecipeId;
        this.ingredients = ingredients;
    }

    public RecipeModel() {
    }

    public boolean isUserRatedRecipe() {
        return userRatedRecipe;
    }

    public void setUserRatedRecipe(boolean userRatedRecipe) {
        this.userRatedRecipe = userRatedRecipe;
    }

    public List<String> getUsersWhoRatedRecipeId() {
        return usersWhoRatedRecipeId;
    }

    public void setUsersWhoRatedRecipeId(List<String> usersWhoRatedRecipeId) {
        this.usersWhoRatedRecipeId = usersWhoRatedRecipeId;
    }

    public String getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(String creatorId) {
        this.creatorId = creatorId;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    public String getFactureDescription() {
        return factureDescription;
    }

    public void setFactureDescription(String factureDescription) {
        this.factureDescription = factureDescription;
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
        return shortDescription;
    }

    public void setDescription(String description) {
        this.shortDescription = description;
    }

    public int getSumOfRating() {
        return sumOfRating;
    }

    public void setSumOfRating(int sumOfRating) {
        this.sumOfRating = sumOfRating;
    }

    public int getTotalPeopleRatedCount() {
        return totalPeopleRatedCount;
    }

    public void setTotalPeopleRatedCount(int totalPeopleRatedCount) {
        this.totalPeopleRatedCount = totalPeopleRatedCount;
    }

    public String getFactureDuration() {
        return factureDescription;
    }

    public void setFactureDuration(String factureDuration) {
        this.factureDescription = factureDuration;
    }

    public String getFactureDificulty() {
        return factureDificulty;
    }

    public void setFactureDificulty(String factureDificulty) {
        this.factureDificulty = factureDificulty;
    }

    public long getPostDate() {
        return postDate;
    }

    public void setPostDate(long postDate) {
        this.postDate = postDate;
    }

    public List<IngredientsModel> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<IngredientsModel> ingredients) {
        this.ingredients = ingredients;
    }

    @Override
    public String toString() {
        return id + creatorId + mealType + title + shortDescription + sumOfRating + totalPeopleRatedCount + factureDescription + factureDificulty + postDate;
    }
}
