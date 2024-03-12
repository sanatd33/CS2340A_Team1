package com.example.cs2340a_team1;

import org.junit.Test;

import static org.junit.Assert.*;

import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

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
    @Test public void checkValidUser() {
        //when user is valid
        UserViewModel user1 = new UserViewModel();
        user1.updateUser("Testing");
        user1.updatePass("1234");
        UserData data1 = user1.getUserData();
        boolean usernameValid1 = data1.isUsernameValid(data1.getUser());
        boolean passwordValid1 = data1.isPasswordValid(data1.getPass());
        //expected to be valid (true)
        assertTrue(usernameValid1);
        assertTrue(passwordValid1);

        //when user is not valid
        UserViewModel user2 = new UserViewModel();
        user2.updateUser("White Space"); //there is a space in username
        user2.updatePass(null); //password is set to null
        UserData data2 = user2.getUserData();
        boolean usernameValid2 = data2.isUsernameValid(data2.getUser());
        boolean passwordValid2 = data2.isPasswordValid(data2.getPass());
        //expected to not be valid (false)
        assertFalse(usernameValid2);
        assertFalse(passwordValid2);

        //Lastly when user doesn't input anything
        UserViewModel user3 = new UserViewModel();
        UserData data3 = user3.getUserData();
        boolean usernameValid3 = data3.isUsernameValid(data3.getUser());
        boolean passwordValid3 = data3.isPasswordValid(data3.getPass());
        //expected to not be valid (false)
        assertFalse(usernameValid3);
        assertFalse(passwordValid3);
    }

    @Test
    public void mealInputCorrectly() { //Checks if meals were added to a user
        UserViewModel user = new UserViewModel();
        user.updateUser("tester");
        user.updatePass("tester");
        user.setMeals("banana", 40);
        assertEquals(user.getUserData().getMealData("banana").getMealName(), "banana");
        assertEquals(user.getUserData().getMealData("banana").getCalorieAmt(), 40);
    }
}