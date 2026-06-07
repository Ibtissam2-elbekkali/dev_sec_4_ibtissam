package com.example.dev_sec_4.activities;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;
import com.example.dev_sec_4.R;
import com.example.dev_sec_4.adapters.MainPagerAdapter_ibtissam;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class MainActivity extends AppCompatActivity {

    private TabLayout tabLayout_ibtissam;
    private ViewPager2 viewPager_ibtissam;

    @Override
    protected void onCreate(Bundle savedInstanceState_ibtissam) {
        super.onCreate(savedInstanceState_ibtissam);
        setContentView(R.layout.activity_main);

        tabLayout_ibtissam = findViewById(R.id.tabLayout_ibtissam);
        viewPager_ibtissam = findViewById(R.id.viewPager_ibtissam);

        MainPagerAdapter_ibtissam adapter_ibtissam = new MainPagerAdapter_ibtissam(this);
        viewPager_ibtissam.setAdapter(adapter_ibtissam);

        new TabLayoutMediator(tabLayout_ibtissam, viewPager_ibtissam, (tab_ibtissam, position_ibtissam) -> {
            switch (position_ibtissam) {
                case 0: tab_ibtissam.setText(getString(R.string.tab_internal_ibtissam)); break;
                case 1: tab_ibtissam.setText(getString(R.string.tab_external_ibtissam)); break;
                case 2: tab_ibtissam.setText(getString(R.string.tab_room_ibtissam)); break;
                case 3: tab_ibtissam.setText(getString(R.string.tab_encrypted_ibtissam)); break;
            }
        }).attach();
    }
}
