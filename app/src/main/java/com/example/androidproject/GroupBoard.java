package com.example.androidproject;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class GroupBoard extends Fragment {
    Handler handler = new Handler();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_group_board, container, false);
        ListView listView = view.findViewById(R.id.group_board_lv);
        GroupBoardAdapter adapter = new GroupBoardAdapter();
        listView.setAdapter(adapter);
        new Thread() {
            @Override
            public void run() {
                try {
                    ArrayList<GroupBoardItem> itemArrayList = new GroupBoardDAO().loadPostList();
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            adapter.clearItem();
                            for(GroupBoardItem item : itemArrayList) {
                                adapter.addItem(item);
                            }
                            adapter.notifyDataSetChanged();
                            Toast.makeText(view.getContext(), "ok", Toast.LENGTH_SHORT).show();
                        }
                    });
                } catch (Exception e) {
                    Log.e("", "error: ", e);
                }
            }
        }.start();

        return view;
    }
}