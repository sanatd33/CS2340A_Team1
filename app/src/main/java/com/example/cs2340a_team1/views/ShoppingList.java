package com.example.cs2340a_team1.views;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.cs2340a_team1.R;
import com.example.cs2340a_team1.viewmodels.ShoppingViewModel;


public class ShoppingList extends AppCompatActivity {
    private ShoppingViewModel viewModel;
    private TextView totalCostText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.shoppingscreen);
        viewModel = ShoppingViewModel.getInstance();
        totalCostText = findViewById(R.id.total_price);
        Button checkOutButton = findViewById(R.id.button_check_out);
        Button toInputMealScreenButton = findViewById(R.id.ToMeal);
        Button toRecipeScreenButton = findViewById(R.id.ToRecipe);
        Button toIngredientScreenButton = findViewById(R.id.toIngredient);
        Button toHomeScreenButton = findViewById(R.id.toHomeScreenButton);
        Button toPersonalInfoScreenButton = findViewById(R.id.toPersonalInfoScreenButton);

        // Buttons
        toInputMealScreenButton.setOnClickListener(v -> {
            Intent intent = new Intent(ShoppingList.this, InputMealActivity.class);
            startActivity(intent);
        });
        toRecipeScreenButton.setOnClickListener(v -> {
            Intent intent = new Intent(ShoppingList.this, RecipeActivity.class);
            startActivity(intent);
        });

        toIngredientScreenButton.setOnClickListener(v -> {
            Intent intent = new Intent(ShoppingList.this, IngredientsActivity.class);
            startActivity(intent);
        });

        toHomeScreenButton.setOnClickListener(v -> {
            Intent intent = new Intent(ShoppingList.this, HomeActivity.class);
            startActivity(intent);
        });

        toPersonalInfoScreenButton.setOnClickListener(v -> {
            Intent intent = new Intent(ShoppingList.this, PersonalInfoActivity.class);
            startActivity(intent);
        });
    }

}
