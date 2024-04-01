package com.example.cs2340a_team1.views;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.cs2340a_team1.R;
import com.example.cs2340a_team1.model.RecipeData;
import com.example.cs2340a_team1.viewmodels.CookbookViewModel;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class NewRecipeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_recipe);

        Button cancelButton = findViewById(R.id.cancelBtn);
        Button submitButton = findViewById(R.id.returnBtn);
        TextView errorText = findViewById(R.id.errorText);
        EditText ingredientName = findViewById(R.id.ingredientName);
        EditText ingredientQuantity = findViewById(R.id.quanitityText);
        EditText recipeName = findViewById(R.id.recipeName);
        Button addIngredientButton = findViewById(R.id.addIngredientBtn);
        TextView ingredientList = findViewById(R.id.recipeInfo);

        final RecipeData recipe = new RecipeData();

        recipeName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                recipe.setName(s.toString());
            }
        });

        errorText.setText("You cannot have an empty recipe");

        addIngredientButton.setOnClickListener(v -> {
            if (ingredientQuantity.getText().length() > 0
                    && ingredientName.getText().length() > 0
                    && Integer.valueOf(ingredientQuantity.getText().toString()) > 0) {
                recipe.addIngredient(ingredientName.getText().toString(),
                        Integer.valueOf(ingredientQuantity.getText().toString()));
                errorText.setText("");
                ingredientList.setText(ingredientList.getText() + "\n"
                        + ingredientName.getText().toString() + "\t\t-\t\t"
                        + ingredientQuantity.getText().toString());
                ingredientName.setText("");
                ingredientQuantity.setText("");
            }
        });

        cancelButton.setOnClickListener(v -> {
            Intent intent = new Intent(NewRecipeActivity.this, RecipeActivity.class);
            startActivity(intent);
        });

        submitButton.setOnClickListener(v -> {
            if (recipeName.getText().length() > 0) {
                if (recipe.getIngredientSet().size() > 0) {
                    FirebaseDatabase database = FirebaseDatabase.getInstance();
                    for (String name : recipe.getIngredientSet()) {
                        DatabaseReference ref = database.getReference("recipes/"
                                + recipeName.getText().toString() + "/" + name);
                        ref.setValue(recipe.getQuantity(name));
                    }

                    CookbookViewModel.getInstance().getData().addRecipe(recipe);
                    Intent intent = new Intent(NewRecipeActivity.this, RecipeActivity.class);
                    startActivity(intent);
                }
            } else {
                errorText.setText("You must set a recipe name");
            }
        });
    }
}