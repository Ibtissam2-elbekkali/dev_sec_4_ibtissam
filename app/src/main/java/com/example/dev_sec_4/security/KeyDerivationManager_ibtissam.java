package com.example.dev_sec_4.security;

import android.util.Log;
import java.security.GeneralSecurityException;
import java.security.SecureRandom;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;

public class KeyDerivationManager_ibtissam {
    private static final String TAG_ibtissam = "KeyDerivationManager";
    
    public static byte[] deriveKeyPbkdf2_ibtissam(char[] password_ibtissam, byte[] salt_ibtissam, int iterations_ibtissam, int keyLength_ibtissam) {
        try {
            SecretKeyFactory factory_ibtissam = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
            PBEKeySpec spec_ibtissam = new PBEKeySpec(password_ibtissam, salt_ibtissam, iterations_ibtissam, keyLength_ibtissam);
            SecretKey key_ibtissam = factory_ibtissam.generateSecret(spec_ibtissam);
            Log.d(TAG_ibtissam, "Clé dérivée avec succès");
            return key_ibtissam.getEncoded();
        } catch (GeneralSecurityException e_ibtissam) {
            Log.e(TAG_ibtissam, "Erreur dérivation: " + e_ibtissam.getMessage());
            return null;
        }
    }
    
    public static byte[] generateSalt_ibtissam(int length_ibtissam) {
        byte[] salt_ibtissam = new byte[length_ibtissam];
        new SecureRandom().nextBytes(salt_ibtissam);
        return salt_ibtissam;
    }
    
    public static SecretKey createAesKey_ibtissam(byte[] keyBytes_ibtissam) {
        return new SecretKeySpec(keyBytes_ibtissam, "AES");
    }
    
    public static SecretKey deriveAesKey_ibtissam(char[] password_ibtissam, byte[] salt_ibtissam) {
        byte[] keyBytes_ibtissam = deriveKeyPbkdf2_ibtissam(password_ibtissam, salt_ibtissam, 10000, 256);
        if (keyBytes_ibtissam != null) {
            return createAesKey_ibtissam(keyBytes_ibtissam);
        }
        return null;
    }
}
