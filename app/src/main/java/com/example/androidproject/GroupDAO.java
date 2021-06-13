package com.example.androidproject;

import android.util.Log;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class GroupDAO extends DAO {
    public ArrayList<GroupDTO> getGroupList() {
        try {
            URL setURL = new URL("http://klure.dothome.co.kr/getGroupList.php");
            HttpURLConnection http = getConnection(setURL);
            OutputStreamWriter outStream = new OutputStreamWriter(http.getOutputStream(), "utf-8");
            outStream.flush();
            InputStreamReader tmp = new InputStreamReader(http.getInputStream(), "UTF-8");
            final BufferedReader reader = new BufferedReader(tmp);
            ArrayList<GroupDTO> dtos = new ArrayList<GroupDTO>();
            String str;
            while ((str = reader.readLine()) != null) {
                GroupDTO item = new GroupDTO();
                String[] input = str.split("/");
                item.setId(Integer.valueOf(input[0]));
                item.setName(input[1]);
                item.setIntro(input[2]);
                item.setMemberCount(Integer.valueOf(input[3]));

                dtos.add(item);
            }

            return dtos;
        } catch (Exception e) {
            Log.e("", "Error", e);
        }
        return null;

    }

    public ArrayList<SideBarMenuItem> getSideBarMenuItems(Integer groupID) {
        try {
            URL setURL = new URL("http://klure.dothome.co.kr/getSideBarMenuItems.php");
            HttpURLConnection http = getConnection(setURL);
            OutputStreamWriter outStream = new OutputStreamWriter(http.getOutputStream(), "utf-8");
            outStream.write("groupID=" + String.valueOf(groupID));
            outStream.flush();
            InputStreamReader tmp = new InputStreamReader(http.getInputStream(), "UTF-8");
            final BufferedReader reader = new BufferedReader(tmp);
            ArrayList<SideBarMenuItem> sideBarMenuItems = new ArrayList<SideBarMenuItem>();
            String str;
            while ((str = reader.readLine()) != null) {
                System.out.println("fsdafasdfasd"+str);
                String[] data = str.split("/");
                SideBarMenuItem item = new SideBarMenuItem();
                item.setId(data[0]);
                item.setName(data[1]);

                sideBarMenuItems.add(item);
            }

            return sideBarMenuItems;
        } catch (Exception e) {
            Log.e("", "Error", e);
        }
        return null;
    }

    public ArrayList<CommentItem> getComments(Integer boardID, Integer PostID) {
        return null;
    }
}
