package com.example.cs2340a_team1.views;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.cs2340a_team1.R;


public class InputMealActivity extends AppCompatActivity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.inputmealscreen);
        Button toRecipeScreenButton = findViewById(R.id.toRecipe);
        Button toIngredientScreenButton = findViewById(R.id.toIngredient);
        Button toShoppingListScreenButton = findViewById(R.id.toShopping);
        Button toHomeScreenButton = findViewById(R.id.toHomeScreenButton);

        toIngredientScreenButton.setOnClickListener(v -> {
            Intent intent = new Intent(InputMealActivity.this, IngredientsActivity.class);
            startActivity(intent);
        });

        toShoppingListScreenButton.setOnClickListener(v -> {
            Intent intent = new Intent(InputMealActivity.this, ShoppingList.class);
            startActivity(intent);
        });
        toRecipeScreenButton.setOnClickListener(v -> {
            Intent intent = new Intent(InputMealActivity.this, RecipeActivity.class);
            startActivity(intent);
        });
        toHomeScreenButton.setOnClickListener(v -> {
            Intent intent = new Intent(InputMealActivity.this, HomeActivity.class);
            startActivity(intent);
        });
    }
}
