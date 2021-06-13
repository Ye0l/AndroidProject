package com.example.androidproject;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;

import java.util.ArrayList;

public class SideBarMenuItemAdapter extends BaseAdapter {
    ArrayList<SideBarMenuItem> sideBarMenuItems = new ArrayList<SideBarMenuItem>();

    @Override
    public int getCount() {
        return sideBarMenuItems.size();
    }

    @Override
    public Object getItem(int position) {
        return sideBarMenuItems.get(position);
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
            convertView = inflater.inflate(R.layout.side_bar_menu_item, parent, false);
        }

        Button menuItem = (Button) convertView.findViewById(R.id.sideBarMenuItem);

        SideBarMenuItem item = sideBarMenuItems.get(position);

        menuItem.setText(item.getName());

        return convertView;
    }

    public void addItem(SideBarMenuItem item) {
        sideBarMenuItems.add(item);

    }

    public void clearItem() {
        sideBarMenuItems.clear();
    }
}
