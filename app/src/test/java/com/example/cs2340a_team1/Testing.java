package com.example.cs2340a_team1;

import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

import android.content.ContentProvider;
import android.content.Intent;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.cs2340a_team1.model.FirebaseUtil;
import com.example.cs2340a_team1.model.MealData;
import com.example.cs2340a_team1.model.UserData;
import com.example.cs2340a_team1.viewmodels.ShoppingViewModel;
import com.example.cs2340a_team1.viewmodels.UserViewModel;
import com.example.cs2340a_team1.views.HomeActivity;
import com.example.cs2340a_team1.views.LoginActivity;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class Testing {
    @Test public void checkValidUser() {
        //when user is valid
        UserViewModel user1 = UserViewModel.getInstance();
        user1.updateUser("Testing");
        UserData data1 = user1.getUserData();
        assertEquals("Testing", data1.getUser());
    }

    @Test
    public void checkUserWhitespace(){
        //when user is not valid
        UserViewModel user2 = UserViewModel.getInstance();
        user2.updateUser("Testing");
        user2.updateUser("White Space"); //there is a space in username
        UserData data2 = user2.getUserData();
        assertEquals("Testing", data2.getUser());
    }
    @Test
    public void checkUserEmpty(){
        //when user is not valid
        //Lastly when user doesn't input anything
        UserViewModel user3 = UserViewModel.getInstance();
        user3.updateUser("Testing");
        user3.updateUser(""); //there is a space in username
        UserData data3 = user3.getUserData();
        assertEquals("Testing", data3.getUser());
    }

    @Test
    public void checkValidPass() {
        //when user is valid
        UserViewModel user1 = UserViewModel.getInstance();
        user1.updatePass("1234");
        UserData data1 = user1.getUserData();
        assertEquals("1234", data1.getPass());
    }
    @Test
    public void checkNullPass() {
        //when user is not valid
        UserViewModel user2 = UserViewModel.getInstance();
        user2.updatePass("1234");
        user2.updatePass(null); //password is set to null
        UserData data2 = user2.getUserData();
        assertEquals("1234", data2.getPass());
    }

    @Test
    public void checkPassEmpty() {
        //Lastly when user doesn't input anything
        UserViewModel user3 = UserViewModel.getInstance();
        user3.updatePass("1234");
        user3.updatePass(""); //password is set to null
        UserData data3 = user3.getUserData();
        assertEquals("1234", data3.getPass());
    }

    @Test
    public void mealInputCorrectly() { //Checks if meals were added to a user
        UserViewModel user = UserViewModel.getInstance();
        user.updateUser("tester");
        user.updatePass("tester");
        user.setMeals(new MealData("banana", "40"));
        assertEquals(user.getUserData().getMealData("banana").getMealName(), "banana");
        assertEquals(user.getUserData().getMealData("banana").getCalorieAmt(), 40);
    }



    @Test
    public void heightChecker() {//detect if inputted personal info is updated correctly in user data
        UserViewModel user = UserViewModel.getInstance();
        user.updateHeight(180);
        UserData data = user.getUserData();
        int height = data.getHeight();
        assertEquals(180, height);
    }

    @Test
    public void weightChecker() {//detect if inputted personal info is updated correctly in user data
        UserViewModel user = UserViewModel.getInstance();
        user.updateWeight(150);
        UserData data = user.getUserData();
        int weight = data.getWeight();
        assertEquals(150, weight);
    }

    @Test
    public void genderChecker() {//detect if inputted personal info is updated correctly in user data
        UserViewModel user = UserViewModel.getInstance();
        user.updateGender("Male");
        UserData data = user.getUserData();
        String gender = data.getGender();
        assertEquals("Male", gender);
    }

    @Test
    public void ageChecker() {//detect if inputted personal info is updated correctly in user data
        UserViewModel user = UserViewModel.getInstance();
        user.updateAge(17);
        UserData data = user.getUserData();
        int age = data.getAge();
        assertEquals(17, age);
    }

    @Test
    public void validAgeChecker() {//detect if inputted personal info is updated correctly in user data
        UserViewModel user = UserViewModel.getInstance();
        user.updateAge(30);
        user.updateAge(-17);
        UserData data = user.getUserData();
        int age = data.getAge();
        assertEquals(30, age);
    }
}