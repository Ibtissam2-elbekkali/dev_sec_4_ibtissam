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
import com.example.dev_sec_4.security.EncryptedFileManager_ibtissam;

public class EncryptedFileFragment_ibtissam extends Fragment {

    private EditText etEncFilename_ibtissam;
    private EditText etEncContent_ibtissam;
    private TextView tvEncDisplay_ibtissam;
    private EncryptedFileManager_ibtissam encryptedFileManager_ibtissam;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater_ibtissam, @Nullable ViewGroup container_ibtissam, @Nullable Bundle savedInstanceState_ibtissam) {
        View view_ibtissam = inflater_ibtissam.inflate(R.layout.fragment_encrypted_file_ibtissam, container_ibtissam, false);
        
        encryptedFileManager_ibtissam = new EncryptedFileManager_ibtissam(requireContext());
        
        etEncFilename_ibtissam = view_ibtissam.findViewById(R.id.etEncFilename_ibtissam);
        etEncContent_ibtissam = view_ibtissam.findViewById(R.id.etEncContent_ibtissam);
        tvEncDisplay_ibtissam = view_ibtissam.findViewById(R.id.tvEncDisplay_ibtissam);
        
        Button btnEncWrite_ibtissam = view_ibtissam.findViewById(R.id.btnEncWrite_ibtissam);
        Button btnEncRead_ibtissam = view_ibtissam.findViewById(R.id.btnEncRead_ibtissam);
        
        etEncFilename_ibtissam.setText("secret_ibtissam.enc");
        
        btnEncWrite_ibtissam.setOnClickListener(v_ibtissam -> {
            String filename_ibtissam = etEncFilename_ibtissam.getText().toString();
            String content_ibtissam = etEncContent_ibtissam.getText().toString();
            if (encryptedFileManager_ibtissam.writeToEncryptedFile_ibtissam(filename_ibtissam, content_ibtissam)) {
                Toast.makeText(getContext(), "Fichier chiffré !", Toast.LENGTH_SHORT).show();
                etEncContent_ibtissam.setText("");
            }
        });
        
        btnEncRead_ibtissam.setOnClickListener(v_ibtissam -> {
            String filename_ibtissam = etEncFilename_ibtissam.getText().toString();
            String decryptedContent_ibtissam = encryptedFileManager_ibtissam.readFromEncryptedFile_ibtissam(filename_ibtissam);
            tvEncDisplay_ibtissam.setText(decryptedContent_ibtissam != null ? decryptedContent_ibtissam : "Échec du déchiffrement");
        });
        
        return view_ibtissam;
    }
}
