package com.example.cs2340a_team1.views;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.cs2340a_team1.R;
import com.example.cs2340a_team1.viewmodels.UserViewModel;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

//High Cohesion
//The variables and objects in this class are highly cohesive
//They do not work on unrelated tasks
//The only job of the class is to create an account using the inputted username and password
public class NewAccountActivity extends AppCompatActivity {

    private EditText usernameText;
    private EditText passwordText;
    private Button createAccount;
    private TextView errorText;

    private String username;
    private String password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_account);

        usernameText = findViewById(R.id.username);
        passwordText = findViewById(R.id.password);
        createAccount = findViewById(R.id.create_acct_button);
        errorText = findViewById(R.id.errorText);
        errorText.setText("The username/password cannot be empty");

        usernameText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) { }
            @Override
            public void afterTextChanged(Editable editable) { }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                username = usernameText.getText().toString();
                if (username.trim().length() == 0) {
                    errorText.setText("The username cannot be empty");
                } else if (username.contains(" ")) {
                    errorText.setText("The username cannot contain spaces");
                } else {
                    errorText.setText("");
                }
            }
        });

        passwordText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) { }
            @Override
            public void afterTextChanged(Editable editable) { }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                password = passwordText.getText().toString();
                if (password.trim().length() == 0) {
                    errorText.setText("The password cannot be empty");
                } else if (password.contains(" ")) {
                    errorText.setText("The password cannot contain spaces");
                } else {
                    errorText.setText("");
                }
            }
        });

        createAccount.setOnClickListener(v -> {
            if (errorText.getText().length() == 0
                    && username.length() != 0 && password.length() != 0) {
                FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference ref = database.getReference(username);
                UserViewModel viewModel = UserViewModel.getInstance();
                viewModel.updateUser(username);
                viewModel.updatePass(password);
                ref.setValue(viewModel);
                username = "";
                password = "";
                Intent intent = new Intent(NewAccountActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });
    }
}
