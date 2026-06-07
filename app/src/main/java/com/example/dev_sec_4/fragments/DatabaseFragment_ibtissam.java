package com.example.dev_sec_4.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import com.example.dev_sec_4.R;
import com.example.dev_sec_4.activities.LoginActivity_ibtissam;

public class DatabaseFragment_ibtissam extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater_ibtissam, @Nullable ViewGroup container_ibtissam, @Nullable Bundle savedInstanceState_ibtissam) {
        View view_ibtissam = inflater_ibtissam.inflate(R.layout.fragment_database_ibtissam, container_ibtissam, false);
        
        Button btnGoToLogin_ibtissam = view_ibtissam.findViewById(R.id.btnGoToLogin_ibtissam);
        btnGoToLogin_ibtissam.setOnClickListener(v_ibtissam -> {
            Intent intent_ibtissam = new Intent(getActivity(), LoginActivity_ibtissam.class);
            startActivity(intent_ibtissam);
        });
        
        return view_ibtissam;
    }
}
