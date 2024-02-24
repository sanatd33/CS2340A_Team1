package com.example.cs2340a_team1.views;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import com.example.cs2340a_team1.R;

import androidx.appcompat.app.AppCompatActivity;

public class RecipeActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_screen);
        Button toInputMealScreenButton = findViewById(R.id.toInputMealScreenButton);
        Button toHomeScreenButton = findViewById(R.id.toRecipeScreenButton);
        Button toIngredientScreenButton = findViewById(R.id.toIngredientScreenButton);
        Button toShoppingListScreenButton = findViewById(R.id.toShoppingListScreenButton);

        toInputMealScreenButton.setOnClickListener(v -> {
            Intent intent = new Intent(RecipeActivity.this, inputMealActivity.class);
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
/*
        toIngredientScreenButton.setOnClickListener(v -> {
            Intent intent = new Intent(RecipeActivity.this, IngredientActivity.class);
            startActivity(intent);
        });

 */
    }

}