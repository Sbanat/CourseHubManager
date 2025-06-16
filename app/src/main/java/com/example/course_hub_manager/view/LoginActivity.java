package com.example.course_hub_manager.view;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.course_hub_manager.R;
import com.example.course_hub_manager.databinding.ActivityLoginBinding;
import com.example.course_hub_manager.viewmodel.UserViewModel;

public class LoginActivity extends AppCompatActivity {

    private ActivityLoginBinding binding;
    private UserViewModel userViewModel;
    private SharedPreferences sharedPreferences;

    private static final String PREFS_NAME = "LoginPrefs";
    private static final String PREF_EMAIL = "email";
    private static final String PREF_REMEMBER = "remember";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        userViewModel = new ViewModelProvider(this).get(UserViewModel.class);
        sharedPreferences = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);

        loadPreferences();

        binding.btnLogin.setOnClickListener(v -> loginUser());
    }

    private void loadPreferences() {
        boolean rememberMe = sharedPreferences.getBoolean(PREF_REMEMBER, false);
        if (rememberMe) {
            String savedEmail = sharedPreferences.getString(PREF_EMAIL, "");
            binding.etEmail.setText(savedEmail);
            binding.cbRememberMe.setChecked(true);
        }
    }

    private void loginUser() {
        String email = binding.etEmail.getText().toString().trim();
        String password = binding.etPassword.getText().toString().trim();

        if (email.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "Email and password cannot be empty", Toast.LENGTH_SHORT).show();
            return;
        }

        userViewModel.getUserByEmail(email).observe(this, existingUser -> {
            if (existingUser != null) {
                if (existingUser.getPassword().equals(password)) {
                    // Correct password
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putInt("current_user_id", existingUser.getId()); // Storing user ID
                    editor.apply();

                    Toast.makeText(this, "Login Successful", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(LoginActivity.this, com.example.course_hub_manager.MainActivity.class);
                    startActivity(intent);
                    finish();
                } else {
                    // Incorrect password
                    Toast.makeText(this, "Invalid email or password", Toast.LENGTH_SHORT).show();
                }
            } else {
                // User not found
                Toast.makeText(this, "Invalid email or password", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void savePreferences(String email) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        if (binding.cbRememberMe.isChecked()) {
            editor.putString(PREF_EMAIL, email);
            editor.putBoolean(PREF_REMEMBER, true);
        } else {
            editor.remove(PREF_EMAIL);
            editor.remove(PREF_REMEMBER);
        }
        editor.apply();
    }
}
