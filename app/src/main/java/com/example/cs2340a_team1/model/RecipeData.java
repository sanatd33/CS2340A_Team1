package com.example.cs2340a_team1.model;

import java.util.HashMap;
import java.util.Set;

public class RecipeData {
    private HashMap<String, Integer> ingredientList;

    public RecipeData() {
        ingredientList = new HashMap<>();
    }

    public void addIngredient(String ingredient, Integer quantity) {
        if (quantity > 0) {
            ingredientList.put(ingredient, quantity);
        }
    }

    public Set<String> getIngredientSet() {
        return ingredientList.keySet();
    }

    public Integer getQuantity(String name) {
        return ingredientList.get(name);
    }
}
