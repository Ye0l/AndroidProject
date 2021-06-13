package com.example.androidproject;

import android.content.Context;
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

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class GroupBoard extends Fragment {
    private Handler handler = new Handler();
    private ArrayList<GroupBoardItem> groupBoardItems = new ArrayList<GroupBoardItem>();
    private GroupBoardAdapter boardAdapter = new GroupBoardAdapter();
    private FloatingActionButton btnWrite;

    @Override
    public void onResume() {
        super.onResume();
        boardRefresh();
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_group_board, container, false);

        btnWrite = view.findViewById(R.id.groupBoardBtnWrite);
        ListView boardListView = view.findViewById(R.id.group_board_lv);
        boardListView.setAdapter(boardAdapter);

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
                intent.putExtra("POSTID", item.getPostID());

                startActivity(intent);
            }
        });
        
        btnWrite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), GroupBoardWrite.class);
                intent.putExtra("BOARDID", String.valueOf(getArguments().getInt("ID")));
                startActivity(intent);
            }
        });

        return view;
    }

    public void boardRefresh() {
        new Thread() {
            @Override
            public void run() {
                final ArrayList<GroupBoardItem> itemArrayList = new GroupBoardDAO().loadPostList(getArguments().getInt("ID"));
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            boardAdapter.clearItem();
                            groupBoardItems.clear();
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
    }
}