package com.example.dev_sec_4.adapters;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.dev_sec_4.fragments.DatabaseFragment_ibtissam;
import com.example.dev_sec_4.fragments.EncryptedFileFragment_ibtissam;
import com.example.dev_sec_4.fragments.ExternalStorageFragment_ibtissam;
import com.example.dev_sec_4.fragments.InternalStorageFragment_ibtissam;

public class MainPagerAdapter_ibtissam extends FragmentStateAdapter {

    public MainPagerAdapter_ibtissam(@NonNull FragmentActivity fragmentActivity_ibtissam) {
        super(fragmentActivity_ibtissam);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position_ibtissam) {
        switch (position_ibtissam) {
            case 0:
                return new InternalStorageFragment_ibtissam();
            case 1:
                return new ExternalStorageFragment_ibtissam();
            case 2:
                return new DatabaseFragment_ibtissam();
            case 3:
                return new EncryptedFileFragment_ibtissam();
            default:
                return new InternalStorageFragment_ibtissam();
        }
    }

    @Override
    public int getItemCount() {
        return 4;
    }
}
