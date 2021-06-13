package com.example.androidproject;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class CommentsAdapter extends BaseAdapter {
    ArrayList<CommentItem> commentItemArrayList = new ArrayList<CommentItem>();
    @Override
    public int getCount() {
        return commentItemArrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return commentItemArrayList.get(position);
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
            convertView = inflater.inflate(R.layout.comment_item, parent, false);
        }

        TextView nick = (TextView) convertView.findViewById(R.id.commentNick);
        TextView id = (TextView) convertView.findViewById(R.id.commentID);
        TextView date = (TextView) convertView.findViewById(R.id.commentDate);
        TextView contents = (TextView) convertView.findViewById(R.id.commentContents);

        CommentItem item = commentItemArrayList.get(position);

        nick.setText(item.getNick());
        id.setText(item.getId());
        date.setText(item.getDate());
        contents.setText(item.getContents());

        return convertView;
    }

    public void addItem(CommentItem item) {
        commentItemArrayList.add(item);
    }

    public void clearItem() {
        commentItemArrayList.clear();
    }
}
