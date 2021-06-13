package com.example.androidproject;

import android.content.Intent;
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
    ArrayList<GroupBoardItem> groupBoardItems = new ArrayList<GroupBoardItem>();

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
                final ArrayList<GroupBoardItem> itemArrayList = new GroupBoardDAO().loadPostList(getArguments().getInt("ID"));
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            boardAdapter.clearItem();
                            for(GroupBoardItem item : itemArrayList) {
                                boardAdapter.addItem(item);
                                groupBoardItems.add(item);
                            }
                            boardAdapter.notifyDataSetChanged();
                        } catch (Exception e) {
                            Log.e("", "error: ", e);
                        }
                    }
                });
            }
        }.start();

        boardListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getContext(), PostDetail.class);
                GroupBoardItem item = groupBoardItems.get(position);
                intent.putExtra("TITLE", item.getTitle());
                intent.putExtra("NICK", item.getWriter());
                intent.putExtra("ID", item.getId());
                intent.putExtra("DATE", item.getDate());
                intent.putExtra("CONTENTS", item.getContents());
                intent.putExtra("BOARDID", String.valueOf(getArguments().getInt("ID")));
                intent.putExtra("POSTID", String.valueOf(position+1)); // ArrayList는 0부터 시작하는데 MYSQL AUTO_INCREASMENT 는 1부터 시작함.

                startActivity(intent);
            }
        });

        return view;
    }
}