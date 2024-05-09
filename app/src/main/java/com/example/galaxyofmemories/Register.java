package com.example.galaxyofmemories;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseAuthWeakPasswordException;
import com.google.firebase.auth.UserProfileChangeRequest;

public class Register extends AppCompatActivity {

    EditText username, registerEmail, registerPassword, registerConfirmPassword;
    Button registerButton;
    FirebaseAuth mAuth;

    TextView loginText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mAuth = FirebaseAuth.getInstance();

        username = findViewById(R.id.username);
        registerEmail = findViewById(R.id.email);
        registerPassword = findViewById(R.id.password);
        registerConfirmPassword = findViewById(R.id.confirm_password);
        registerButton = findViewById(R.id.signupBtn);
        loginText = findViewById(R.id.signupText);

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String name = username.getText().toString().trim();
                final String email = registerEmail.getText().toString().trim();
                final String password = registerPassword.getText().toString().trim();
                String confirmPassword = registerConfirmPassword.getText().toString().trim();

                if (name.isEmpty() || email.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
                    Toast.makeText(Register.this, "All fields are required", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (!password.equals(confirmPassword)) {
                    Toast.makeText(Register.this, "Passwords do not match", Toast.LENGTH_SHORT).show();
                    return;
                }

                mAuth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener(Register.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    sendEmailVerification(name, email);
                                } else {
                                    try {
                                        throw task.getException();
                                    } catch (FirebaseAuthWeakPasswordException e) {
                                        Toast.makeText(Register.this, "Weak Password", Toast.LENGTH_SHORT).show();
                                    } catch (FirebaseAuthInvalidCredentialsException e) {
                                        Toast.makeText(Register.this, "Invalid Email", Toast.LENGTH_SHORT).show();
                                    } catch (FirebaseAuthUserCollisionException e) {
                                        Toast.makeText(Register.this, "User with this email already exists", Toast.LENGTH_SHORT).show();
                                    } catch (Exception e) {
                                        Log.e("FirebaseError", "Error: " + e.getMessage());
                                        Toast.makeText(Register.this, "Error: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                                    }
                                }
                            }
                        });
            }
        });
        loginText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Register.this, Login.class));
            }
        });
    }

    private void sendEmailVerification(final String name, final String email) {
        mAuth.getCurrentUser().sendEmailVerification()
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(Register.this, "Verification email sent to " + mAuth.getCurrentUser().getEmail(), Toast.LENGTH_SHORT).show();

                            UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest.Builder()
                                    .setDisplayName(name)
                                    .build();
                            mAuth.getCurrentUser().updateProfile(profileUpdates);
                            Intent intent = new Intent(Register.this, Login.class);
                            startActivity(intent);
                        } else {
                            Log.e("EmailVerification", "sendEmailVerification", task.getException());
                            Toast.makeText(Register.this, "Failed to send verification email", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
}