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
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class GroupMain extends AppCompatActivity {
    private DrawerLayout drawerLayout;
    private View sideBar;
    private TextView groupName, memberCount, groupMainTitle;
    private FragmentManager fragmentManager = getSupportFragmentManager();
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
        groupMainTitle = findViewById(R.id.groupMainTitle);

        groupName.setText(getIntent().getStringExtra("TITLE"));
        memberCount.setText(getIntent().getStringExtra("COUNT"));

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
                        try {
                            for (SideBarMenuItem item : itemArrayList) {
                                adapter.addItem(item);
                            }

                            SideBarMenuItem settings = new SideBarMenuItem();
                            settings.setName("Settings");
                            adapter.addItem(settings);
                            adapter.notifyDataSetChanged();
                            Bundle bundle = new Bundle();
                            bundle.putInt("ID", Integer.valueOf(itemArrayList.get(0).getId()));
                            GroupBoard groupBoard = new GroupBoard();
                            groupBoard.setArguments(bundle);

                            groupMainTitle.setText(itemArrayList.get(0).getName());

                            FragmentTransaction transaction = fragmentManager.beginTransaction();
                            transaction.replace(R.id.groupMainFrame, groupBoard).commitAllowingStateLoss();
                        } catch (Exception e) {
                            Log.e("", "Error", e);
                        }
                    }
                });
            }
        }).start();

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(((SideBarMenuItem)parent.getItemAtPosition(position)).getName().equals("Settings")) {
                    groupMainTitle.setText("SETTINGS");
                    GroupSettings groupSettings = new GroupSettings();
                    FragmentTransaction transaction = fragmentManager.beginTransaction();
                    transaction.replace(R.id.groupMainFrame, groupSettings).commitAllowingStateLoss();
                } else {
                    Bundle bundle = new Bundle();
                    bundle.putInt("ID", Integer.valueOf(((SideBarMenuItem) parent.getItemAtPosition(position)).getId()));
                    groupMainTitle.setText(((SideBarMenuItem) parent.getItemAtPosition(position)).getName());

                    GroupBoard groupBoard = new GroupBoard();
                    groupBoard.setArguments(bundle);

                    FragmentTransaction transaction = fragmentManager.beginTransaction();
                    transaction.replace(R.id.groupMainFrame, groupBoard).commitAllowingStateLoss();

                }

                drawerLayout.closeDrawer(Gravity.LEFT);
            }
        });
    }
}