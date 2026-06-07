package com.example.dev_sec_4.database;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "users_ibtissam")
public class User_ibtissam {
    @PrimaryKey(autoGenerate = true)
    private int id_ibtissam;
    
    private String username_ibtissam;
    private String email_ibtissam;
    private String password_ibtissam;
    
    public User_ibtissam(String username_ibtissam, String email_ibtissam, String password_ibtissam) {
        this.username_ibtissam = username_ibtissam;
        this.email_ibtissam = email_ibtissam;
        this.password_ibtissam = password_ibtissam;
    }
    
    public int getId_ibtissam() { return id_ibtissam; }
    public void setId_ibtissam(int id_ibtissam) { this.id_ibtissam = id_ibtissam; }
    
    public String getUsername_ibtissam() { return username_ibtissam; }
    public void setUsername_ibtissam(String username_ibtissam) { this.username_ibtissam = username_ibtissam; }
    
    public String getEmail_ibtissam() { return email_ibtissam; }
    public void setEmail_ibtissam(String email_ibtissam) { this.email_ibtissam = email_ibtissam; }
    
    public String getPassword_ibtissam() { return password_ibtissam; }
    public void setPassword_ibtissam(String password_ibtissam) { this.password_ibtissam = password_ibtissam; }
}
