package com.example.recipeapp.Models;

public class IngredientsModel {

    private String amount;
    private String ingredientName;

    public IngredientsModel(String amount, String ingredientName) {
        this.amount = amount;
        this.ingredientName = ingredientName;
    }

    public IngredientsModel() {
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getIngredientName() {
        return ingredientName;
    }

    public void setIngredientName(String ingredientName) {
        this.ingredientName = ingredientName;
    }
}
