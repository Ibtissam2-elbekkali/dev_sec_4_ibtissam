package com.example.dev_sec_4.database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface NoteDao_ibtissam {
    @Insert
    long insert_ibtissam(Note_ibtissam note_ibtissam);
    
    @Update
    void update_ibtissam(Note_ibtissam note_ibtissam);
    
    @Delete
    void delete_ibtissam(Note_ibtissam note_ibtissam);
    
    @Query("SELECT * FROM notes_ibtissam WHERE id_ibtissam = :id_ibtissam")
    Note_ibtissam getNoteById_ibtissam(int id_ibtissam);
    
    @Query("SELECT * FROM notes_ibtissam WHERE userId_ibtissam = :userId_ibtissam ORDER BY createdAt_ibtissam DESC")
    List<Note_ibtissam> getNotesByUserId_ibtissam(int userId_ibtissam);
    
    @Query("SELECT * FROM notes_ibtissam")
    List<Note_ibtissam> getAllNotes_ibtissam();
}
