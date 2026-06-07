package com.example.dev_sec_4.security;

import android.content.Context;
import android.util.Log;
import androidx.security.crypto.EncryptedFile;
import androidx.security.crypto.MasterKey;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.security.GeneralSecurityException;

public class EncryptedFileManager_ibtissam {
    private static final String TAG_ibtissam = "EncryptedFileManager";
    private final Context context_ibtissam;
    private MasterKey masterKey_ibtissam;
    
    public EncryptedFileManager_ibtissam(Context context_ibtissam) {
        this.context_ibtissam = context_ibtissam;
        initializeMasterKey_ibtissam();
    }
    
    private void initializeMasterKey_ibtissam() {
        try {
            masterKey_ibtissam = new MasterKey.Builder(context_ibtissam)
                    .setKeyScheme(MasterKey.KeyScheme.AES256_GCM)
                    .setUserAuthenticationRequired(false)
                    .build();
        } catch (GeneralSecurityException | IOException e_ibtissam) {
            Log.e(TAG_ibtissam, "Erreur MasterKey: " + e_ibtissam.getMessage());
        }
    }
    
    public boolean writeToEncryptedFile_ibtissam(String filename_ibtissam, String content_ibtissam) {
        if (masterKey_ibtissam == null) return false;
        try {
            File file_ibtissam = new File(context_ibtissam.getFilesDir(), filename_ibtissam);
            if (file_ibtissam.exists()) file_ibtissam.delete();
            
            EncryptedFile encryptedFile_ibtissam = new EncryptedFile.Builder(
                    context_ibtissam,
                    file_ibtissam,
                    masterKey_ibtissam,
                    EncryptedFile.FileEncryptionScheme.AES256_GCM_HKDF_4KB
            ).build();
            
            try (OutputStream os_ibtissam = encryptedFile_ibtissam.openFileOutput()) {
                os_ibtissam.write(content_ibtissam.getBytes(StandardCharsets.UTF_8));
            }
            return true;
        } catch (GeneralSecurityException | IOException e_ibtissam) {
            Log.e(TAG_ibtissam, "Erreur écriture: " + e_ibtissam.getMessage());
            return false;
        }
    }
    
    public String readFromEncryptedFile_ibtissam(String filename_ibtissam) {
        if (masterKey_ibtissam == null) return null;
        try {
            File file_ibtissam = new File(context_ibtissam.getFilesDir(), filename_ibtissam);
            if (!file_ibtissam.exists()) return null;

            EncryptedFile encryptedFile_ibtissam = new EncryptedFile.Builder(
                    context_ibtissam,
                    file_ibtissam,
                    masterKey_ibtissam,
                    EncryptedFile.FileEncryptionScheme.AES256_GCM_HKDF_4KB
            ).build();
            
            try (InputStream is_ibtissam = encryptedFile_ibtissam.openFileInput();
                 ByteArrayOutputStream baos_ibtissam = new ByteArrayOutputStream()) {
                byte[] buffer_ibtissam = new byte[1024];
                int bytesRead_ibtissam;
                while ((bytesRead_ibtissam = is_ibtissam.read(buffer_ibtissam)) != -1) {
                    baos_ibtissam.write(buffer_ibtissam, 0, bytesRead_ibtissam);
                }
                return baos_ibtissam.toString(StandardCharsets.UTF_8.name());
            }
        } catch (GeneralSecurityException | IOException e_ibtissam) {
            Log.e(TAG_ibtissam, "Erreur lecture: " + e_ibtissam.getMessage());
            return null;
        }
    }
}
