package com.example.dev_sec_4.storage;

import android.content.Context;
import android.util.Log;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class InternalStorageManager_ibtissam {
    private static final String TAG_ibtissam = "InternalStorageManager";
    private final Context context_ibtissam;

    public InternalStorageManager_ibtissam(Context context_ibtissam) {
        this.context_ibtissam = context_ibtissam;
    }

    public boolean writeToInternalStorage_ibtissam(String filename_ibtissam, String content_ibtissam) {
        try (FileOutputStream fos_ibtissam = context_ibtissam.openFileOutput(filename_ibtissam, Context.MODE_PRIVATE)) {
            fos_ibtissam.write(content_ibtissam.getBytes());
            return true;
        } catch (IOException e_ibtissam) {
            Log.e(TAG_ibtissam, "Erreur écriture: " + e_ibtissam.getMessage());
            return false;
        }
    }

    public String readFromInternalStorage_ibtissam(String filename_ibtissam) {
        try (FileInputStream fis_ibtissam = context_ibtissam.openFileInput(filename_ibtissam);
             InputStreamReader isr_ibtissam = new InputStreamReader(fis_ibtissam);
             BufferedReader br_ibtissam = new BufferedReader(isr_ibtissam)) {
            StringBuilder sb_ibtissam = new StringBuilder();
            String line_ibtissam;
            while ((line_ibtissam = br_ibtissam.readLine()) != null) {
                sb_ibtissam.append(line_ibtissam).append("\n");
            }
            return sb_ibtissam.toString();
        } catch (IOException e_ibtissam) {
            Log.e(TAG_ibtissam, "Erreur lecture: " + e_ibtissam.getMessage());
            return null;
        }
    }
}
