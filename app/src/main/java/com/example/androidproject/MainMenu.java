package com.example.androidproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.zip.Inflater;

public class MainMenu extends AppCompatActivity {
    private FragmentManager fragmentManager = getSupportFragmentManager();
    private fragment_feed fragmentFeed = new fragment_feed();
    private fragment_alert fragmentAlert = new fragment_alert();
    private fragment_profile fragmentProfile = new fragment_profile();
    private fragment_search fragmentSearch = new fragment_search();
    private fragment_settings fragmentSettings = new fragment_settings();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

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
                        break;
                    case R.id.feed:
                        transaction1.replace(R.id.frameLayout, fragmentFeed).commitAllowingStateLoss();
                        break;
                    case R.id.alert:
                        transaction1.replace(R.id.frameLayout, fragmentAlert).commitAllowingStateLoss();
                        break;
                    case R.id.profile:
                        transaction1.replace(R.id.frameLayout, fragmentProfile).commitAllowingStateLoss();
                        break;
                    case R.id.settings:
                        transaction1.replace(R.id.frameLayout, fragmentSettings).commitAllowingStateLoss();
                        break;
                }
                return true;
            }
        });
    }
}