package com.example.cs2340a_team1.views;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.example.cs2340a_team1.R;
import com.example.cs2340a_team1.model.RecipeData;

public class DisplayRecipeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_recipe);

        Button returnBtn = findViewById(R.id.returnBtn);
        TextView recipeName = findViewById(R.id.recipeName);
        TextView ingredientList = findViewById(R.id.recipeInfo);

        RecipeData recipe = (RecipeData) getIntent().getSerializableExtra("recipe");

        recipeName.setText(recipe.getName());

        for (String s : recipe.getIngredientSet()) {
            ingredientList.append(s + "\t\t-\t\t" + recipe.getQuantity(s) + "\n");
        }

        returnBtn.setOnClickListener(v -> {
            Intent intent = new Intent(DisplayRecipeActivity.this, RecipeActivity.class);
            startActivity(intent);
        });
    }
}