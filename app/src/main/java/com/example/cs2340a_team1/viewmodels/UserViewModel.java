package com.example.cs2340a_team1.viewmodels;

import com.example.cs2340a_team1.model.UserData;

public class UserViewModel {
    private static UserViewModel instance;
    private final UserData userData;

    public UserViewModel() {
        userData = new UserData();
        this.updateUser("");
        this.updatePass("");
    }

    public static synchronized UserViewModel getInstance() {
        if (instance == null) {
            instance = new UserViewModel();
        }
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
}
