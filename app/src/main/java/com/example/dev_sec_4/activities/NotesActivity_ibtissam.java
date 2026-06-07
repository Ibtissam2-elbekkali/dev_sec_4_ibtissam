package com.example.dev_sec_4.activities;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dev_sec_4.R;
import com.example.dev_sec_4.adapters.NoteAdapter_ibtissam;
import com.example.dev_sec_4.database.DatabaseManager_ibtissam;
import com.example.dev_sec_4.database.Note_ibtissam;
import com.example.dev_sec_4.security.EncryptedFileManager_ibtissam;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class NotesActivity_ibtissam extends AppCompatActivity {
    
    private EditText etNoteTitle_ibtissam;
    private EditText etNoteContent_ibtissam;
    private Button btnSaveNote_ibtissam;
    private RecyclerView rvNotes_ibtissam;
    private TextView tvUsername_ibtissam;
    
    private DatabaseManager_ibtissam databaseManager_ibtissam;
    private EncryptedFileManager_ibtissam encryptedFileManager_ibtissam;
    private NoteAdapter_ibtissam noteAdapter_ibtissam;
    private List<Note_ibtissam> notesList_ibtissam;
    
    private int userId_ibtissam;
    private String username_ibtissam;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notes_ibtissam);
        
        userId_ibtissam = getIntent().getIntExtra("USER_ID", -1);
        username_ibtissam = getIntent().getStringExtra("USERNAME");
        
        if (userId_ibtissam == -1 || username_ibtissam == null) {
            Toast.makeText(this, "Erreur: utilisateur non identifié", Toast.LENGTH_SHORT).show();
            finish();
            return;
        }
        
        databaseManager_ibtissam = new DatabaseManager_ibtissam(this);
        encryptedFileManager_ibtissam = new EncryptedFileManager_ibtissam(this);
        
        etNoteTitle_ibtissam = findViewById(R.id.etNoteTitle_ibtissam);
        etNoteContent_ibtissam = findViewById(R.id.etNoteContent_ibtissam);
        btnSaveNote_ibtissam = findViewById(R.id.btnSaveNote_ibtissam);
        rvNotes_ibtissam = findViewById(R.id.rvNotes_ibtissam);
        tvUsername_ibtissam = findViewById(R.id.tvUsername_ibtissam);
        
        notesList_ibtissam = new ArrayList<>();
        noteAdapter_ibtissam = new NoteAdapter_ibtissam(notesList_ibtissam);
        rvNotes_ibtissam.setLayoutManager(new LinearLayoutManager(this));
        rvNotes_ibtissam.setAdapter(noteAdapter_ibtissam);
        
        tvUsername_ibtissam.setText("Notes de " + username_ibtissam);
        
        btnSaveNote_ibtissam.setOnClickListener(v_ibtissam -> saveNote_ibtissam());
        
        loadNotes_ibtissam();
    }
    
    private void saveNote_ibtissam() {
        String title_ibtissam = etNoteTitle_ibtissam.getText().toString();
        String content_ibtissam = etNoteContent_ibtissam.getText().toString();
        
        if (title_ibtissam.isEmpty() || content_ibtissam.isEmpty()) {
            Toast.makeText(this, "Veuillez remplir tous les champs", Toast.LENGTH_SHORT).show();
            return;
        }
        
        Note_ibtissam note_ibtissam = new Note_ibtissam(userId_ibtissam, title_ibtissam, content_ibtissam);
        
        databaseManager_ibtissam.insertNote_ibtissam(note_ibtissam, new DatabaseManager_ibtissam.DatabaseCallback_ibtissam<Long>() {
            @Override
            public void onSuccess_ibtissam(Long noteId_ibtissam) {
                logAction_ibtissam("Note créée: " + title_ibtissam);
                
                note_ibtissam.setId_ibtissam(noteId_ibtissam.intValue());
                runOnUiThread(() -> {
                    notesList_ibtissam.add(0, note_ibtissam);
                    noteAdapter_ibtissam.notifyItemInserted(0);
                    etNoteTitle_ibtissam.setText("");
                    etNoteContent_ibtissam.setText("");
                    Toast.makeText(NotesActivity_ibtissam.this, "Note enregistrée avec succès", Toast.LENGTH_SHORT).show();
                });
            }
            
            @Override
            public void onError_ibtissam(Exception e_ibtissam) {
                runOnUiThread(() -> Toast.makeText(NotesActivity_ibtissam.this, "Erreur: " + e_ibtissam.getMessage(), Toast.LENGTH_SHORT).show());
            }
        });
    }
    
    private void loadNotes_ibtissam() {
        databaseManager_ibtissam.getNotesByUserId_ibtissam(userId_ibtissam, new DatabaseManager_ibtissam.DatabaseCallback_ibtissam<List<Note_ibtissam>>() {
            @Override
            public void onSuccess_ibtissam(List<Note_ibtissam> notes_ibtissam) {
                runOnUiThread(() -> {
                    notesList_ibtissam.clear();
                    notesList_ibtissam.addAll(notes_ibtissam);
                    noteAdapter_ibtissam.notifyDataSetChanged();
                    logAction_ibtissam("Notes chargées: " + notes_ibtissam.size() + " notes");
                });
            }
            
            @Override
            public void onError_ibtissam(Exception e_ibtissam) {
                runOnUiThread(() -> Toast.makeText(NotesActivity_ibtissam.this, "Erreur: " + e_ibtissam.getMessage(), Toast.LENGTH_SHORT).show());
            }
        });
    }
    
    private void logAction_ibtissam(String action_ibtissam) {
        SimpleDateFormat sdf_ibtissam = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
        String timestamp_ibtissam = sdf_ibtissam.format(new Date());
        String logMessage_ibtissam = timestamp_ibtissam + " - " + username_ibtissam + " - " + action_ibtissam + "\n";
        
        String logFilename_ibtissam = "user_" + userId_ibtissam + "_log_ibtissam.txt";
        
        String existingLog_ibtissam = encryptedFileManager_ibtissam.readFromEncryptedFile_ibtissam(logFilename_ibtissam);
        if (existingLog_ibtissam == null) {
            existingLog_ibtissam = "";
        }
        
        String newLog_ibtissam = logMessage_ibtissam + existingLog_ibtissam;
        encryptedFileManager_ibtissam.writeToEncryptedFile_ibtissam(logFilename_ibtissam, newLog_ibtissam);
    }
}
