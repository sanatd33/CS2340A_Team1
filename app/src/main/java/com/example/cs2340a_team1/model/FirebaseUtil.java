package com.example.cs2340a_team1.model;

import androidx.annotation.NonNull;

import com.example.cs2340a_team1.viewmodels.UserViewModel;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;

public class FirebaseUtil {
    public static UserData loadFromFirebase(DataSnapshot snapshot) throws IllegalArgumentException{
        HashMap<Object, Object> map = (HashMap<Object, Object>) snapshot.getValue();
        HashMap<String, Object> mapActual = (HashMap<String, Object>) map.get("userData");
        UserData userData = new UserData();
        userData.setUser((String) mapActual.get("user"));
        userData.setPass((String) mapActual.get("pass"));

        if (mapActual.containsKey("age")) {
            userData.setAge(Math.toIntExact((Long) mapActual.get("age")));
        }
        if (mapActual.containsKey("gender")) {
            userData.setGender((String) mapActual.get("gender"));
        }
        if (mapActual.containsKey("meals")) {
            ArrayList<HashMap<String, String>> mealsMap = (ArrayList) mapActual.get("meals");
            ArrayList<MealData> meals = new ArrayList<>();
            for (HashMap<String, String> meal : mealsMap) {
                MealData mealData = new MealData(meal.get("mealName"), String.valueOf(meal.get("calorieAmt")));
                meals.add(mealData);
            }
            userData.setMeals(meals);
        }
        if (mapActual.containsKey("height")) {
            userData.setHeight(Math.toIntExact((Long) mapActual.get("height")));
        }
        if (mapActual.containsKey("weight")) {
            userData.setWeight(Math.toIntExact((Long) mapActual.get("weight")));
        }

        UserViewModel.getInstance().updateData(userData);
        return userData;
    }
}
