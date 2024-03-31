package com.example.cs2340a_team1.model;

public class MealData {

    public MealData(String mealName, String calorie) {
        setMealName(mealName);
        setCalorieAmt(calorie);
    }

    public MealData() {
        mealName = "";
        calorieAmt = -1;
    }
    private String mealName;
    private int calorieAmt;

    public String getMealName() {
        return this.mealName;
    }
    public void setMealName(String mealName) throws IllegalArgumentException {
        if (mealName == null || mealName.length() == 0) {
            throw new IllegalArgumentException("The meal name cannot be empty");
        }
        this.mealName = mealName;
    }

    public int getCalorieAmt() {
        return this.calorieAmt;
    }
    public void setCalorieAmt(String calorieStr) throws IllegalArgumentException {
        int calorieAmt;
        try {
            calorieAmt = Integer.parseInt(calorieStr);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("The calorie amount has to be an integer");
        }
        if (calorieAmt <= 0) {
            throw new IllegalArgumentException("The calorie amount cannot be <= 0");
        }
        this.calorieAmt = calorieAmt;
    }
}
