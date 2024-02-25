package com.example.cs2340a_team1.views;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.cs2340a_team1.R;


public class inputMealActivity extends AppCompatActivity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.inputmealscreen);
        Button toRecipeScreenButton = findViewById(R.id.toRecipe);
        Button toIngredientScreenButton = findViewById(R.id.toIngredient);
        Button toShoppingListScreenButton = findViewById(R.id.toShopping);

//        toIngredientScreenButton.setOnClickListener(v -> {
//            Intent intent = new Intent(inputMealActivity.this, IngredientActivity.class);
//            startActivity(intent);
//        });

        toShoppingListScreenButton.setOnClickListener(v -> {
            Intent intent = new Intent(inputMealActivity.this, ShoppingList.class);
            startActivity(intent);
        });
        toRecipeScreenButton.setOnClickListener(v -> {
            Intent intent = new Intent(inputMealActivity.this, RecipeActivity.class);
            startActivity(intent);
        });
    }
}
