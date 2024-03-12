package com.example.cs2340a_team1.views;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.cs2340a_team1.R;
import com.example.cs2340a_team1.model.FirebaseUtil;
import com.example.cs2340a_team1.model.MealData;
import com.example.cs2340a_team1.model.UserData;
import com.example.cs2340a_team1.viewmodels.UserViewModel;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class InputMealActivity extends AppCompatActivity {
    private String mealName;
    private int calorieAmt;
    private TextView errorText;
    private TextView personalInfoText;
    private UserViewModel userViewModel;
    private MealData mealData = new MealData();
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
        errorText = findViewById(R.id.errorTextEdit);
        userViewModel = UserViewModel.getInstance();
        personalInfoText = findViewById(R.id.personalInfoText);


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
                try {
                    mealData.setMealName(charSequence.toString());
                    errorText.setText("");
                } catch (IllegalArgumentException e) {
                    errorText.setText(e.getMessage());
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
                try {
                    mealData.setCalorieAmt(charSequence.toString());
                    errorText.setText("");
                } catch (IllegalArgumentException e) {
                    errorText.setText(e.getMessage());
                }
            }
        });

        submitMealButton.setOnClickListener(v -> {
            if (errorText.length() == 0) {
                FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference ref = database.getReference(userViewModel.getUserData().getUser());
                userViewModel.setMeals(mealData);
                ref.setValue(userViewModel);
                mealName = "";
                calorieAmt = 0;
                updateText();
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        updateText();
    }

    private void updateText() {
        FirebaseDatabase db1 = FirebaseDatabase.getInstance();
        DatabaseReference ref1 = db1.getReference(userViewModel.getUserData().getUser());
        ref1.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    FirebaseUtil.loadFromFirebase(snapshot);
                    if (userViewModel.getUserData().getGender() != null &&
                            !userViewModel.getUserData().getGender().equals("")) {
                        int calorieIntake = 0;
                        System.out.println(userViewModel.getUserData().getMeals().get(0).getClass());
                        for (MealData meal : userViewModel.getUserData().getMeals()) {
                            calorieIntake += meal.getCalorieAmt();
                        }

                        personalInfoText.setText(String.format("Height: %d\n" +
                                        "Weight: %d\n" +
                                        "Gender: %s\n" +
                                        "Calculated Calorie Goal: %d\n" +
                                        "Daily Calorie Intake: %d",
                                userViewModel.getUserData().getHeight(), userViewModel.getUserData().getWeight(),
                                userViewModel.getUserData().getGender(), (int) userViewModel.getUserData().getCalorieGoal(),
                                calorieIntake));
                    } else {
                        personalInfoText.setText("Height: No Data Provided\n" +
                                "Weight: No Data Provided\n" +
                                "Gender: No Data Provided\n" +
                                "Calculated Calorie Goal: No Data Provided\n" +
                                "Daily Calorie Intake: No Data Provided");
                    }
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}
