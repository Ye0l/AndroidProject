package com.example.androidproject;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class GroupBoardDAO extends DAO{
    public ArrayList<GroupBoardItem> loadPostList(Integer groupID) {
        try {
            URL setURL = new URL("http://klure.dothome.co.kr/loadPostList.php");
            HttpURLConnection http = getConnection(setURL);
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(http.getOutputStream(), "utf-8");
            outputStreamWriter.write("groupID=" + groupID);
            outputStreamWriter.flush();
            InputStreamReader tmp = new InputStreamReader(http.getInputStream(), "utf-8");
            final BufferedReader reader = new BufferedReader(tmp);
            ArrayList<GroupBoardItem> itemArrayList = new ArrayList<GroupBoardItem>();
            String readitem;
            while ((readitem = reader.readLine()) != null) {
                GroupBoardItem item = new GroupBoardItem();
                String[] str = readitem.split("/");
                System.out.println(readitem);
                item.setTitle(str[0]);
                item.setWriter(str[1]);
                item.setId("@" + str[2]);
                item.setDate(str[3]);
                item.setContents(str[4]);
                item.setPostID(str[5]);

                itemArrayList.add(item);
            }

            return itemArrayList;
        } catch (Exception e) {
            Log.e("", "Error", e);
        }
        return null;
    }

    public void sendComment(Context mContext, String contents, String boardID, String postID) {
        try {
            URL setURL = new URL("http://klure.dothome.co.kr/sendComment.php");
            HttpURLConnection http = getConnection(setURL);
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(http.getOutputStream(), "utf-8");
            outputStreamWriter.write("data="
                    + boardID + "/"
                    + postID + "/"
                    + PreferenceManager.getString(mContext.getApplicationContext(), "id") + "/"
                    + contents);
            outputStreamWriter.flush();
            InputStreamReader tmp = new InputStreamReader(http.getInputStream(), "utf-8");
            final BufferedReader reader = new BufferedReader(tmp);
            String readitem;
            while ((readitem = reader.readLine()) != null) {
                System.out.println(readitem);
            }
        } catch (Exception e) {
            Log.e("", "Error", e);
        }
    }

    public ArrayList<CommentItem> loadComments(String boardID, String postID) {
        try {
            URL setURL = new URL("http://klure.dothome.co.kr/loadComments.php");
            HttpURLConnection http = getConnection(setURL);
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(http.getOutputStream(), "utf-8");
            outputStreamWriter.write("data=" + boardID + "/" + postID);
            outputStreamWriter.flush();
            InputStreamReader tmp = new InputStreamReader(http.getInputStream(), "utf-8");
            final BufferedReader reader = new BufferedReader(tmp);
            ArrayList<CommentItem> itemArrayList = new ArrayList<CommentItem>();
            String readitem;
            while ((readitem = reader.readLine()) != null) {
                CommentItem item = new CommentItem();
                String[] str = readitem.split("/");
                System.out.println(readitem);
                item.setId(str[0]);
                item.setNick(str[1]);
                item.setDate(str[2]);
                item.setContents(str[3]);

                itemArrayList.add(item);
            }

            return itemArrayList;
        } catch (Exception e) {
            Log.e("", "Error", e);
        }
        return null;
    }

    public void writePost(String boardID, String accountID, String title, String contents) {
        try {
            URL setURL = new URL("http://klure.dothome.co.kr/writePost.php");
            HttpURLConnection http = getConnection(setURL);
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(http.getOutputStream(), "utf-8");
            outputStreamWriter.write("data="
                    + boardID + "/"
                    + accountID + "/"
                    + title + "/"
                    + contents);
            outputStreamWriter.flush();
            InputStreamReader tmp = new InputStreamReader(http.getInputStream(), "utf-8");
            final BufferedReader reader = new BufferedReader(tmp);
            String readitem;
            while ((readitem = reader.readLine()) != null) {
                System.out.println(readitem);
            }
        } catch (Exception e) {
            Log.e("", "Error", e);
        }

    }
}
