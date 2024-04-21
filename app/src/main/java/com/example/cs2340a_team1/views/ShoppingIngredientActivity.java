package com.example.cs2340a_team1.views;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.cs2340a_team1.model.FirebaseUtil;
import com.example.cs2340a_team1.model.IngredientData;

import com.example.cs2340a_team1.R;
import com.example.cs2340a_team1.viewmodels.UserViewModel;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ShoppingIngredientActivity extends AppCompatActivity {

    private TextView errorText;
    private UserViewModel user = UserViewModel.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping_ingredient_activity);
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference reference = database.getReference(user.getUserData().getUser());
        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                FirebaseUtil.loadFromFirebase(snapshot);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

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
                user.getUserData().removeFromShoppingList(ingredientData.getIngredientName(), 0);
                reference.setValue(user);
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
            Intent intent = new Intent(ShoppingIngredientActivity.this, ShoppingList.class);
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
                user.getUserData().addToShoppingList(ingredientName.getText().toString(), count);
                FirebaseDatabase database1 = FirebaseDatabase.getInstance();
                DatabaseReference ref2 = database1.getReference(user.getUserData().getUser());
                ref2.setValue(user);
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