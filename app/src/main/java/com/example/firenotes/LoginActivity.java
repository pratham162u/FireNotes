package com.example.firenotes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {

    EditText emailId, passwordId;
    Button loginAccountBtn;
    ProgressBar progressBar;
    TextView signUpBtn, forgotPassBtn;
    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        emailId = findViewById(R.id.email_edit_text);
        passwordId = findViewById(R.id.password_edit_text);
        loginAccountBtn = findViewById(R.id.login_button);
        progressBar = findViewById(R.id.progress_bar);
        signUpBtn = findViewById(R.id.signup_text_button);
        forgotPassBtn = findViewById(R.id.forgot_password_text);

        firebaseAuth = FirebaseAuth.getInstance();

        loginAccountBtn.setOnClickListener(v -> loginUser());
        signUpBtn.setOnClickListener(v -> startActivity(new Intent(LoginActivity.this, CreateAccountActivity.class)));
        forgotPassBtn.setOnClickListener(v -> startActivity(new Intent(LoginActivity.this, ForgotPasswordActivity.class)));
    }

    void loginUser() {
        String email = emailId.getText().toString().trim();
        String password = passwordId.getText().toString().trim();

        if (!validateData(email, password)) return;

        loginAccountInFirebase(email, password);
    }

    boolean validateData(String email, String password) {
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            emailId.setError("Email ID is Invalid!");
            return false;
        }
        if (password.length() < 6) {
            passwordId.setError("Password too short (min 6 chars)");
            return false;
        }
        return true;
    }

    void loginAccountInFirebase(String email, String password) {
        changeInProgress(true);

        firebaseAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(task -> {
                    changeInProgress(false);
                    if (task.isSuccessful() && firebaseAuth.getCurrentUser() != null) {
                        if (firebaseAuth.getCurrentUser().isEmailVerified()) {
                            startActivity(new Intent(LoginActivity.this, MainActivity.class));
                            finish();
                        } else {
                            sendVerificationEmailAgain();
                        }
                    } else {
                        String errorMsg = task.getException() != null ? task.getException().getLocalizedMessage() : "Unknown error";
                        Toast.makeText(LoginActivity.this, "Login Failed: " + errorMsg, Toast.LENGTH_LONG).show();
                        if (task.getException() != null) task.getException().printStackTrace();
                    }
                });
    }

    void sendVerificationEmailAgain() {
        if (firebaseAuth.getCurrentUser() == null) return;

        firebaseAuth.getCurrentUser().sendEmailVerification()
                .addOnCompleteListener(mailTask -> {
                    Toast.makeText(LoginActivity.this,
                            "Email not verified! Verification link resent. Check inbox/spam.",
                            Toast.LENGTH_LONG).show();
                })
                .addOnFailureListener(e -> {
                    Toast.makeText(LoginActivity.this,
                            "Failed to send verification email: " + e.getLocalizedMessage(),
                            Toast.LENGTH_LONG).show();
                    e.printStackTrace();
                });
    }

    void changeInProgress(boolean inProgress) {
        if (inProgress) {
            progressBar.setVisibility(View.VISIBLE);
            loginAccountBtn.setVisibility(View.GONE);
        } else {
            progressBar.setVisibility(View.GONE);
            loginAccountBtn.setVisibility(View.VISIBLE);
        }
    }
}
