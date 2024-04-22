package com.example.cs2340a_team1.viewmodels;

import android.util.Pair;

import com.example.cs2340a_team1.model.IngredientData;
import com.example.cs2340a_team1.model.MealData;
import com.example.cs2340a_team1.model.UserData;

import java.util.ArrayList;
import java.util.HashMap;

public class UserViewModel {
    private static UserViewModel instance;

    //Creator Pattern
    //UserViewModel closely uses UserData by creating an instance of it
    //and using that instance in the updateData method
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
        updateIngredients(data.getIngredients());
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
        if (age > 0) {
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
    public void setIngredient(IngredientData ingredientData, Integer count) {
        userData.addIngredient((ingredientData), count);
    }

    public void updateIngredient(IngredientData ingredientData, Integer count) {
        userData.updateIngredient((ingredientData), count);
    }

    public void addIngredient(IngredientData ingredientData, Integer count) {
        userData.addIngredient2((ingredientData), count);
    }

    public void removeIngredient(IngredientData ingredientData) {
        userData.removeIngredient(ingredientData);
    }

    public void setShopping(IngredientData ingredientData, Integer count) {
        userData.addShopping((ingredientData), count);
    }

    public void updateShopping(IngredientData ingredientData, Integer count) {
        userData.updateShopping((ingredientData), count);
    }

    public void removeShopping(IngredientData ingredientData) {
        userData.removeShopping(ingredientData);
    }

    private void updateMeals(ArrayList<MealData> meals) {
        userData.setMeals(meals);
    }
    private void updateIngredients(HashMap<String, Pair<IngredientData, Integer>> ingredient) {
        userData.setIngredients(ingredient);
    }

}
