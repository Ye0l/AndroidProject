package com.example.androidproject;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class GroupListAdapter extends BaseAdapter {
    ArrayList<GroupListItem> groupListItems = new ArrayList<GroupListItem>();

    @Override
    public int getCount() {
        return groupListItems.size();
    }

    @Override
    public Object getItem(int position) {
        return groupListItems.get(position);
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
            convertView = inflater.inflate(R.layout.group_listview_item, parent, false);
        }

        TextView groupTitle = (TextView) convertView.findViewById(R.id.listItemGroupTitle);
        TextView memberCount = (TextView) convertView.findViewById(R.id.listItemMemberCount);
        TextView groupIntro = (TextView) convertView.findViewById(R.id.listItemGroupIntro);

        GroupListItem groupListItem = groupListItems.get(position);

        groupTitle.setText(groupListItem.getName());
        memberCount.setText(String.valueOf(groupListItem.getMembercount()));
        groupIntro.setText(groupListItem.getIntroduce());

        return convertView;
    }

    public void addItem(GroupDTO dto) {
        GroupListItem item = new GroupListItem();

        item.setName(dto.getName());
        item.setMembercount(dto.getMemberCount());
        item.setIntroduce(dto.getIntro());

        groupListItems.add(item);
    }

    public void clearItem() {
        groupListItems.clear();
    }
}
