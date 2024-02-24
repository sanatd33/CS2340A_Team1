package com.example.androidprojecttemplate;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
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

       /* toInputMealScreenButton = findViewById(R.id.toInputMealScreenButton);
        toInputMealScreenButton.setOnClickListener(v -> {
            Intent intent = new Intent(HomeActivity.this, InputMealActivity.class);
            startActivity(intent);
        });

        */
    }

}