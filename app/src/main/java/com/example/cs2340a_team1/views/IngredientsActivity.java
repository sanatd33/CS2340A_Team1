package com.example.cs2340a_team1.views;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Pair;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.example.cs2340a_team1.model.IngredientData;

import com.example.cs2340a_team1.R;
import com.example.cs2340a_team1.viewmodels.Update;
import com.example.cs2340a_team1.viewmodels.UserViewModel;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

public class IngredientsActivity extends AppCompatActivity {
    private String mealName;
    private int calorieAmt;
    private TextView errorText;
    private TextView personalInfoText;
    private UserViewModel user = UserViewModel.getInstance();

    private TextView ingredientList;
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
        ingredientList = findViewById(R.id.ingredientList);

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

    @Override
    protected void onStart() {
        super.onStart();

        UserViewModel model = UserViewModel.getInstance();

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference reference = database.getReference(user.getUserData().getUser()
                + "/userData/ingredients");
        TableLayout btnContainer = findViewById(R.id.ingredientBtnContainer);
        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    HashMap<String, Object> map = (HashMap<String, Object>) snapshot.getValue();
                    String list = "";
                    model.getUserData().clearIngredients();
                    for (String name : map.keySet()) {
                        HashMap<String, Object> map2 =
                                (HashMap<String, Object>) map.get(name);

                        HashMap<String, String> map3 =
                                (HashMap<String, String>) map2.get("first");

                        IngredientData ing = new IngredientData(map3.get("ingredientName"),
                                map3.get("calories"));
                        int count = Math.toIntExact((Long) map2.get("second"));
                        list += name + "\t\t-\t\t" + count + "\n";
                        model.setIngredient(ing, count);

                        Button add = new Button(getApplicationContext());
                        add.setText("+");
                        add.setOnClickListener(v -> {
                            int newCount = user.getUserData().getIngredients().
                                    get(ing.getIngredientName()).second + 1;
                            user.updateIngredient(ing, newCount);
                            if (newCount <= 0) {
                                user.removeIngredient(ing);
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
                            int newCount = user.getUserData().getIngredients().
                                    get(ing.getIngredientName()).second - 1;
                            user.updateIngredient(ing,
                                     newCount);
                            if (newCount <= 0) {
                                user.removeIngredient(ing);
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
        String list = Update.updateList(user.getUserData().getIngredients());
        ingredientList.setText(list);
    }
}