package com.example.cs2340a_team1.viewmodels;

import com.example.cs2340a_team1.model.UserData;

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

}
