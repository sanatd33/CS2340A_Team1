package com.example.cs2340a_team1.views;

import android.content.Intent;
import android.os.Bundle;
import android.util.Pair;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.cs2340a_team1.R;
import com.example.cs2340a_team1.model.IngredientData;
import com.example.cs2340a_team1.model.UserData;
import com.example.cs2340a_team1.viewmodels.ShoppingViewModel;
import com.example.cs2340a_team1.viewmodels.UserViewModel;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;


public class ShoppingList extends AppCompatActivity {
    private ShoppingViewModel viewModel;

    private UserViewModel user = UserViewModel.getInstance();
    private TextView ingredientList;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.shoppingscreen);
        Button toInputMealScreenButton = findViewById(R.id.ToMeal);
        Button toRecipeScreenButton = findViewById(R.id.ToRecipe);
        Button toIngredientScreenButton = findViewById(R.id.toIngredient);
        Button toHomeScreenButton = findViewById(R.id.toHomeScreenButton);
        Button toPersonalInfoScreenButton = findViewById(R.id.toPersonalInfoScreenButton);
        Button toFormButton = findViewById(R.id.toFormButton);
        ingredientList = findViewById(R.id.ingredientList);

        // The new code should go here
        
        // Buttons
        toInputMealScreenButton.setOnClickListener(v -> {
            Intent intent = new Intent(ShoppingList.this, InputMealActivity.class);
            startActivity(intent);
        });
        toRecipeScreenButton.setOnClickListener(v -> {
            Intent intent = new Intent(ShoppingList.this, RecipeActivity.class);
            startActivity(intent);
        });

        toIngredientScreenButton.setOnClickListener(v -> {
            Intent intent = new Intent(ShoppingList.this, IngredientsActivity.class);
            startActivity(intent);
        });

        toHomeScreenButton.setOnClickListener(v -> {
            Intent intent = new Intent(ShoppingList.this, HomeActivity.class);
            startActivity(intent);
        });

        toPersonalInfoScreenButton.setOnClickListener(v -> {
            Intent intent = new Intent(ShoppingList.this, PersonalInfoActivity.class);
            startActivity(intent);
        });

        toFormButton.setOnClickListener(v -> {
            Intent intent = new Intent(ShoppingList.this, ShoppingIngredientActivity.class);
            startActivity(intent);
        });

    }

    @Override
    protected void onStart() {
        super.onStart();

        UserViewModel model = UserViewModel.getInstance();

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference reference = database.getReference(user.getUserData().getUser()
                + "/userData/shoppingList");
        TableLayout btnContainer = findViewById(R.id.ingredientBtnContainer);
        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    HashMap<String, Object> map = (HashMap<String, Object>) snapshot.getValue();
                    String list = "";
                    model.getUserData().clearIngredients();
                    for (String name : map.keySet()) {
                        IngredientData ing = new IngredientData(name, "1");
                        int count = Math.toIntExact((Long) map.get(name));
                        list += name + "\t\t-\t\t" + count + "\n";
                        model.getUserData().addToShoppingList(name, count);

                        Button add = new Button(getApplicationContext());
                        add.setText("+");
                        add.setOnClickListener(v -> {
                            int newCount = user.getUserData().getShoppingList().get(name) + 1;
                            user.getUserData().addToShoppingList(ing.getIngredientName(),
                                    newCount);
                            if (newCount <= 0) {
                                user.getUserData().removeFromShoppingList(ing.getIngredientName(),
                                        newCount);
                            }
                            updateList();
                            FirebaseDatabase database = FirebaseDatabase.getInstance();
                            DatabaseReference reference =
                                    database.getReference(user.getUserData().getUser());
                            reference.setValue(user);
                        });

                        Button subtract = new Button(getApplicationContext());
                        subtract.setText("-");
                        subtract.setOnClickListener(v -> {
                            int newCount = user.getUserData().getShoppingList().get(name) - 1;
                            user.getUserData().addToShoppingList(ing.getIngredientName(),
                                    newCount);
                            if (newCount <= 0) {
                                user.getUserData().removeFromShoppingList(ing.getIngredientName(),
                                        newCount);
                                subtract.setVisibility(View.INVISIBLE);
                                add.setVisibility(View.INVISIBLE);
                            }
                            updateList();
                            FirebaseDatabase database = FirebaseDatabase.getInstance();
                            DatabaseReference reference =
                                    database.getReference(user.getUserData().getUser());
                            reference.setValue(user);
                        });

                        TableRow row = new TableRow(getApplicationContext());
                        row.addView(subtract);
                        row.addView(add);
                        btnContainer.addView(row);
                    }
                    ingredientList.setText(list);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    //Implementation 3 will likely need to be worked on here - delete afterwards

    private void updateList() {
        String list = "";
        UserViewModel model = UserViewModel.getInstance();
        HashMap<String, Integer> ingredients =
                model.getUserData().getShoppingList();
        for (String s : ingredients.keySet()) {
            int count = ingredients.get(s);
            list += s + "\t\t-\t\t" + count + "\n";
        }
        ingredientList.setText(list);
    }

}
