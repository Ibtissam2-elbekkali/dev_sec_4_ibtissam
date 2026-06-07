package com.example.dev_sec_4.storage;

import android.content.Context;
import android.os.Build;
import android.os.Environment;
import android.util.Log;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class ExternalStorageManager_ibtissam {
    private static final String TAG_ibtissam = "ExternalStorageManager";
    private final Context context_ibtissam;

    public ExternalStorageManager_ibtissam(Context context_ibtissam) {
        this.context_ibtissam = context_ibtissam;
    }

    public boolean isExternalStorageWritable_ibtissam() {
        String state_ibtissam = Environment.getExternalStorageState();
        return Environment.MEDIA_MOUNTED.equals(state_ibtissam);
    }

    public boolean isExternalStorageReadable_ibtissam() {
        String state_ibtissam = Environment.getExternalStorageState();
        return Environment.MEDIA_MOUNTED.equals(state_ibtissam) || 
               Environment.MEDIA_MOUNTED_READ_ONLY.equals(state_ibtissam);
    }

    public File getExternalStorageDir_ibtissam(String type_ibtissam) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            return context_ibtissam.getExternalFilesDir(type_ibtissam);
        } else {
            if (type_ibtissam == null) {
                return Environment.getExternalStorageDirectory();
            } else {
                return Environment.getExternalStoragePublicDirectory(type_ibtissam);
            }
        }
    }

    public boolean writeToExternalStorage_ibtissam(String filename_ibtissam, String content_ibtissam, String type_ibtissam) {
        if (!isExternalStorageWritable_ibtissam()) return false;
        File dir_ibtissam = getExternalStorageDir_ibtissam(type_ibtissam);
        if (dir_ibtissam != null && !dir_ibtissam.exists() && !dir_ibtissam.mkdirs()) return false;
        File file_ibtissam = new File(dir_ibtissam, filename_ibtissam);
        try (FileOutputStream fos_ibtissam = new FileOutputStream(file_ibtissam)) {
            fos_ibtissam.write(content_ibtissam.getBytes());
            return true;
        } catch (IOException e_ibtissam) {
            return false;
        }
    }

    public String readFromExternalStorage_ibtissam(String filename_ibtissam, String type_ibtissam) {
        if (!isExternalStorageReadable_ibtissam()) return null;
        File dir_ibtissam = getExternalStorageDir_ibtissam(type_ibtissam);
        File file_ibtissam = new File(dir_ibtissam, filename_ibtissam);
        try (FileInputStream fis_ibtissam = new FileInputStream(file_ibtissam);
             InputStreamReader isr_ibtissam = new InputStreamReader(fis_ibtissam);
             BufferedReader br_ibtissam = new BufferedReader(isr_ibtissam)) {
            StringBuilder sb_ibtissam = new StringBuilder();
            String line_ibtissam;
            while ((line_ibtissam = br_ibtissam.readLine()) != null) {
                sb_ibtissam.append(line_ibtissam).append("\n");
            }
            return sb_ibtissam.toString();
        } catch (IOException e_ibtissam) {
            return null;
        }
    }
}
