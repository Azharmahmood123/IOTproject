package com.fyp.iot.project;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Patterns;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {

    private EditText etEmail;
    private EditText etPassword;

    private Button btnLogin;
    private boolean isEmail = false, isPassword = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        if (Utils.getStringPrefs(Utils.PrefConstants.PREF_USER_EMAIL).length() > 0) {
            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        }

        findViews();
        configure();
    }

    private void findViews() {
        etEmail = findViewById(R.id.etEmail);
        etPassword = findViewById(R.id.etPassword);
        btnLogin = findViewById(R.id.btnLogin);
    }

    private void configure() {

        etEmail.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.toString().isEmpty()) {
                    isEmail = false;
                    etEmail.setError("Email cannot be empty");
                } else if (!Patterns.EMAIL_ADDRESS.matcher(s.toString()).matches()) {
                    isEmail = false;
                    etEmail.setError("Not a valid email");
                } else {
                    isEmail = true;
                    etEmail.setError(null);
                }
                checkSignInButton();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        etPassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.toString().isEmpty()) {
                    isPassword = false;
                    etPassword.setError("Password cannot be empty");
                } else if (s.toString().length() < 5) {
                    isPassword = false;
                    etPassword.setError("Password must be >5 characters");
                } else {
                    isPassword = true;
                    etPassword.setError(null);
                }
                checkSignInButton();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        btnLogin.setOnClickListener(v -> {
            Utils.setPrefs(Utils.PrefConstants.PREF_USER_EMAIL, etEmail.getText().toString().trim());
            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        });
    }

    private void checkSignInButton() {
        btnLogin.setEnabled(isEmail && isPassword);
    }
}