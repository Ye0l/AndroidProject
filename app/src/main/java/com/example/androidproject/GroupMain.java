package com.example.androidproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class GroupMain extends AppCompatActivity {
    private DrawerLayout drawerLayout;
    private View sideBar;
    private TextView groupName, memberCount;
    private FragmentManager fragmentManager = getSupportFragmentManager();
    private GroupBoard groupBoard = new GroupBoard();
    private Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group_main);

        Intent intent = getIntent();

        drawerLayout = (DrawerLayout)findViewById(R.id.group_dl);
        sideBar = (View)findViewById(R.id.sideBar);
        groupName = findViewById(R.id.sideBarGroupName);
        memberCount = findViewById(R.id.sideBarMemberCount);

        groupName.setText(getIntent().getStringExtra("TITLE"));
        memberCount.setText(getIntent().getStringExtra("COUNT"));

        Bundle bundle = new Bundle();
        bundle.putInt("ID", intent.getIntExtra("ID", 0));
        groupBoard.setArguments(bundle);
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

        ListView listView = findViewById(R.id.sideBarMenuLv);
        SideBarMenuItemAdapter adapter = new SideBarMenuItemAdapter();
        listView.setAdapter(adapter);

        new Thread(new Runnable() {
            @Override
            public void run() {
                final ArrayList<SideBarMenuItem> itemArrayList = new GroupDAO().getSideBarMenuItems(intent.getIntExtra("ID", 0));
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        adapter.clearItem();
                        for (SideBarMenuItem item : itemArrayList) {
                            adapter.addItem(item);
                        }
                        adapter.notifyDataSetChanged();
                    }
                });
            }
        }).start();
    }
}