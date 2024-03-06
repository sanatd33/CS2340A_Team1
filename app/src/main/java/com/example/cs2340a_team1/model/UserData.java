package com.example.cs2340a_team1.model;

import java.util.ArrayList;

public class UserData {
    private String user;
    private ArrayList<MealData> meals = new ArrayList<MealData>();

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public void addMeal(String mealName, int calorieAmt) {
        MealData meal = new MealData();
        meal.setMealName(mealName);
        meal.setCalorieAmt(calorieAmt);
        meals.add(meal);
    }

}
