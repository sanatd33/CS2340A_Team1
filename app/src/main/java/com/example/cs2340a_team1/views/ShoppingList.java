package com.example.cs2340a_team1.views;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;

import com.example.cs2340a_team1.R;
import com.example.cs2340a_team1.viewmodels.ShoppingViewModel;


public class ShoppingList extends AppCompatActivity {
    private ShoppingViewModel viewModel;
    private EditText totalCostText;


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

        // Buttons
        toInputMealScreenButton.setOnClickListener(v -> {
            Intent intent = new Intent(ShoppingList.this, inputMealActivity.class);
            startActivity(intent);
        });
        toRecipeScreenButton.setOnClickListener(v -> {
            Intent intent = new Intent(ShoppingList.this, RecipeActivity.class);
            startActivity(intent);
        });

        //when ingredient screen coded uncomment this
        /*toRecipeScreenButton.setOnClickListener(v -> {
            Intent intent = new Intent(ShoppingList.this, IngredientActivity.class);
            startActivity(intent);
        });
        */
    }

}
