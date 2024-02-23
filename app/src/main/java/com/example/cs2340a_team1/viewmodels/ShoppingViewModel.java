package com.example.cs2340a_team1.viewmodels;

import com.example.cs2340a_team1.model.ShoppingData;

public class ShoppingViewModel {
    private final ShoppingData shoppingData;
    private static ShoppingViewModel instance;
    //On start up sets total cost to 0
    public ShoppingViewModel() {
        shoppingData = new ShoppingData();
        this.updateCost(0);
    }
    public static synchronized ShoppingViewModel getInstance() {
        if (instance == null) {
            instance = new ShoppingViewModel();
        }
        return instance;
    }

    public ShoppingData getShoppingData() {
        return shoppingData;
    }

    //called when user adds new item to list to calculate new price
    public void updateCost(int itemCost) {
        int currentCost = shoppingData.getTotalPrice();
        currentCost += itemCost;
        shoppingData.setTotalPrice(currentCost);
    }
}
