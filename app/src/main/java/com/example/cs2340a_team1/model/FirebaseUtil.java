package com.example.cs2340a_team1.model;


import com.example.cs2340a_team1.viewmodels.UserViewModel;
import com.google.firebase.database.DataSnapshot;

import java.util.ArrayList;
import java.util.HashMap;

public class FirebaseUtil {
    public static UserData loadFromFirebase(DataSnapshot snapshot) throws IllegalArgumentException {
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
                MealData mealData = new MealData(meal.get("mealName"),
                        String.valueOf(meal.get("calorieAmt")));
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

        if (mapActual.containsKey("ingredients")) {
            HashMap<String, Object> map4 = (HashMap<String, Object>) mapActual.get("ingredients");
            userData.clearIngredients();
            for (String name : map4.keySet()) {
                HashMap<String, Object> map2 =
                        (HashMap<String, Object>) map4.get(name);

                HashMap<String, String> map3 =
                        (HashMap<String, String>) map2.get("first");

                IngredientData ing = new IngredientData(map3.get("ingredientName"),
                        map3.get("calories"));
                int count = Math.toIntExact((Long) map2.get("second"));
                userData.addIngredient(ing, count);
            }
        }

        if (mapActual.containsKey("shoppingList")) {
            HashMap<String, Object> map4 = (HashMap<String, Object>) mapActual.get("shoppingList");
            userData.clearShopping();
            for (String name : map4.keySet()) {
                HashMap<String, Object> map2 =
                        (HashMap<String, Object>) map4.get(name);

                HashMap<String, String> map3 =
                        (HashMap<String, String>) map2.get("first");

                IngredientData ing = new IngredientData(map3.get("ingredientName"),
                        map3.get("calories"));
                int count = Math.toIntExact((Long) map2.get("second"));
                userData.addShopping(ing, count);
            }
        }

        UserViewModel.getInstance().updateData(userData);
        return userData;
    }
}
