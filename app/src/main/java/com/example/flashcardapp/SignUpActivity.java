package com.example.flashcardapp;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

public class SignUpActivity extends AppCompatActivity {

    EditText name, phone, email, password, confirmPassword;
    MaterialButton signupBtn;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        name = findViewById(R.id.name);
        phone = findViewById(R.id.phone);
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        confirmPassword = findViewById(R.id.confirmPassword);
        signupBtn = findViewById(R.id.signupBtn);

        sharedPreferences = getSharedPreferences("FlashPrefs", MODE_PRIVATE);

        signupBtn.setOnClickListener(v -> {
            String userName = name.getText().toString().trim();
            String userPhone = phone.getText().toString().trim();
            String userEmail = email.getText().toString().trim();
            String userPass = password.getText().toString().trim();
            String userConfirmPass = confirmPassword.getText().toString().trim();

            // Empty fields check
            if(userName.isEmpty() || userPhone.isEmpty() || userEmail.isEmpty() ||
                    userPass.isEmpty() || userConfirmPass.isEmpty()) {
                Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show();
                return;
            }

            // Password match check
            if(!userPass.equals(userConfirmPass)) {
                Toast.makeText(this, "Passwords do not match", Toast.LENGTH_SHORT).show();
                return;
            }

            // Save to SharedPreferences
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString("name", userName);
            editor.putString("phone", userPhone);
            editor.putString("email", userEmail);
            editor.putString("password", userPass);
            editor.apply();

            Toast.makeText(this, "Sign Up Successful", Toast.LENGTH_SHORT).show();
            finish();
        });
    }
}
