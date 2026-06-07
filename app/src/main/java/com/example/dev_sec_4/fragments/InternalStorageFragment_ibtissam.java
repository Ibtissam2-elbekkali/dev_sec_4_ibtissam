package com.example.dev_sec_4.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.dev_sec_4.R;
import com.example.dev_sec_4.storage.InternalStorageManager_ibtissam;

public class InternalStorageFragment_ibtissam extends Fragment {

    private EditText etFilename_ibtissam;
    private EditText etContent_ibtissam;
    private TextView tvFileContent_ibtissam;
    private InternalStorageManager_ibtissam storageManager_ibtissam;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater_ibtissam, @Nullable ViewGroup container_ibtissam, @Nullable Bundle savedInstanceState_ibtissam) {
        View view_ibtissam = inflater_ibtissam.inflate(R.layout.fragment_internal_storage_ibtissam, container_ibtissam, false);
        
        storageManager_ibtissam = new InternalStorageManager_ibtissam(requireContext());
        
        etFilename_ibtissam = view_ibtissam.findViewById(R.id.etFilename_ibtissam);
        etContent_ibtissam = view_ibtissam.findViewById(R.id.etContent_ibtissam);
        tvFileContent_ibtissam = view_ibtissam.findViewById(R.id.tvFileContent_ibtissam);
        
        Button btnSave_ibtissam = view_ibtissam.findViewById(R.id.btnSave_ibtissam);
        Button btnLoad_ibtissam = view_ibtissam.findViewById(R.id.btnLoad_ibtissam);
        
        etFilename_ibtissam.setText("notes_ibtissam.txt");
        
        btnSave_ibtissam.setOnClickListener(v_ibtissam -> {
            String filename_ibtissam = etFilename_ibtissam.getText().toString();
            String content_ibtissam = etContent_ibtissam.getText().toString();
            if (storageManager_ibtissam.writeToInternalStorage_ibtissam(filename_ibtissam, content_ibtissam)) {
                Toast.makeText(getContext(), "Sauvegardé !", Toast.LENGTH_SHORT).show();
            }
        });
        
        btnLoad_ibtissam.setOnClickListener(v_ibtissam -> {
            String filename_ibtissam = etFilename_ibtissam.getText().toString();
            String content_ibtissam = storageManager_ibtissam.readFromInternalStorage_ibtissam(filename_ibtissam);
            tvFileContent_ibtissam.setText(content_ibtissam != null ? content_ibtissam : "Fichier vide");
        });
        
        return view_ibtissam;
    }
}
