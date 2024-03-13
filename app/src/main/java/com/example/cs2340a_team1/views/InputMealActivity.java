package com.example.cs2340a_team1.views;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;


import com.example.cs2340a_team1.R;
import com.example.cs2340a_team1.model.FirebaseUtil;
import com.example.cs2340a_team1.model.MealData;
import com.example.cs2340a_team1.viewmodels.UserViewModel;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
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
        Button dailyCalorieButton = findViewById(R.id.calorieLineChartBtn);
        Button mealCalorieButton = findViewById(R.id.caloriePieChartBtn);

        BarChart barChart = findViewById(R.id.barChart);
        PieChart pieChart = findViewById(R.id.pieChart);


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
                DatabaseReference ref =
                        database.getReference(userViewModel.getUserData().getUser());
                userViewModel.setMeals(mealData);
                ref.setValue(userViewModel);
                mealName = "";
                calorieAmt = 0;
                updateText();
            }
        });

        dailyCalorieButton.setOnClickListener(v -> {
            ArrayList<BarEntry> barEntries = new ArrayList<>();
            ArrayList<BarEntry> barEntries2 = new ArrayList<>();
            barEntries.add(new BarEntry(0, (float) userViewModel.getUserData().getCalorieGoal()));
            float calorieIntake = 0;
            for (MealData meal : userViewModel.getUserData().getMeals()) {
                calorieIntake += meal.getCalorieAmt();
            }
            barEntries2.add(new BarEntry(1, calorieIntake));

            BarDataSet dataSet1 = new BarDataSet(barEntries, "Calorie Goal");
            dataSet1.setColor(Color.BLUE);
            BarDataSet dataSet2 = new BarDataSet(barEntries2, "Daily Calories");
            dataSet2.setColor(Color.RED);
            BarData data = new BarData(dataSet1, dataSet2);
            barChart.getAxisLeft().setAxisMinimum(0f);
            barChart.setBackgroundColor(Color.WHITE);
            barChart.setData(data);
        });

        mealCalorieButton.setOnClickListener(v -> {
            ArrayList<PieEntry> pieEntries = new ArrayList<>();
            ArrayList<Integer> colors = new ArrayList<>();
            int i = 0;
            int inc = 255 / userViewModel.getUserData().getMeals().size();
            for (MealData meal : userViewModel.getUserData().getMeals()) {
                pieEntries.add(new PieEntry(meal.getCalorieAmt(), meal.getMealName()));
                colors.add(Color.rgb(255 - i, 0, i));
                i += inc;
            }

            PieDataSet dataset = new PieDataSet(pieEntries, "Meal Calories");
            dataset.setColors(colors);
            PieData data = new PieData(dataset);
            pieChart.setData(data);
            pieChart.setBackgroundColor(Color.WHITE);
            pieChart.setHoleRadius(0);
            pieChart.setVisibility(View.VISIBLE);
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
                    if (userViewModel.getUserData().getGender() != null
                            && !userViewModel.getUserData().getGender().equals("")) {
                        int calorieIntake = 0;
                        for (MealData meal : userViewModel.getUserData().getMeals()) {
                            calorieIntake += meal.getCalorieAmt();
                        }

                        personalInfoText.setText(String.format("Height: %d\n"
                                        + "Weight: %d\n" + "Gender: %s\n"
                                        + "Calculated Calorie Goal: %d\n"
                                        + "Daily Calorie Intake: %d",
                                userViewModel.getUserData().getHeight(),
                                userViewModel.getUserData().getWeight(),
                                userViewModel.getUserData().getGender(),
                                (int) userViewModel.getUserData().getCalorieGoal(),
                                calorieIntake));
                    } else {
                        personalInfoText.setText("Height: No Data Provided\n"
                                + "Weight: No Data Provided\n"
                                + "Gender: No Data Provided\n"
                                + "Calculated Calorie Goal: No Data Provided\n"
                                + "Daily Calorie Intake: No Data Provided");
                    }
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}
