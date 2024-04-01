package com.example.cs2340a_team1.model;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Set;

public class RecipeData implements Serializable {
    String name = new String();
    private HashMap<String, Integer> ingredientList;

    public RecipeData() {
        ingredientList = new HashMap<>();
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName(){
        return name;
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
