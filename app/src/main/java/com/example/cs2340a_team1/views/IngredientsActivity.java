package com.example.cs2340a_team1.views;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.example.cs2340a_team1.R;

public class IngredientsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ingredients);

        Button toInputMealScreenButton = findViewById(R.id.toInputMealScreenButton);
        Button toHomeScreenButton = findViewById(R.id.toHomeScreenButton);
        Button toRecipeScreenButton = findViewById(R.id.toRecipeScreenButton);
        Button toShoppingListScreenButton = findViewById(R.id.toShoppingListScreenButton);
        Button toPersonalInfoScreenButton = findViewById(R.id.toPersonalInfoScreenButton);

        //goal: need to create a button to add and remove ingredients from pantry

        toInputMealScreenButton.setOnClickListener(v -> {
            Intent intent = new Intent(IngredientsActivity.this, InputMealActivity.class);
            startActivity(intent);
        });

        toShoppingListScreenButton.setOnClickListener(v -> {
            Intent intent = new Intent(IngredientsActivity.this, ShoppingList.class);
            startActivity(intent);
        });

        toHomeScreenButton.setOnClickListener(v -> {
            Intent intent = new Intent(IngredientsActivity.this, HomeActivity.class);
            startActivity(intent);
        });
        toRecipeScreenButton.setOnClickListener(v -> {
            Intent intent = new Intent(IngredientsActivity.this, RecipeActivity.class);
            startActivity(intent);
        });
        toPersonalInfoScreenButton.setOnClickListener(v -> {
            Intent intent = new Intent(IngredientsActivity.this, PersonalInfoActivity.class);
            startActivity(intent);
        });
    }
}