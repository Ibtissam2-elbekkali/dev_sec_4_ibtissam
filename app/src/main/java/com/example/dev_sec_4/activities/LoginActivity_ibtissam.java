package com.example.dev_sec_4.activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.dev_sec_4.R;
import com.example.dev_sec_4.database.DatabaseManager_ibtissam;
import com.example.dev_sec_4.database.User_ibtissam;
import com.example.dev_sec_4.security.PasswordHasher_ibtissam;

public class LoginActivity_ibtissam extends AppCompatActivity {
    
    private EditText etUsername_ibtissam;
    private EditText etPassword_ibtissam;
    private Button btnLogin_ibtissam;
    private Button btnRegister_ibtissam;
    
    private DatabaseManager_ibtissam databaseManager_ibtissam;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_ibtissam);
        
        databaseManager_ibtissam = new DatabaseManager_ibtissam(this);
        
        etUsername_ibtissam = findViewById(R.id.etUsername_ibtissam);
        etPassword_ibtissam = findViewById(R.id.etPassword_ibtissam);
        btnLogin_ibtissam = findViewById(R.id.btnLogin_ibtissam);
        btnRegister_ibtissam = findViewById(R.id.btnRegister_ibtissam);
        
        btnLogin_ibtissam.setOnClickListener(v_ibtissam -> login_ibtissam());
        btnRegister_ibtissam.setOnClickListener(v_ibtissam -> register_ibtissam());
    }
    
    private void login_ibtissam() {
        String username_ibtissam = etUsername_ibtissam.getText().toString();
        String password_ibtissam = etPassword_ibtissam.getText().toString();
        
        if (username_ibtissam.isEmpty() || password_ibtissam.isEmpty()) {
            Toast.makeText(this, "Veuillez remplir tous les champs", Toast.LENGTH_SHORT).show();
            return;
        }
        
        databaseManager_ibtissam.getUserByUsername_ibtissam(username_ibtissam, new DatabaseManager_ibtissam.DatabaseCallback_ibtissam<User_ibtissam>() {
            @Override
            public void onSuccess_ibtissam(User_ibtissam user_ibtissam) {
                if (user_ibtissam != null) {
                    boolean passwordValid_ibtissam = PasswordHasher_ibtissam.verifyPassword_ibtissam(password_ibtissam, user_ibtissam.getPassword_ibtissam());
                    
                    if (passwordValid_ibtissam) {
                        runOnUiThread(() -> {
                            Toast.makeText(LoginActivity_ibtissam.this, "Connexion réussie", Toast.LENGTH_SHORT).show();
                            Intent intent_ibtissam = new Intent(LoginActivity_ibtissam.this, NotesActivity_ibtissam.class);
                            intent_ibtissam.putExtra("USER_ID", user_ibtissam.getId_ibtissam());
                            intent_ibtissam.putExtra("USERNAME", user_ibtissam.getUsername_ibtissam());
                            startActivity(intent_ibtissam);
                            finish();
                        });
                    } else {
                        runOnUiThread(() -> Toast.makeText(LoginActivity_ibtissam.this, "Mot de passe incorrect", Toast.LENGTH_SHORT).show());
                    }
                } else {
                    runOnUiThread(() -> Toast.makeText(LoginActivity_ibtissam.this, "Utilisateur non trouvé", Toast.LENGTH_SHORT).show());
                }
            }
            
            @Override
            public void onError_ibtissam(Exception e_ibtissam) {
                runOnUiThread(() -> Toast.makeText(LoginActivity_ibtissam.this, "Erreur: " + e_ibtissam.getMessage(), Toast.LENGTH_SHORT).show());
            }
        });
    }
    
    private void register_ibtissam() {
        String username_ibtissam = etUsername_ibtissam.getText().toString();
        String password_ibtissam = etPassword_ibtissam.getText().toString();
        
        if (username_ibtissam.isEmpty() || password_ibtissam.isEmpty()) {
            Toast.makeText(this, "Veuillez remplir tous les champs", Toast.LENGTH_SHORT).show();
            return;
        }
        
        String hashedPassword_ibtissam = PasswordHasher_ibtissam.hashPassword_ibtissam(password_ibtissam);
        User_ibtissam user_ibtissam = new User_ibtissam(username_ibtissam, username_ibtissam + "@example.com", hashedPassword_ibtissam);
        
        databaseManager_ibtissam.insertUser_ibtissam(user_ibtissam, new DatabaseManager_ibtissam.DatabaseCallback_ibtissam<Long>() {
            @Override
            public void onSuccess_ibtissam(Long userId_ibtissam) {
                runOnUiThread(() -> {
                    Toast.makeText(LoginActivity_ibtissam.this, "Inscription réussie", Toast.LENGTH_SHORT).show();
                    login_ibtissam();
                });
            }
            
            @Override
            public void onError_ibtissam(Exception e_ibtissam) {
                runOnUiThread(() -> Toast.makeText(LoginActivity_ibtissam.this, "Erreur: " + e_ibtissam.getMessage(), Toast.LENGTH_SHORT).show());
            }
        });
    }
}
