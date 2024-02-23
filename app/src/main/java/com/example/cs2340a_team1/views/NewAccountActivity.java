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
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.w3c.dom.Text;


public class NewAccountActivity extends AppCompatActivity {

    private EditText usernameText;
    private EditText passwordText;
    private Button createAccount;
    private TextView errorText;

    private String username;
    private String password;
    private String confirmPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_account);

        usernameText = findViewById(R.id.username);
        passwordText = findViewById(R.id.password);
        createAccount = findViewById(R.id.create_acct_button);
        errorText = findViewById(R.id.errorText);

        usernameText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}
            @Override
            public void afterTextChanged(Editable editable) {}

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
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}
            @Override
            public void afterTextChanged(Editable editable) {}

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
            if (errorText.getText().length() == 0) {
                FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference ref = database.getReference(username);
                ref.setValue(password);
                username = "";
                password = "";
                Intent intent = new Intent(NewAccountActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });
    }
}
