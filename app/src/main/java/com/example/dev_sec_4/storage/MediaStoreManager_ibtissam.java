package com.example.dev_sec_4.storage;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

public class MediaStoreManager_ibtissam {
    private static final String TAG_ibtissam = "MediaStoreManager";
    private final ContentResolver contentResolver_ibtissam;

    public MediaStoreManager_ibtissam(Context context_ibtissam) {
        this.contentResolver_ibtissam = context_ibtissam.getContentResolver();
    }

    public Uri saveImageToGallery_ibtissam(Bitmap bitmap_ibtissam, String displayName_ibtissam) {
        ContentValues values_ibtissam = new ContentValues();
        values_ibtissam.put(MediaStore.Images.Media.DISPLAY_NAME, displayName_ibtissam);
        values_ibtissam.put(MediaStore.Images.Media.MIME_TYPE, "image/jpeg");
        
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            values_ibtissam.put(MediaStore.Images.Media.IS_PENDING, 1);
            values_ibtissam.put(MediaStore.Images.Media.RELATIVE_PATH, Environment.DIRECTORY_PICTURES);
        }

        Uri uri_ibtissam = null;
        try {
            uri_ibtissam = contentResolver_ibtissam.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values_ibtissam);
            if (uri_ibtissam != null) {
                try (OutputStream os_ibtissam = contentResolver_ibtissam.openOutputStream(uri_ibtissam)) {
                    if (os_ibtissam != null) {
                        bitmap_ibtissam.compress(Bitmap.CompressFormat.JPEG, 90, os_ibtissam);
                    }
                }
                
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                    values_ibtissam.clear();
                    values_ibtissam.put(MediaStore.Images.Media.IS_PENDING, 0);
                    contentResolver_ibtissam.update(uri_ibtissam, values_ibtissam, null, null);
                }
                return uri_ibtissam;
            }
        } catch (IOException e_ibtissam) {
            Log.e(TAG_ibtissam, "Erreur MediaStore: " + e_ibtissam.getMessage());
        }
        return null;
    }

    public List<Uri> getAllImages_ibtissam() {
        List<Uri> imageUris_ibtissam = new ArrayList<>();
        String[] projection_ibtissam = { MediaStore.Images.Media._ID };
        
        try (Cursor cursor_ibtissam = contentResolver_ibtissam.query(
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                projection_ibtissam,
                null, null,
                MediaStore.Images.Media.DATE_ADDED + " DESC")) {
            
            if (cursor_ibtissam != null) {
                int idColumn_ibtissam = cursor_ibtissam.getColumnIndexOrThrow(MediaStore.Images.Media._ID);
                while (cursor_ibtissam.moveToNext()) {
                    long id_ibtissam = cursor_ibtissam.getLong(idColumn_ibtissam);
                    Uri contentUri_ibtissam = Uri.withAppendedPath(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, String.valueOf(id_ibtissam));
                    imageUris_ibtissam.add(contentUri_ibtissam);
                }
            }
        }
        return imageUris_ibtissam;
    }
}
