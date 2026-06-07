package com.example.dev_sec_4.database;

import android.content.Context;
import android.util.Log;
import androidx.security.crypto.EncryptedSharedPreferences;
import androidx.security.crypto.MasterKey;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class DatabaseManager_ibtissam {
    private static final String TAG_ibtissam = "DatabaseManager";
    private static final String PASSPHRASE_PREF_NAME_ibtissam = "secure_db_passphrase_ibtissam";
    private static final String PASSPHRASE_KEY_ibtissam = "db_passphrase_ibtissam";
    private static final int PASSPHRASE_LENGTH_ibtissam = 32;
    
    private final Context context_ibtissam;
    private final Executor executor_ibtissam;
    private AppDatabase_ibtissam database_ibtissam;
    private char[] passphrase_ibtissam;
    
    public DatabaseManager_ibtissam(Context context_ibtissam) {
        this.context_ibtissam = context_ibtissam;
        this.executor_ibtissam = Executors.newSingleThreadExecutor();
        initializeDatabase_ibtissam();
    }
    
    private void initializeDatabase_ibtissam() {
        try {
            passphrase_ibtissam = getOrCreatePassphrase_ibtissam();
            database_ibtissam = AppDatabase_ibtissam.getInstance_ibtissam(context_ibtissam, passphrase_ibtissam);
            Log.d(TAG_ibtissam, "Base de données initialisée avec succès");
        } catch (Exception e_ibtissam) {
            Log.e(TAG_ibtissam, "Erreur lors de l'initialisation de la base de données: " + e_ibtissam.getMessage());
        }
    }
    
    private char[] getOrCreatePassphrase_ibtissam() throws GeneralSecurityException, IOException {
        MasterKey masterKey_ibtissam = new MasterKey.Builder(context_ibtissam)
                .setKeyScheme(MasterKey.KeyScheme.AES256_GCM)
                .build();
        
        EncryptedSharedPreferences encryptedPrefs_ibtissam = (EncryptedSharedPreferences) EncryptedSharedPreferences.create(
                context_ibtissam,
                PASSPHRASE_PREF_NAME_ibtissam,
                masterKey_ibtissam,
                EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
                EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
        );
        
        String storedPassphrase_ibtissam = encryptedPrefs_ibtissam.getString(PASSPHRASE_KEY_ibtissam, null);
        if (storedPassphrase_ibtissam == null) {
            char[] newPassphrase_ibtissam = AppDatabase_ibtissam.generateRandomPassphrase_ibtissam(PASSPHRASE_LENGTH_ibtissam);
            storedPassphrase_ibtissam = new String(newPassphrase_ibtissam);
            encryptedPrefs_ibtissam.edit().putString(PASSPHRASE_KEY_ibtissam, storedPassphrase_ibtissam).apply();
            return newPassphrase_ibtissam;
        } else {
            return storedPassphrase_ibtissam.toCharArray();
        }
    }
    
    public void insertUser_ibtissam(User_ibtissam user_ibtissam, DatabaseCallback_ibtissam<Long> callback_ibtissam) {
        executor_ibtissam.execute(() -> {
            try {
                long userId_ibtissam = database_ibtissam.userDao_ibtissam().insert_ibtissam(user_ibtissam);
                callback_ibtissam.onSuccess_ibtissam(userId_ibtissam);
            } catch (Exception e_ibtissam) {
                callback_ibtissam.onError_ibtissam(e_ibtissam);
            }
        });
    }
    
    public void getUserByUsername_ibtissam(String username_ibtissam, DatabaseCallback_ibtissam<User_ibtissam> callback_ibtissam) {
        executor_ibtissam.execute(() -> {
            try {
                User_ibtissam user_ibtissam = database_ibtissam.userDao_ibtissam().getUserByUsername_ibtissam(username_ibtissam);
                callback_ibtissam.onSuccess_ibtissam(user_ibtissam);
            } catch (Exception e_ibtissam) {
                callback_ibtissam.onError_ibtissam(e_ibtissam);
            }
        });
    }
    
    public void insertNote_ibtissam(Note_ibtissam note_ibtissam, DatabaseCallback_ibtissam<Long> callback_ibtissam) {
        executor_ibtissam.execute(() -> {
            try {
                long noteId_ibtissam = database_ibtissam.noteDao_ibtissam().insert_ibtissam(note_ibtissam);
                callback_ibtissam.onSuccess_ibtissam(noteId_ibtissam);
            } catch (Exception e_ibtissam) {
                callback_ibtissam.onError_ibtissam(e_ibtissam);
            }
        });
    }
    
    public void getNotesByUserId_ibtissam(int userId_ibtissam, DatabaseCallback_ibtissam<List<Note_ibtissam>> callback_ibtissam) {
        executor_ibtissam.execute(() -> {
            try {
                List<Note_ibtissam> notes_ibtissam = database_ibtissam.noteDao_ibtissam().getNotesByUserId_ibtissam(userId_ibtissam);
                callback_ibtissam.onSuccess_ibtissam(notes_ibtissam);
            } catch (Exception e_ibtissam) {
                callback_ibtissam.onError_ibtissam(e_ibtissam);
            }
        });
    }
    
    public interface DatabaseCallback_ibtissam<T> {
        void onSuccess_ibtissam(T result_ibtissam);
        void onError_ibtissam(Exception e_ibtissam);
    }
}
