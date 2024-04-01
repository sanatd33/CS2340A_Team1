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

    public RecipeData getRecipe(String name) {
        for (RecipeData recipeData : recipes) {
            if (name.equals(recipeData.getName())) {
                return recipeData;
            }
        }
        return null;
    }

    public void clearRecipes() {
        recipes.clear();
    }
}
