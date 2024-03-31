package com.example.cs2340a_team1.views;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
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
    private IngredientData ingredientData = new IngredientData();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ingredients);

        Button toInputMealScreenButton = findViewById(R.id.toInputMealScreenButton);
        Button toHomeScreenButton = findViewById(R.id.toHomeScreenButton);
        Button toRecipeScreenButton = findViewById(R.id.toRecipeScreenButton);
        Button toShoppingListScreenButton = findViewById(R.id.toShoppingListScreenButton);
        Button toPersonalInfoScreenButton = findViewById(R.id.toPersonalInfoScreenButton);
        Button enterButton = findViewById(R.id.EnterButton);

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

        enterButton.setOnClickListener(v -> {
            EditText ingredientName = findViewById(R.id.IngredientName);
            EditText ingredientQuantity = findViewById(R.id.IngredientQuantity);

            EditText caloriesAmt = findViewById(R.id.Calories);
            EditText caloriesQuantity = findViewById(R.id.CalorieQuantity);

            EditText expiration = findViewById(R.id.Expiration);
            String name = "";
            String quantity;
            String calories;
            String calQuantity;
            int x;

            if (ingredientName.toString().length() != 0) {
                name = ingredientName.toString();
            }

            try {
                x = Integer.parseInt(ingredientQuantity.toString());
            } catch (NumberFormatException e) {
                    throw new IllegalArgumentException("The ingredient amount has to be an integer");
            }
            if (x <= 0) {
                throw new IllegalArgumentException("The ingredient amount cannot be <= 0");
            } else {
                quantity = ingredientQuantity.toString();
            }

            try {
                x = Integer.parseInt(caloriesAmt.toString());
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException("The calorie has to be an integer");
            }
            if (x <= 0) {
                throw new IllegalArgumentException("The calorie cannot be <= 0");
            } else {
                calories = caloriesAmt.toString();
            }

            try {
                x = Integer.parseInt(caloriesQuantity.toString());
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException("The calorie amount has to be an integer");
            }
            if (x <= 0) {
                throw new IllegalArgumentException("The calorie amount cannot be <= 0");
            } else {
                calQuantity = caloriesQuantity.toString();
            }

            IngredientData ingredientData1 = new IngredientData(name, calories);
            user.setIngredient(ingredientData1);
            user.updateData(user.getUserData());
        });

    }
}