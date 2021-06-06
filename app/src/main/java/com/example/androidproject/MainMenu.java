package com.example.androidproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainMenu extends AppCompatActivity {
    private TextView tvTitle;
    private FragmentManager fragmentManager = getSupportFragmentManager();
    private fragment_feed fragmentFeed = new fragment_feed();
    private fragment_alert fragmentAlert = new fragment_alert();
    private fragment_profile fragmentProfile = new fragment_profile();
    private fragment_groups fragmentSearch = new fragment_groups();
    private fragment_settings fragmentSettings = new fragment_settings();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        tvTitle = findViewById(R.id.tvTitle);
        tvTitle.setText("FEED");

        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.frameLayout, fragmentFeed).commitAllowingStateLoss();

        BottomNavigationView bottomNavigationView = findViewById(R.id.NavView);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                FragmentTransaction transaction1 = fragmentManager.beginTransaction();

                switch (item.getItemId()) {
                    case R.id.search:
                        transaction1.replace(R.id.frameLayout, fragmentSearch).commitAllowingStateLoss();
                        tvTitle.setText("GROUPS");
                        break;
                    case R.id.feed:
                        transaction1.replace(R.id.frameLayout, fragmentFeed).commitAllowingStateLoss();
                        tvTitle.setText("FEED");
                        break;
                    case R.id.alert:
                        transaction1.replace(R.id.frameLayout, fragmentAlert).commitAllowingStateLoss();
                        tvTitle.setText("ALERT");
                        break;
                    case R.id.profile:
                        transaction1.replace(R.id.frameLayout, fragmentProfile).commitAllowingStateLoss();
                        tvTitle.setText("PROFILE");
                        break;
                    case R.id.settings:
                        transaction1.replace(R.id.frameLayout, fragmentSettings).commitAllowingStateLoss();
                        tvTitle.setText("SETTINGS");
                        break;
                }
                return true;
            }
        });
    }
}