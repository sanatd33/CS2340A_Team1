package com.example.cs2340a_team1.model;

import java.util.ArrayList;

public class CookbookData {
    private ArrayList<RecipeData> recipes = new ArrayList<>();

    public void addRecipe(RecipeData recipe) {
        recipes.add(recipe);
    }

    public ArrayList<RecipeData> getRecipes() {
        return recipes;
    }

    public void clearRecipes() {
        recipes.clear();
    }
}
