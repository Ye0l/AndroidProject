package com.example.androidproject;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
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

        ListView boardListView = view.findViewById(R.id.group_board_lv);
        GroupBoardAdapter boardAdapter = new GroupBoardAdapter();
        boardListView.setAdapter(boardAdapter);
        new Thread() {
            @Override
            public void run() {
                try {
                    final ArrayList<GroupBoardItem> itemArrayList = new GroupBoardDAO().loadPostList(getArguments().getInt("ID"));
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            boardAdapter.clearItem();
                            for(GroupBoardItem item : itemArrayList) {
                                boardAdapter.addItem(item);
                            }
                            boardAdapter.notifyDataSetChanged();
                        }
                    });
                } catch (Exception e) {
                    Log.e("", "error: ", e);
                }
            }
        }.start();
        boardListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                
            }
        });

        return view;
    }
}