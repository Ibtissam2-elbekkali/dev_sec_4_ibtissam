package com.example.dev_sec_4.database;

import android.content.Context;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import net.sqlcipher.database.SQLiteDatabase;
import net.sqlcipher.database.SupportFactory;
import java.security.SecureRandom;

@Database(entities = {User_ibtissam.class, Note_ibtissam.class}, version = 1, exportSchema = false)
public abstract class AppDatabase_ibtissam extends RoomDatabase {
    
    private static final String DATABASE_NAME_ibtissam = "secure_app_db_ibtissam";
    private static AppDatabase_ibtissam instance_ibtissam;
    
    public abstract UserDao_ibtissam userDao_ibtissam();
    public abstract NoteDao_ibtissam noteDao_ibtissam();
    
    public static synchronized AppDatabase_ibtissam getInstance_ibtissam(Context context_ibtissam, char[] passphrase_ibtissam) {
        if (instance_ibtissam == null) {
            byte[] passphraseBytes_ibtissam = SQLiteDatabase.getBytes(passphrase_ibtissam);
            SupportFactory factory_ibtissam = new SupportFactory(passphraseBytes_ibtissam);
            
            instance_ibtissam = Room.databaseBuilder(context_ibtissam.getApplicationContext(),
                    AppDatabase_ibtissam.class, DATABASE_NAME_ibtissam)
                    .openHelperFactory(factory_ibtissam)
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return instance_ibtissam;
    }
    
    public static char[] generateRandomPassphrase_ibtissam(int length_ibtissam) {
        String allowedChars_ibtissam = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789!@#$%^&*()";
        SecureRandom random_ibtissam = new SecureRandom();
        char[] passphrase_ibtissam = new char[length_ibtissam];
        
        for (int i_ibtissam = 0; i_ibtissam < length_ibtissam; i_ibtissam++) {
            passphrase_ibtissam[i_ibtissam] = allowedChars_ibtissam.charAt(random_ibtissam.nextInt(allowedChars_ibtissam.length()));
        }
        
        return passphrase_ibtissam;
    }
}
