package com.example.cs2340a_team1.model;

import java.util.ArrayList;

public class UserData {
    private String user;
    private ArrayList<MealData> meals = new ArrayList<>();
    private String pass;

    private int height;
    private int weight;
    private String gender;


    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        if (isUsernameValid(user)) {
            this.user = user;
        }
    }

    public void addMeal(String mealName, int calorieAmt) {
        MealData meal = new MealData();
        meal.setMealName(mealName);
        meal.setCalorieAmt(calorieAmt);
        meals.add(meal);
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        if (isPasswordValid(pass)) {
            this.pass = pass;
        }
    }
    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
    public boolean isUsernameValid(String username) {
        if (username == null) {
            return false;
        }
        if (username.length() > 0) {
            if (!username.contains(" ")) {
                return true;
            }
        }
        return false;
    }
    public boolean isPasswordValid(String password) {
        if (password == null) {
            return false;
        }
        if (password.length() > 0) {
            if (!password.contains(" ")) {
                return true;
            }
        }
        return false;
    }

    public MealData getMealData(String mealName) {
        for (int i = 0; i < meals.size(); i++) {
            MealData check = meals.get(i);
            if (check.getMealName().equals(mealName)) {
                return check;
            }
        }
        return null;
    }
}
