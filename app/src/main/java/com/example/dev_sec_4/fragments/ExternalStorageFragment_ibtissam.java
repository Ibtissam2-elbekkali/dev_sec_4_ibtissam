package com.example.dev_sec_4.fragments;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.example.dev_sec_4.R;
import com.example.dev_sec_4.storage.ExternalStorageManager_ibtissam;

public class ExternalStorageFragment_ibtissam extends Fragment {

    private static final int PERMISSION_REQUEST_CODE_ibtissam = 123;
    private EditText etExtFilename_ibtissam;
    private EditText etExtContent_ibtissam;
    private TextView tvExtFileContent_ibtissam;
    private ExternalStorageManager_ibtissam storageManager_ibtissam;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater_ibtissam, @Nullable ViewGroup container_ibtissam, @Nullable Bundle savedInstanceState_ibtissam) {
        View view_ibtissam = inflater_ibtissam.inflate(R.layout.fragment_external_storage_ibtissam, container_ibtissam, false);
        
        storageManager_ibtissam = new ExternalStorageManager_ibtissam(requireContext());
        
        etExtFilename_ibtissam = view_ibtissam.findViewById(R.id.etExtFilename_ibtissam);
        etExtContent_ibtissam = view_ibtissam.findViewById(R.id.etExtContent_ibtissam);
        tvExtFileContent_ibtissam = view_ibtissam.findViewById(R.id.tvExtFileContent_ibtissam);
        
        Button btnSave_ibtissam = view_ibtissam.findViewById(R.id.btnExtSave_ibtissam);
        Button btnLoad_ibtissam = view_ibtissam.findViewById(R.id.btnExtLoad_ibtissam);
        
        etExtFilename_ibtissam.setText("external_ibtissam.txt");
        
        btnSave_ibtissam.setOnClickListener(v_ibtissam -> {
            if (checkPermissions_ibtissam()) {
                String filename_ibtissam = etExtFilename_ibtissam.getText().toString();
                String content_ibtissam = etExtContent_ibtissam.getText().toString();
                if (storageManager_ibtissam.writeToExternalStorage_ibtissam(filename_ibtissam, content_ibtissam, Environment.DIRECTORY_DOCUMENTS)) {
                    Toast.makeText(getContext(), "Sauvegardé sur SD !", Toast.LENGTH_SHORT).show();
                }
            }
        });
        
        btnLoad_ibtissam.setOnClickListener(v_ibtissam -> {
            if (checkPermissions_ibtissam()) {
                String filename_ibtissam = etExtFilename_ibtissam.getText().toString();
                String content_ibtissam = storageManager_ibtissam.readFromExternalStorage_ibtissam(filename_ibtissam, Environment.DIRECTORY_DOCUMENTS);
                tvExtFileContent_ibtissam.setText(content_ibtissam != null ? content_ibtissam : "Fichier introuvable");
            }
        });
        
        return view_ibtissam;
    }
    
    private boolean checkPermissions_ibtissam() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) return true;
        if (ContextCompat.checkSelfPermission(requireContext(), Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(requireActivity(), new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, PERMISSION_REQUEST_CODE_ibtissam);
            return false;
        }
        return true;
    }
}
