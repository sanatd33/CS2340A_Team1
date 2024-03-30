package com.example.cs2340a_team1.model;

public class IngredientData {
    private String ingredientName;
    private String calories;

    public IngredientData(String ingredientName, String calories) {
        setIngredientName(ingredientName);
        setCalories(calories);
    }
    public void setCalories(String calories) throws IllegalArgumentException {
        int calorieAmt;
        try {
            calorieAmt = Integer.parseInt(calories);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("The calorie amount has to be an integer");
        }
        if (calorieAmt <= 0) {
            throw new IllegalArgumentException("The calorie amount cannot be <= 0");
        }
        this.calories = calories;
    }
    public void setIngredientName(String ingredientName) throws IllegalArgumentException {
        if (ingredientName == null ||ingredientName.length() == 0) {
            throw new IllegalArgumentException("The ingredient name cannot be empty");
        }
        this.ingredientName = ingredientName;
    }
    public String getIngredientName() {
        return ingredientName;
    }
    public String getCalories() {
        return calories;
    }
}
