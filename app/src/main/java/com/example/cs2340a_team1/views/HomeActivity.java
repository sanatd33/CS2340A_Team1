package com.example.cs2340a_team1.views;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import com.example.cs2340a_team1.R;

import androidx.appcompat.app.AppCompatActivity;

public class HomeActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);
        Button toInputMealScreenButton = findViewById(R.id.toInputMealScreenButton);
        Button toRecipeScreenButton = findViewById(R.id.toRecipeScreenButton);
        Button toIngredientScreenButton = findViewById(R.id.toIngredientScreenButton);
        Button toShoppingListScreenButton = findViewById(R.id.toShoppingListScreenButton);

        toInputMealScreenButton.setOnClickListener(v -> {
            Intent intent = new Intent(HomeActivity.this, inputMealActivity.class);
            startActivity(intent);
        });

        toShoppingListScreenButton.setOnClickListener(v -> {
            Intent intent = new Intent(HomeActivity.this, ShoppingList.class);
            startActivity(intent);
        });
        toRecipeScreenButton.setOnClickListener(v -> {
            Intent intent = new Intent(HomeActivity.this, RecipeActivity.class);
            startActivity(intent);
        });
        toIngredientScreenButton.setOnClickListener(v -> {
            Intent intent = new Intent(HomeActivity.this, IngredientsActivity.class);
            startActivity(intent);
        });
    }

}