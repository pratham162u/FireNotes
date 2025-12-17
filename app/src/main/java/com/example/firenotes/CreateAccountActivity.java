package com.example.firenotes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

public class CreateAccountActivity extends AppCompatActivity {

    EditText emailId, passwordId, confirmPasswordId;
    Button createAccountBtn;
    ProgressBar progressBar;
    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);

        emailId = findViewById(R.id.email_edit_text);
        passwordId = findViewById(R.id.password_edit_text);
        confirmPasswordId = findViewById(R.id.confirm_password_edit_text);
        createAccountBtn = findViewById(R.id.create_account_button);
        progressBar = findViewById(R.id.progress_bar);

        firebaseAuth = FirebaseAuth.getInstance();

        createAccountBtn.setOnClickListener(v -> createAccount());
    }

    void createAccount() {
        String email = emailId.getText().toString().trim();
        String password = passwordId.getText().toString().trim();
        String confirmPassword = confirmPasswordId.getText().toString().trim();

        if (!validateData(email, password, confirmPassword)) return;

        createAccountInFirebase(email, password);
    }

    boolean validateData(String email, String password, String confirmPassword) {
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            emailId.setError("Invalid Email!");
            return false;
        }
        if (password.length() < 6) {
            passwordId.setError("Password too short (min 6 chars)");
            return false;
        }
        if (!password.equals(confirmPassword)) {
            confirmPasswordId.setError("Passwords do not match!");
            return false;
        }
        return true;
    }

    void createAccountInFirebase(String email, String password) {
        changeInProgress(true);

        firebaseAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(task -> {
                    changeInProgress(false);
                    if (task.isSuccessful() && firebaseAuth.getCurrentUser() != null) {
                        sendVerificationEmail();
                    } else {
                        String errorMsg = task.getException() != null ? task.getException().getLocalizedMessage() : "Unknown error";
                        Toast.makeText(CreateAccountActivity.this, "Account creation failed: " + errorMsg, Toast.LENGTH_LONG).show();
                        task.getException().printStackTrace(); // logcat me error
                    }
                });
    }

    void sendVerificationEmail() {
        if (firebaseAuth.getCurrentUser() == null) return;

        firebaseAuth.getCurrentUser().sendEmailVerification()
                .addOnCompleteListener(mailTask -> {
                    if (mailTask.isSuccessful()) {
                        Toast.makeText(CreateAccountActivity.this,
                                "Account created! Please check your email to verify before login.",
                                Toast.LENGTH_LONG).show();
                        firebaseAuth.signOut();
                        finish(); // Back to login
                    }
                })
                .addOnFailureListener(e -> {
                    Toast.makeText(CreateAccountActivity.this,
                            "Failed to send verification email: " + e.getLocalizedMessage(),
                            Toast.LENGTH_LONG).show();
                    e.printStackTrace(); // logcat me full reason
                });
    }

    void changeInProgress(boolean inProgress) {
        if (inProgress) {
            progressBar.setVisibility(View.VISIBLE);
            createAccountBtn.setVisibility(View.GONE);
        } else {
            progressBar.setVisibility(View.GONE);
            createAccountBtn.setVisibility(View.VISIBLE);
        }
    }
}
