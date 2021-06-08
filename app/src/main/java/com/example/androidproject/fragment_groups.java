package com.example.androidproject;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

public class fragment_groups extends Fragment {
    ListView listView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_groups, container, false);

        GroupListAdapter adapter = new GroupListAdapter();
        listView = view.findViewById(R.id.groups_lv);
        listView.setAdapter(adapter);

        String[] groupNames = {"Sed auctor eget quam.", "Aliquam aliquam purus pellentesque.", "Cras scelerisque nulla quis.", "Mauris quis vehicula mauris."};
        Integer[] memberCounts = {54, 8, 9, 32};
        String[] groupIntros = {"Aenean tincidunt ligula in metus euismod, id posuere felis lacinia.", "Maecenas suscipit odio sit amet erat varius condimentum.", "Morbi ultrices dui vel sollicitudin congue.", "Curabitur a mauris sit amet nisl pharetra aliquet quis ut nisl."};

        for(int i = 0; i < groupNames.length; i++) {
            adapter.addItem(groupNames[i], memberCounts[i], groupIntros[i]);
        }

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(view.getContext(), GroupMain.class);
                GroupListItem item = (GroupListItem) parent.getItemAtPosition(position);
                intent.putExtra("TITLE", item.getName());
                intent.putExtra("COUNT", String.valueOf(item.getMembercount()));
                intent.putExtra("INTRO", item.getIntroduce());
                startActivity(intent);
            }
        });

//        Button btn = view.findViewById(R.id.button3);
//        btn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(view.getContext(), GroupMain.class);
//                startActivity(intent);
//            }
//        });


        return view;
    }
}