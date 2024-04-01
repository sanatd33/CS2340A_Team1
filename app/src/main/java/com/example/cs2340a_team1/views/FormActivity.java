package com.example.cs2340a_team1.views;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.cs2340a_team1.model.IngredientData;

import com.example.cs2340a_team1.R;
import com.example.cs2340a_team1.viewmodels.UserViewModel;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class FormActivity extends AppCompatActivity {

    private TextView errorText;
    private UserViewModel user = UserViewModel.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ingredient_form_screen);

        Button enterButton = findViewById(R.id.EnterButton);
        Button removeButton = findViewById(R.id.RemoveButton);
        Button returnButton = findViewById(R.id.toIngredientsBtn);
        EditText ingredientName = findViewById(R.id.IngredientName);
        EditText ingredientQuantity = findViewById(R.id.IngredientQuantity);

        EditText caloriesAmt = findViewById(R.id.Calories);

        EditText expiration = findViewById(R.id.Expiration);
        errorText = findViewById(R.id.errorTextView);
        
        removeButton.setOnClickListener(v ->  {
            try {
                IngredientData ingredientData =
                        new IngredientData(ingredientName.getText().toString(),
                                caloriesAmt.getText().toString());
                user.removeIngredient(ingredientData);
                errorText.setText("");
            } catch (Exception e) {
                errorText.setText(e.getMessage());
            }

            ingredientName.setText("");
            ingredientQuantity.setText("");
            caloriesAmt.setText("");
            expiration.setText("");
        });

        returnButton.setOnClickListener(v -> {
            Intent intent = new Intent(FormActivity.this, IngredientsActivity.class);
            startActivity(intent);
        });

        enterButton.setOnClickListener(v -> {
            try {
                IngredientData ingredientData =
                        new IngredientData(ingredientName.getText().toString(),
                        caloriesAmt.getText().toString());
                int count = Integer.parseInt(ingredientQuantity.getText().toString());
                if (count <= 0) {
                    throw new IllegalArgumentException("The count needs to be positive");
                }
                user.setIngredient(ingredientData, count);
                FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference reference = database.getReference(user.getUserData().getUser());
                reference.setValue(user);
                errorText.setText("");
            } catch (NumberFormatException n) {
                errorText.setText("The count must be a number");
            } catch (Exception e) {
                errorText.setText(e.getMessage());
            }

            ingredientName.setText("");
            ingredientQuantity.setText("");
            caloriesAmt.setText("");
            expiration.setText("");
        });


    }
}