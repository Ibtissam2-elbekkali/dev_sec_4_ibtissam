package com.example.dev_sec_4.security;

import android.content.Context;
import android.os.Build;
import android.security.keystore.KeyGenParameterSpec;
import android.security.keystore.KeyProperties;
import android.util.Log;

import androidx.security.crypto.MasterKey;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.security.KeyStore;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

public class KeystoreManager_ibtissam {
    private static final String TAG_ibtissam = "KeystoreManager";
    private static final String ANDROID_KEYSTORE_ibtissam = "AndroidKeyStore";
    private static final String STRONGBOX_KEY_ALIAS_ibtissam = "secure_strongbox_key_ibtissam";
    
    private final Context context_ibtissam;
    
    public KeystoreManager_ibtissam(Context context_ibtissam) {
        this.context_ibtissam = context_ibtissam;
    }
    
    public MasterKey createStrongBoxMasterKey_ibtissam() throws GeneralSecurityException, IOException {
        boolean isStrongBoxAvailable_ibtissam = isStrongBoxAvailable_ibtissam();
        
        MasterKey.Builder builder_ibtissam = new MasterKey.Builder(context_ibtissam, STRONGBOX_KEY_ALIAS_ibtissam)
                .setKeyScheme(MasterKey.KeyScheme.AES256_GCM)
                .setUserAuthenticationRequired(false);
        
        if (isStrongBoxAvailable_ibtissam) {
            // MasterKey.Builder uses setRequestStrongBoxBacked instead of setIsStrongBoxBacked
            builder_ibtissam.setRequestStrongBoxBacked(true);
            Log.d(TAG_ibtissam, "Création d'une MasterKey protégée par StrongBox");
        } else {
            Log.d(TAG_ibtissam, "StrongBox non disponible, création d'une MasterKey standard");
        }
        
        return builder_ibtissam.build();
    }
    
    public boolean isStrongBoxAvailable_ibtissam() {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.P) {
            return false;
        }
        
        try {
            KeyGenParameterSpec spec_ibtissam = new KeyGenParameterSpec.Builder(
                    "strongbox_test_key_ibtissam",
                    KeyProperties.PURPOSE_ENCRYPT | KeyProperties.PURPOSE_DECRYPT)
                    .setBlockModes(KeyProperties.BLOCK_MODE_GCM)
                    .setEncryptionPaddings(KeyProperties.ENCRYPTION_PADDING_NONE)
                    .setKeySize(256)
                    .setIsStrongBoxBacked(true)
                    .build();
            
            KeyGenerator keyGenerator_ibtissam = KeyGenerator.getInstance(
                    KeyProperties.KEY_ALGORITHM_AES,
                    ANDROID_KEYSTORE_ibtissam);
            keyGenerator_ibtissam.init(spec_ibtissam);
            keyGenerator_ibtissam.generateKey();
            
            KeyStore keyStore_ibtissam = KeyStore.getInstance(ANDROID_KEYSTORE_ibtissam);
            keyStore_ibtissam.load(null);
            keyStore_ibtissam.deleteEntry("strongbox_test_key_ibtissam");
            
            return true;
        } catch (Exception e_ibtissam) {
            Log.d(TAG_ibtissam, "StrongBox non disponible: " + e_ibtissam.getMessage());
            return false;
        }
    }
    
    public boolean generateAesKey_ibtissam(String alias_ibtissam) {
        try {
            KeyGenParameterSpec spec_ibtissam = new KeyGenParameterSpec.Builder(
                    alias_ibtissam,
                    KeyProperties.PURPOSE_ENCRYPT | KeyProperties.PURPOSE_DECRYPT)
                    .setBlockModes(KeyProperties.BLOCK_MODE_GCM)
                    .setEncryptionPaddings(KeyProperties.ENCRYPTION_PADDING_NONE)
                    .setKeySize(256)
                    .build();
            
            KeyGenerator keyGenerator_ibtissam = KeyGenerator.getInstance(
                    KeyProperties.KEY_ALGORITHM_AES,
                    ANDROID_KEYSTORE_ibtissam);
            keyGenerator_ibtissam.init(spec_ibtissam);
            keyGenerator_ibtissam.generateKey();
            
            Log.d(TAG_ibtissam, "Clé AES générée avec succès: " + alias_ibtissam);
            return true;
        } catch (Exception e_ibtissam) {
            Log.e(TAG_ibtissam, "Erreur lors de la génération de la clé AES: " + e_ibtissam.getMessage());
            return false;
        }
    }
    
    public SecretKey getKey_ibtissam(String alias_ibtissam) {
        try {
            KeyStore keyStore_ibtissam = KeyStore.getInstance(ANDROID_KEYSTORE_ibtissam);
            keyStore_ibtissam.load(null);
            
            KeyStore.SecretKeyEntry entry_ibtissam = (KeyStore.SecretKeyEntry) keyStore_ibtissam.getEntry(
                    alias_ibtissam,
                    null);
            
            if (entry_ibtissam != null) {
                Log.d(TAG_ibtissam, "Clé récupérée avec succès: " + alias_ibtissam);
                return entry_ibtissam.getSecretKey();
            } else {
                Log.e(TAG_ibtissam, "Clé non trouvée: " + alias_ibtissam);
                return null;
            }
        } catch (Exception e_ibtissam) {
            Log.e(TAG_ibtissam, "Erreur lors de la récupération de la clé: " + e_ibtissam.getMessage());
            return null;
        }
    }
    
    public boolean deleteKey_ibtissam(String alias_ibtissam) {
        try {
            KeyStore keyStore_ibtissam = KeyStore.getInstance(ANDROID_KEYSTORE_ibtissam);
            keyStore_ibtissam.load(null);
            keyStore_ibtissam.deleteEntry(alias_ibtissam);
            
            Log.d(TAG_ibtissam, "Clé supprimée avec succès: " + alias_ibtissam);
            return true;
        } catch (Exception e_ibtissam) {
            Log.e(TAG_ibtissam, "Erreur lors de la suppression de la clé: " + e_ibtissam.getMessage());
            return false;
        }
    }
}
