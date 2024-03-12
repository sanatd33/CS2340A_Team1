package com.example.cs2340a_team1.viewmodels;

import com.example.cs2340a_team1.model.UserData;

public class UserViewModel {
    private static UserViewModel instance;
    private UserData userData;

    private UserViewModel() {}

    public static synchronized UserViewModel getInstance() {
        if (instance == null) {
            instance = new UserViewModel();
        }
        instance.userData = new UserData();
        instance.updateUser("");
        instance.updatePass("");
        return instance;
    }

    public static synchronized void setInstance(UserViewModel model) {
        instance = model;
    }

    public UserData getUserData() {
        return userData;
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

    public void updateWeight(int weight) {
        userData.setWeight(weight);
    }

    public void updateGender(String gender) {
        userData.setGender(gender);
    }

    public void setMeals(String mealName, int calorieAmt) {
        userData.addMeal(mealName, calorieAmt);
    }
}
