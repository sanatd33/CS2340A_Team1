package com.example.cs2340a_team1.views;

import android.content.Intent;
import android.os.Bundle;
import android.util.Pair;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.cs2340a_team1.R;
import com.example.cs2340a_team1.model.FirebaseUtil;
import com.example.cs2340a_team1.model.IngredientData;
import com.example.cs2340a_team1.viewmodels.ShoppingViewModel;
import com.example.cs2340a_team1.viewmodels.UserViewModel;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.atomic.AtomicBoolean;


public class ShoppingList extends AppCompatActivity {
    private ShoppingViewModel viewModel;

    private UserViewModel user = UserViewModel.getInstance();
    private TextView ingredientList;

    private ArrayList<IngredientData> buying = new ArrayList<>();



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
        Button buyButton = findViewById(R.id.buyBtn);

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

        buyButton.setOnClickListener(v -> {
            UserViewModel user = UserViewModel.getInstance();
            for (IngredientData ing : buying) {
                int count = user.getUserData().getShoppingList().get(ing.getIngredientName()).second;
                user.removeShopping(ing);
                user.addIngredient(ing, count);
            }

            DatabaseReference ref = FirebaseDatabase.getInstance().getReference(user.getUserData().getUser());
            ref.setValue(user);


            TableLayout btnContainer = findViewById(R.id.ingredientBtnContainer);
            LinearLayout checkboxContainer = findViewById(R.id.checkboxContainer);
            btnContainer.removeAllViews();
            checkboxContainer.removeAllViews();
            String list = "";
            for (String name : user.getUserData().getShoppingList().keySet()) {
                IngredientData ing = user.getUserData().getShoppingList().get(name).first;
                int count = user.getUserData().getShoppingList().get(name).second;
                list += name + "\t\t-\t\t" + count + "\n";

                Button add = new Button(getApplicationContext());
                add.setText("+");
                add.setOnClickListener(x -> {
                    int newCount = user.getUserData().getShoppingList().
                            get(ing.getIngredientName()).second + 1;
                    System.out.println("making " + ing.getIngredientName() + ": " +
                            user.getUserData().getShoppingList()
                                    .get(ing.getIngredientName()).second + "->" + newCount);
                    user.setShopping(ing,
                            newCount);
                    if (newCount <= 0) {
                        user.removeShopping(ing);
                    }
                    updateList();
                    FirebaseDatabase database = FirebaseDatabase.getInstance();
                    DatabaseReference reference =
                            database.getReference(user.getUserData().getUser());
                    reference.setValue(user);
                });

                Button subtract = new Button(getApplicationContext());
                subtract.setText("-");
                subtract.setOnClickListener(x -> {
                    int newCount = user.getUserData().getShoppingList().
                            get(ing.getIngredientName()).second - 1;
                    System.out.println("making " + ing.getIngredientName() + ": " +
                            user.getUserData().getShoppingList()
                                    .get(ing.getIngredientName()).second + "->" + newCount);
                    user.setShopping(ing,
                            newCount);
                    if (newCount <= 0) {
                        user.removeShopping(ing);
                        add.setVisibility(View.INVISIBLE);
                        subtract.setVisibility(View.INVISIBLE);
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

                CheckBox box = new CheckBox(getApplicationContext());
                box.setOnCheckedChangeListener((buttonView, isChecked) -> {
                    if (isChecked) {
                        buying.add(ing);
                    }
                });

                checkboxContainer.addView(box);
            }
            ingredientList.setText(list);
        });

    }

    @Override
    protected void onStart() {
        super.onStart();

        UserViewModel model = UserViewModel.getInstance();

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference ref1 = database.getReference(user.getUserData().getUser());

        ref1.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    FirebaseUtil.loadFromFirebase(snapshot);
                    System.out.println(user.getUserData().getShoppingList());
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


        DatabaseReference reference = database.getReference(user.getUserData().getUser()
                + "/userData/shoppingList");
        TableLayout btnContainer = findViewById(R.id.ingredientBtnContainer);
        LinearLayout checkboxContainer = findViewById(R.id.checkboxContainer);
        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    HashMap<String, Object> map = (HashMap<String, Object>) snapshot.getValue();
                    String list = "";
                    model.getUserData().clearShopping();
                    for (String name : map.keySet()) {
                        AtomicBoolean deleted = new AtomicBoolean(false);
                        HashMap<String, Object> map2 =
                                (HashMap<String, Object>) map.get(name);

                        HashMap<String, String> map3 =
                                (HashMap<String, String>) map2.get("first");

                        IngredientData ing = new IngredientData(map3.get("ingredientName"),
                                map3.get("calories"));
                        int count = Math.toIntExact((Long) map2.get("second"));
                        list += name + "\t\t-\t\t" + count + "\n";
                        model.setShopping(ing, count);

                        Button add = new Button(getApplicationContext());
                        add.setText("+");
                        add.setOnClickListener(v -> {
                            int newCount = user.getUserData().getShoppingList().
                                    get(ing.getIngredientName()).second + 1;
                            System.out.println("making " + ing.getIngredientName() + ": " +
                                    user.getUserData().getShoppingList()
                                            .get(ing.getIngredientName()).second + "->" + newCount);
                            user.setShopping(ing,
                                    newCount);
                            if (newCount <= 0) {
                                user.removeShopping(ing);
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
                            int newCount = user.getUserData().getShoppingList().
                                    get(ing.getIngredientName()).second - 1;
                            System.out.println("making " + ing.getIngredientName() + ": " +
                                    user.getUserData().getShoppingList()
                                            .get(ing.getIngredientName()).second + "->" + newCount);
                            user.setShopping(ing,
                                    newCount);
                            if (newCount <= 0) {
                                user.removeShopping(ing);
                                deleted.set(true);
                                add.setVisibility(View.INVISIBLE);
                                subtract.setVisibility(View.INVISIBLE);
                            }
                            updateList();
                            FirebaseDatabase database = FirebaseDatabase.getInstance();
                            DatabaseReference reference =
                                    database.getReference(user.getUserData().getUser());
                            reference.setValue(user);
                        });

                        if (!deleted.get()) {
                            TableRow row = new TableRow(getApplicationContext());
                            row.addView(subtract);
                            row.addView(add);
                            btnContainer.addView(row);
                        }

                        CheckBox box = new CheckBox(getApplicationContext());
                        box.setOnCheckedChangeListener((buttonView, isChecked) -> {
                            if (isChecked) {
                                buying.add(ing);
                            }
                        });

                        checkboxContainer.addView(box);
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
        HashMap<String, Pair<IngredientData, Integer>> ingredients =
                model.getUserData().getShoppingList();
        for (String s : ingredients.keySet()) {
            int count = ingredients.get(s).second;
            list += s + "\t\t-\t\t" + count + "\n";
        }
        ingredientList.setText(list);
    }

}
