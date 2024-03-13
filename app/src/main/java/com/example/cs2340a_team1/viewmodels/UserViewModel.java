package com.example.cs2340a_team1.viewmodels;

import com.example.cs2340a_team1.model.MealData;
import com.example.cs2340a_team1.model.UserData;

import java.util.ArrayList;

public class UserViewModel {
    private static UserViewModel instance;
    private UserData userData;

    private UserViewModel() { }

    public static synchronized UserViewModel getInstance() {
        if (instance == null) {
            instance = new UserViewModel();
            instance.userData = new UserData();
            instance.updateUser("");
            instance.updatePass("");
        }
        return instance;
    }

    public UserData getUserData() {
        return userData;
    }

    public void updateData(UserData data) {
        updateUser(data.getUser());
        updatePass(data.getPass());
        updateAge(data.getAge());
        updateGender(data.getGender());
        updateWeight(data.getWeight());
        updateHeight(data.getHeight());
        updateMeals(data.getMeals());
    }

    public void updateUser(String user) {
        userData.setUser(user);
    }

    public void updatePass(String pass) {
        userData.setPass(pass);
    }

    public void updateHeight(int height) {
        userData.setHeight(height);
    }

    public void updateAge(int age) {
        if(age > 0) {
            userData.setAge(age);
        }
    }

    public void updateWeight(int weight) {
        userData.setWeight(weight);
    }

    public void updateGender(String gender) {
        userData.setGender(gender);
    }

    public void setMeals(MealData mealData) {
        userData.addMeal(mealData);
    }

    private void updateMeals(ArrayList<MealData> meals) {
        userData.setMeals(meals);
    }
}
