package com.example.cs2340a_team1.views;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.cs2340a_team1.R;

public class RecipeActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_screen);
        Button toInputMealScreenButton = findViewById(R.id.toInputMealScreenButton);
        Button toHomeScreenButton = findViewById(R.id.toHomeScreenButton);
        Button toIngredientScreenButton = findViewById(R.id.toRecipeScreenButton);
        Button toShoppingListScreenButton = findViewById(R.id.toShoppingListScreenButton);

        toInputMealScreenButton.setOnClickListener(v -> {
            Intent intent = new Intent(RecipeActivity.this, InputMealActivity.class);
            startActivity(intent);
        });

        toShoppingListScreenButton.setOnClickListener(v -> {
            Intent intent = new Intent(RecipeActivity.this, ShoppingList.class);
            startActivity(intent);
        });

        toHomeScreenButton.setOnClickListener(v -> {
            Intent intent = new Intent(RecipeActivity.this, HomeActivity.class);
            startActivity(intent);
        });
        toIngredientScreenButton.setOnClickListener(v -> {
            Intent intent = new Intent(RecipeActivity.this, IngredientsActivity.class);
            startActivity(intent);
        });
    }

}