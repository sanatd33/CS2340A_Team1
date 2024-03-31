package com.example.cs2340a_team1.views;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.cs2340a_team1.model.IngredientData;
import com.example.cs2340a_team1.model.UserData;

import com.example.cs2340a_team1.R;
import com.example.cs2340a_team1.viewmodels.UserViewModel;

public class IngredientsActivity extends AppCompatActivity {
    private String mealName;
    private int calorieAmt;
    private TextView errorText;
    private TextView personalInfoText;
    private UserViewModel user = UserViewModel.getInstance();
    //private IngredientData ingredientData = new IngredientData();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ingredients);

        Button toInputMealScreenButton = findViewById(R.id.toInputMealScreenButton);
        Button toHomeScreenButton = findViewById(R.id.toHomeScreenButton);
        Button toRecipeScreenButton = findViewById(R.id.toRecipeScreenButton);
        Button toShoppingListScreenButton = findViewById(R.id.toShoppingListScreenButton);
        Button toPersonalInfoScreenButton = findViewById(R.id.toPersonalInfoScreenButton);
        Button toFormButton = findViewById(R.id.toFormButton);

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
        toFormButton.setOnClickListener(v -> {
            Intent intent = new Intent(IngredientsActivity.this, FormActivity.class);
            startActivity(intent);
        });

    }
}