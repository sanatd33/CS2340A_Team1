package com.example.cs2340a_team1.viewmodels;

import com.example.cs2340a_team1.model.ShoppingData;


import com.example.cs2340a_team1.model.UserData;


import java.util.Map;

import java.util.HashMap;
import java.util.Map;

public class ShoppingViewModel {

    private static ShoppingViewModel instance;
    private UserData userData;

    private ShoppingViewModel() {

    }

    public static synchronized ShoppingViewModel getInstance() {
        if (instance == null) {
            instance = new ShoppingViewModel();
        }
        return instance;
    }
    public void setUserData(UserData userData) {
        this.userData = userData;
    }

    public void addToShoppingList(String ingredientName, int quantity) {
        if(userData != null) {
            userData.addToShoppingList(ingredientName, quantity);
        }
    }

    public void removeFromShoppingList(String ingredientName, int quantity) {
        if (userData != null) {
            userData.removeFromShoppingList(ingredientName, quantity);
        }
    }

    public void clearShoppingList() {
        if (userData != null) {
            userData.clearShoppingList();
        }
    }

    public Map<String, Integer> getShoppingList() {
        if (userData != null) {
            return userData.getShoppingList();
        }
        return null;
    }
}
