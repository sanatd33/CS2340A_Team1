package com.example.cs2340a_team1.views;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.cs2340a_team1.R;
import com.example.cs2340a_team1.viewmodels.UserViewModel;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class InputMealActivity extends AppCompatActivity {
    private String mealName;
    private int calorieAmt;
    private TextView errorText;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.inputmealscreen);
        Button toRecipeScreenButton = findViewById(R.id.toRecipe);
        Button toIngredientScreenButton = findViewById(R.id.toIngredient);
        Button toShoppingListScreenButton = findViewById(R.id.toShopping);
        Button toHomeScreenButton = findViewById(R.id.toHomeScreenButton);
        Button toPersonalInfoScreenButton = findViewById(R.id.toPersonalInfoScreenButton);
        Button submitMealButton = findViewById(R.id.submitMeal);
        EditText mealNameText = findViewById(R.id.mealNameTxt);
        EditText calorieAmtText = findViewById(R.id.calorieAmtTxt);
        UserViewModel userViewModel = UserViewModel.getInstance();

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
        toPersonalInfoScreenButton.setOnClickListener(v -> {
            Intent intent = new Intent(InputMealActivity.this, PersonalInfoActivity.class);
            startActivity(intent);
        });

        mealNameText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) { }
            @Override
            public void afterTextChanged(Editable editable) { }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                mealName = mealNameText.getText().toString();
                if (mealName.trim().length() == 0) {
                    errorText.setText("The username cannot be empty");
                } else if (mealName.contains(" ")) {
                    errorText.setText("The username cannot contain spaces");
                } else {
                    errorText.setText("");
                }
            }
        });

        calorieAmtText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) { }
            @Override
            public void afterTextChanged(Editable editable) { }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                calorieAmt = Integer.parseInt(calorieAmtText.getText().toString());
                if (calorieAmt <= 0) {
                    errorText.setText("The username cannot be negative or 0.");
                } else {
                    errorText.setText("");
                }
            }
        });

        submitMealButton.setOnClickListener(v -> {
            FirebaseDatabase database = FirebaseDatabase.getInstance();
            DatabaseReference ref = database.getReference(userViewModel.getUserData().getUser());
            userViewModel.setMeals(mealName, calorieAmt);
            ref.setValue(userViewModel);
            mealName = "";
            calorieAmt = 0;
        });
    }
}
