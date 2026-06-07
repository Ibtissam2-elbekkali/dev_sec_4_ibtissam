package com.example.dev_sec_4.database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface UserDao_ibtissam {
    @Insert
    long insert_ibtissam(User_ibtissam user_ibtissam);
    
    @Update
    void update_ibtissam(User_ibtissam user_ibtissam);
    
    @Delete
    void delete_ibtissam(User_ibtissam user_ibtissam);
    
    @Query("SELECT * FROM users_ibtissam WHERE id_ibtissam = :id_ibtissam")
    User_ibtissam getUserById_ibtissam(int id_ibtissam);
    
    @Query("SELECT * FROM users_ibtissam WHERE username_ibtissam = :username_ibtissam")
    User_ibtissam getUserByUsername_ibtissam(String username_ibtissam);
    
    @Query("SELECT * FROM users_ibtissam")
    List<User_ibtissam> getAllUsers_ibtissam();
}
