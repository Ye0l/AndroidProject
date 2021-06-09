package com.example.androidproject;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class GroupBoardAdapter extends BaseAdapter {
    ArrayList<GroupBoardItem> groupBoardItems = new ArrayList<GroupBoardItem>();

    @Override
    public int getCount() {
        return groupBoardItems.size();
    }

    @Override
    public Object getItem(int position) {
        return groupBoardItems.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final int pos = position;
        final Context context = parent.getContext();

        if(convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.group_board_item, parent, false);
        }

        TextView title = (TextView) convertView.findViewById(R.id.group_board_title);
        TextView writer = (TextView) convertView.findViewById(R.id.group_board_writer);
        TextView date = (TextView) convertView.findViewById(R.id.group_board_date);
        TextView contents = (TextView) convertView.findViewById(R.id.group_board_contents);

        GroupBoardItem item = groupBoardItems.get(position);

        title.setText(item.getTitle());
        writer.setText(item.getWriter());
        date.setText(item.getDate());
        contents.setText(item.getContents());

        return convertView;
    }

    public void addItem() {
//        게시판용 DTO만들고 addItem마무리하기
    }
}