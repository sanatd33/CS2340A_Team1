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
import com.example.cs2340a_team1.viewmodels.LoginViewModel;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class LoginActivity extends AppCompatActivity {

    private EditText usernameText;
    private EditText passwordText;
    private Button loginButton;
    private Button newAccountButton;
    private Button exitButton;
    private TextView errorText;

    private String username;
    private String password;
    private LoginViewModel viewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        usernameText = findViewById(R.id.username);
        passwordText = findViewById(R.id.password);
        loginButton = findViewById(R.id.login_button);
        newAccountButton = findViewById(R.id.new_acct_button);
        errorText = findViewById(R.id.errorText);
        exitButton = findViewById(R.id.exit);

        viewModel = LoginViewModel.getInstance();

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

        loginButton.setOnClickListener(v -> {
            if (errorText.length() == 0) {
                FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference ref = database.getReference(username);
                ref.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if (snapshot.exists()) {
                            if (password.equals(snapshot.getValue())) {
                                viewModel.updateData(username);
                                Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                                startActivity(intent);
                            } else {
                                errorText.setText("The username or password is incorrect");
                            }
                        } else {
                            errorText.setText("The username does not exist");
                        }
                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError error) { }
                });
            }
        });

        newAccountButton.setOnClickListener(v -> {
            Intent intent = new Intent(LoginActivity.this, NewAccountActivity.class);
            startActivity(intent);
        });

        exitButton.setOnClickListener(v -> {
            setResult(RESULT_OK);
            finish();
            System.exit(0);
        });
    }
}
