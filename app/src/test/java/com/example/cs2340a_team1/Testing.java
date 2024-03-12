package com.example.cs2340a_team1;

import org.junit.Test;

import static org.junit.Assert.*;

import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.cs2340a_team1.model.MealData;
import com.example.cs2340a_team1.model.UserData;
import com.example.cs2340a_team1.viewmodels.UserViewModel;
import com.example.cs2340a_team1.views.LoginActivity;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class Testing {
    @Test
    public void nullChecker() { //detect null inputs
        UserViewModel user = new UserViewModel();
        user.updateUser(null);
        user.updatePass(null);
        UserData data = user.getUserData();
        String username = data.getUser();
        String password = data.getPass();

        assertNull(password);
        assertNull(username);
    }
    @Test
    public void spaceChecker() { //detect space inputs
        boolean condition = false; // True if detect empty space for both password and username
        UserViewModel user = new UserViewModel();
        user.updateUser("Sami Moussa");
        user.updatePass("12 34");
        UserData data = user.getUserData();
        String username = data.getUser();
        String password = data.getPass();

        if (username.contains(" ") && password.contains(" ")) {
            condition = true;
        }
        assertTrue(condition);
    }
    @Test
    public void emptyChecker() {//detect empty inputs
        UserViewModel user = new UserViewModel();
        user.updateUser("");
        user.updatePass("");
        UserData data = user.getUserData();
        String username = data.getUser();
        String password = data.getPass();
        assertEquals("", username);
        assertEquals("", password);
    }

    @Test
    public void mealInputCorrectly() { //Checks if meals were added to a user
        UserViewModel user = new UserViewModel();
        user.updateUser("tester");
        user.updatePass("tester");
        user.setMeals(new MealData("banana", "40"));
        assertEquals(user.getUserData().getMealData("banana").getMealName(), "banana");
        assertEquals(user.getUserData().getMealData("banana").getCalorieAmt(), 40);
    }
}