package com.example.dev_sec_4.security;

import android.util.Base64;
import android.util.Log;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.util.Arrays;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

public class PasswordHasher_ibtissam {
    private static final String TAG_ibtissam = "PasswordHasher";
    private static final String ALGORITHM_ibtissam = "PBKDF2WithHmacSHA256";
    private static final int ITERATIONS_ibtissam = 10000;
    private static final int KEY_LENGTH_ibtissam = 256;
    private static final int SALT_LENGTH_ibtissam = 16;
    
    public static String hashPassword_ibtissam(String password_ibtissam) {
        try {
            byte[] salt_ibtissam = new byte[SALT_LENGTH_ibtissam];
            SecureRandom random_ibtissam = new SecureRandom();
            random_ibtissam.nextBytes(salt_ibtissam);
            
            byte[] hash_ibtissam = pbkdf2_ibtissam(password_ibtissam.toCharArray(), salt_ibtissam, ITERATIONS_ibtissam, KEY_LENGTH_ibtissam);
            
            byte[] combined_ibtissam = new byte[salt_ibtissam.length + hash_ibtissam.length];
            System.arraycopy(salt_ibtissam, 0, combined_ibtissam, 0, salt_ibtissam.length);
            System.arraycopy(hash_ibtissam, 0, combined_ibtissam, salt_ibtissam.length, hash_ibtissam.length);
            
            return Base64.encodeToString(combined_ibtissam, Base64.NO_WRAP);
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e_ibtissam) {
            Log.e(TAG_ibtissam, "Erreur hachage: " + e_ibtissam.getMessage());
            return null;
        }
    }
    
    public static boolean verifyPassword_ibtissam(String password_ibtissam, String storedHash_ibtissam) {
        try {
            byte[] combined_ibtissam = Base64.decode(storedHash_ibtissam, Base64.NO_WRAP);
            byte[] salt_ibtissam = new byte[SALT_LENGTH_ibtissam];
            System.arraycopy(combined_ibtissam, 0, salt_ibtissam, 0, salt_ibtissam.length);
            
            byte[] hash_ibtissam = new byte[combined_ibtissam.length - salt_ibtissam.length];
            System.arraycopy(combined_ibtissam, salt_ibtissam.length, hash_ibtissam, 0, hash_ibtissam.length);
            
            byte[] testHash_ibtissam = pbkdf2_ibtissam(password_ibtissam.toCharArray(), salt_ibtissam, ITERATIONS_ibtissam, KEY_LENGTH_ibtissam);
            return Arrays.equals(hash_ibtissam, testHash_ibtissam);
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e_ibtissam) {
            Log.e(TAG_ibtissam, "Erreur vérification: " + e_ibtissam.getMessage());
            return false;
        }
    }
    
    private static byte[] pbkdf2_ibtissam(char[] password_ibtissam, byte[] salt_ibtissam, int iterations_ibtissam, int keyLength_ibtissam)
            throws NoSuchAlgorithmException, InvalidKeySpecException {
        PBEKeySpec spec_ibtissam = new PBEKeySpec(password_ibtissam, salt_ibtissam, iterations_ibtissam, keyLength_ibtissam);
        SecretKeyFactory factory_ibtissam = SecretKeyFactory.getInstance(ALGORITHM_ibtissam);
        return factory_ibtissam.generateSecret(spec_ibtissam).getEncoded();
    }
}
