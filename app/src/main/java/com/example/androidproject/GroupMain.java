package com.example.androidproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

public class GroupMain extends AppCompatActivity {
    private DrawerLayout drawerLayout;
    private View sideBar;
    private TextView groupName, memberCount;
    private FragmentManager fragmentManager = getSupportFragmentManager();
    private GroupBoard groupBoard = new GroupBoard();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group_main);

        drawerLayout = (DrawerLayout)findViewById(R.id.group_dl);
        sideBar = (View)findViewById(R.id.sideBar);
        groupName = findViewById(R.id.sideBarGroupName);
        memberCount = findViewById(R.id.sideBarMemberCount);

        groupName.setText(getIntent().getStringExtra("TITLE"));
        memberCount.setText(getIntent().getStringExtra("COUNT"));

        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.groupMainFrame, groupBoard).commitAllowingStateLoss();

        drawerLayout.addDrawerListener(new DrawerLayout.DrawerListener() {
            @Override
            public void onDrawerSlide(@NonNull View drawerView, float slideOffset) {

            }

            @Override
            public void onDrawerOpened(@NonNull View drawerView) {

            }

            @Override
            public void onDrawerClosed(@NonNull View drawerView) {

            }

            @Override
            public void onDrawerStateChanged(int newState) {

            }
        });
    }
}