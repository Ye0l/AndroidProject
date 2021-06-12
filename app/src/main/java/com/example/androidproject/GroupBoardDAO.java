package com.example.androidproject;

import android.util.Log;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class GroupBoardDAO extends DAO{
    public ArrayList<GroupBoardItem> loadPostList() {
        try {
            URL setURL = new URL("http://klure.dothome.co.kr/loadPostList.php");
            HttpURLConnection http = getConnection(setURL);
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(http.getOutputStream(), "utf-8");
            outputStreamWriter.flush();
            InputStreamReader tmp = new InputStreamReader(http.getInputStream(), "utf-8");
            final BufferedReader reader = new BufferedReader(tmp);
            ArrayList<GroupBoardItem> itemArrayList = new ArrayList<GroupBoardItem>();
            String readitem;
            while ((readitem = reader.readLine()) != null) {
                GroupBoardItem item = new GroupBoardItem();
                String[] str = readitem.split("/");
                System.out.println(readitem);
                item.setTitle("@" + str[0]);
                item.setWriter(str[1]);
                item.setDate(str[2]);
                item.setContents(str[3].length() > 50 ? str[3].substring(0,50) + "..." : str[3]);

                itemArrayList.add(item);
            }

            return itemArrayList;
        } catch (Exception e) {
            Log.e("", "Error", e);
        }
        return null;
    }
}
