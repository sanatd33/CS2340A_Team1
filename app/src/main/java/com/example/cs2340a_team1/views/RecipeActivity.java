package com.example.cs2340a_team1.views;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.cs2340a_team1.R;
import com.example.cs2340a_team1.model.RecipeData;
import com.example.cs2340a_team1.viewmodels.CookbookViewModel;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class RecipeActivity extends AppCompatActivity {
    private TextView recipeList;
    private HashMap<String, Integer> recipes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_screen);
        Button toInputMealScreenButton = findViewById(R.id.toInputMealScreenButton);
        Button toHomeScreenButton = findViewById(R.id.toHomeScreenButton);
        Button toIngredientScreenButton = findViewById(R.id.toRecipeScreenButton);
        Button toShoppingListScreenButton = findViewById(R.id.toShoppingListScreenButton);
        Button toPersonalInfoScreenButton = findViewById(R.id.toPersonalInfoScreenButton);
        Button newRecipeButton = findViewById(R.id.newRecipeBtn);
        Button sortAlphaButton = findViewById(R.id.sortAlphaBtn);
        Button sortQuantButton = findViewById(R.id.sortQuantBtn);
        recipeList = findViewById(R.id.recipeList);
        recipes = new HashMap<>();

        toInputMealScreenButton.setOnClickListener(v -> {
            Intent intent = new Intent(RecipeActivity.this, InputMealActivity.class);
            startActivity(intent);
        });

        toShoppingListScreenButton.setOnClickListener(v -> {
            Intent intent = new Intent(RecipeActivity.this, ShoppingList.class);
            startActivity(intent);
        });

        toHomeScreenButton.setOnClickListener(v -> {
            Intent intent = new Intent(RecipeActivity.this, HomeActivity.class);
            startActivity(intent);
        });
        toIngredientScreenButton.setOnClickListener(v -> {
            Intent intent = new Intent(RecipeActivity.this, IngredientsActivity.class);
            startActivity(intent);
        });
        toPersonalInfoScreenButton.setOnClickListener(v -> {
            Intent intent = new Intent(RecipeActivity.this, PersonalInfoActivity.class);
            startActivity(intent);
        });
        newRecipeButton.setOnClickListener(v -> {
            Intent intent = new Intent(RecipeActivity.this, NewRecipeActivity.class);
            startActivity(intent);
        });

        sortAlphaButton.setOnClickListener(v -> {
            Set<String> names = recipes.keySet();
            names = names.stream().sorted().collect(Collectors.toCollection(LinkedHashSet::new));
            String list = "";
            for (String name : names) {
                list += name + "\t\t-\t\t" + recipes.get(name) + "\n";
            }
            recipeList.setText(list);
        });

        sortQuantButton.setOnClickListener(v -> {
            Set<String> names = recipes.keySet();
            names = names.stream().sorted((o1, o2) -> recipes.get(o1) - recipes.get(o2))
                    .collect(Collectors.toCollection(LinkedHashSet::new));
            String list = "";
            for (String name : names) {
                list += name + "\t\t-\t\t" + recipes.get(name) + "\n";
            }
            recipeList.setText(list);
        });

    }

    @Override
    protected void onStart() {
        super.onStart();
        CookbookViewModel model = CookbookViewModel.getInstance();

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference reference = database.getReference("recipes");
        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    HashMap<String, Object> map = (HashMap<String, Object>) snapshot.getValue();
                    String list = "";
                    model.getData().clearRecipes();
                    for (String name : map.keySet()) {
                        int quant = 0;
                        RecipeData recipe = new RecipeData();
                        HashMap<String, Long> ingredient = (HashMap<String, Long>) map.get(name);
                        for (String ingName : ingredient.keySet()) {
                            quant += ingredient.get(ingName);
                            recipe.addIngredient(ingName, Math.toIntExact(ingredient.get(ingName)));
                        }
                        model.getData().addRecipe(recipe);

                        list += name + "\t\t-\t\t" + quant + "\n";
                        recipes.put(name, quant);
                    }
                    recipeList.setText(list);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}