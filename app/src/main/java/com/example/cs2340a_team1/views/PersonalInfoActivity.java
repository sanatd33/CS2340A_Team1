package com.example.cs2340a_team1.views;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.Button;
import android.widget.EditText;

import com.example.cs2340a_team1.R;
import com.example.cs2340a_team1.viewmodels.UserViewModel;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class PersonalInfoActivity extends AppCompatActivity {
    private int height = 0;
    private int weight = 0;
    private String gender = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_info);

        Button toInputMealScreenButton = findViewById(R.id.toInputMealScreenButton);
        Button toHomeScreenButton = findViewById(R.id.toHomeScreenButton);
        Button toRecipeScreenButton = findViewById(R.id.toRecipeScreenButton);
        Button toShoppingListScreenButton = findViewById(R.id.toShoppingListScreenButton);
        Button toIngredientsScreenButton = findViewById(R.id.toIngredientScreenButton);
        Button submitChoicesButton = findViewById(R.id.submitChoicesButton);

        EditText heightEditText = findViewById(R.id.heightText);
        EditText weightEditText = findViewById(R.id.weightText);
        EditText genderEditText = findViewById(R.id.genderText);

        toInputMealScreenButton.setOnClickListener(v -> {
            Intent intent = new Intent(PersonalInfoActivity.this, InputMealActivity.class);
            startActivity(intent);
        });

        toShoppingListScreenButton.setOnClickListener(v -> {
            Intent intent = new Intent(PersonalInfoActivity.this, ShoppingList.class);
            startActivity(intent);
        });

        toHomeScreenButton.setOnClickListener(v -> {
            Intent intent = new Intent(PersonalInfoActivity.this, HomeActivity.class);
            startActivity(intent);
        });
        toRecipeScreenButton.setOnClickListener(v -> {
            Intent intent = new Intent(PersonalInfoActivity.this, RecipeActivity.class);
            startActivity(intent);
        });
        toIngredientsScreenButton.setOnClickListener(v -> {
            Intent intent = new Intent(PersonalInfoActivity.this, IngredientsActivity.class);
            startActivity(intent);
        });

        heightEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (charSequence.length() > 0) {
                    height = Integer.parseInt(charSequence.toString());
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        weightEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (charSequence.length() > 0) {
                    weight = Integer.parseInt(charSequence.toString());
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        genderEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (charSequence.length() > 0) {
                    gender = charSequence.toString().toLowerCase();
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        submitChoicesButton.setOnClickListener(v -> {
            UserViewModel model = UserViewModel.getInstance();
            model.updateHeight(height);
            model.updateWeight(weight);
            model.updateGender(gender);

            FirebaseDatabase database = FirebaseDatabase.getInstance();
            DatabaseReference ref = database.getReference(model.getUserData().getUser());
            ref.setValue(model);

            weight = 0;
            height = 0;
            gender = "";
            heightEditText.setText("");
            weightEditText.setText("");
            genderEditText.setText("");
        });
    }
}