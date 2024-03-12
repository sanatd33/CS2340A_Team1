package com.example.cs2340a_team1.viewmodels;

import com.example.cs2340a_team1.model.ShoppingData;

public class ShoppingViewModel {
    private static ShoppingViewModel instance;
    private ShoppingData shoppingData;

    private ShoppingViewModel() {}

    //On start up sets total cost to 0
    public static synchronized ShoppingViewModel getInstance() {
        if (instance == null) {
            instance = new ShoppingViewModel();
            instance.shoppingData = new ShoppingData();
            instance.updateCost(0);
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
