package com.example.cs2340a_team1.model;

import android.util.Pair;

import java.util.ArrayList;
import java.util.HashMap;

public class UserData {
    private String user;
    private ArrayList<MealData> meals = new ArrayList<>();
    private HashMap<String, Pair<IngredientData, Integer>> ingredients = new HashMap<>();
    private HashMap<String, Pair<IngredientData, Integer>> shopping = new HashMap<>();

    private String pass;

    private int height;
    private int weight;
    private String gender = "";

    private int age;


    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        if (isUsernameValid(user)) {
            this.user = user;
        }
    }

    public void addMeal(MealData mealData) {
        meals.add(mealData);
    }
    public void addIngredient(IngredientData ingredientData, Integer count) {
        if (ingredients.containsKey(ingredientData.getIngredientName())) {
            throw new IllegalArgumentException("The given ingredient already exists");
        }
        ingredients.put(ingredientData.getIngredientName(), new Pair<>(ingredientData, count));
    }

    public void addIngredient2(IngredientData ingredientData, Integer count) {
        ingredients.put(ingredientData.getIngredientName(), new Pair<>(ingredientData, count));
    }


    public void updateIngredient(IngredientData ingredientData, Integer count) {
        if (!ingredients.containsKey(ingredientData.getIngredientName())) {
            throw new IllegalArgumentException("The given ingredient does not exist");
        }
        ingredients.put(ingredientData.getIngredientName(), new Pair<>(ingredientData, count));
    }

    public void removeIngredient(IngredientData ingredientData) {
        if (!ingredients.containsKey(ingredientData.getIngredientName())) {
            throw new IllegalArgumentException("The given ingredient does not exist");
        }
        ingredients.remove(ingredientData.getIngredientName());
    }

    public void addShopping(IngredientData ingredientData, Integer count) {
//        if (shopping.containsKey(ingredientData.getIngredientName())) {
//            throw new IllegalArgumentException("The given ingredient already exists");
//        }
        shopping.put(ingredientData.getIngredientName(), new Pair<>(ingredientData, count));
    }


    public void updateShopping(IngredientData ingredientData, Integer count) {
        if (!shopping.containsKey(ingredientData.getIngredientName())) {
            throw new IllegalArgumentException("The given ingredient does not exist");
        }
        shopping.put(ingredientData.getIngredientName(), new Pair<>(ingredientData, count));
    }

    public void removeShopping(IngredientData ingredientData) {
        if (!shopping.containsKey(ingredientData.getIngredientName())) {
            throw new IllegalArgumentException("The given ingredient does not exist");
        }
        shopping.remove(ingredientData.getIngredientName());
    }

    public HashMap<String, Pair<IngredientData, Integer>> getShopping(){
        return shopping;
    }


    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        if (isPasswordValid(pass)) {
            this.pass = pass;
        }
    }
    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
    public boolean isUsernameValid(String username) {
        if (username == null) {
            return false;
        }
        if (username.length() > 0) {
            if (!username.contains(" ")) {
                return true;
            }
        }
        return false;
    }
    public boolean isPasswordValid(String password) {
        if (password == null) {
            return false;
        }
        if (password.length() > 0) {
            if (!password.contains(" ")) {
                return true;
            }
        }
        return false;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public MealData getMealData(String mealName) {
        for (int i = 0; i < meals.size(); i++) {
            MealData check = meals.get(i);
            if (check.getMealName().equals(mealName)) {
                return check;
            }
        }
        return null;
    }

    public ArrayList<MealData> getMeals() {
        return meals;
    }

    public void setMeals(ArrayList<MealData> meals) {
        this.meals = meals;
    }
    public HashMap<String, Pair<IngredientData, Integer>> getIngredients() {
        return ingredients;
    }
    public void setIngredients(HashMap<String, Pair<IngredientData, Integer>> ingredients) {
        this.ingredients = ingredients;
    }

    public void clearIngredients() {
        ingredients.clear();
    }

    public void clearShopping() {
        shopping.clear();
    }

    public double getCalorieGoal() {
        if (gender.equals("M")) {
            return 13.397 * weight + 4.799 * height - 5.677 * age + 88.362;
        } else if (gender.equals("F")) {
            return 9.247 * weight + 3.098 * height - 4.330 * age + 447.593;
        }
        return -1;
    }
//    public void addToShoppingList(String ingredientName, int quantity) {
//        shoppingList.put(ingredientName, quantity);
//    }
//    public void removeFromShoppingList(String ingredientName, int quantity) {
////        if (shoppingList.containsKey(ingredientName)) {
////            int currentQuantity = shoppingList.get(ingredientName);
////            int newQuantity = currentQuantity - quantity;
////            if (newQuantity <= 0) {
////                shoppingList.remove(ingredientName);
////            } else {
////                shoppingList.put(ingredientName, newQuantity);
////            }
////        }
//        shoppingList.remove(ingredientName);
//    }
    public HashMap<String, Pair<IngredientData, Integer>> getShoppingList() {
        return shopping;
    }
//    public void clearShoppingList() {
//        shoppingList.clear();
//    }
}
