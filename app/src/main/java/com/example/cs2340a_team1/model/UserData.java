package com.example.cs2340a_team1.model;

import java.util.ArrayList;

public class UserData {
    private String user;
    private ArrayList<MealData> meals = new ArrayList<>();
    private String pass;

    private int height;
    private int weight;
    private String gender;

    private int age;


    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public void addMeal(MealData mealData) {
        meals.add(mealData);
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
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

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
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

    public ArrayList<MealData> getMeals() {
        return meals;
    }

    public void setMeals(ArrayList<MealData> meals) {
        this.meals = meals;
    }

    public double getCalorieGoal() {
        if (gender.equals("M")) {
             return 13.397 * weight + 4.799 * height - 5.677 * age + 88.362;
        } else if (gender.equals("F")) {
            return 9.247 * weight + 3.098 * height - 4.330 * age + 447.593;
        }
        return -1;
    }
}
