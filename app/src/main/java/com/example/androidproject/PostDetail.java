package com.example.androidproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class PostDetail extends AppCompatActivity {
    private TextView title, nick, id, date, contents;
    private ListView comments;
    private TextInputLayout writeComment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_detail);

        title = (TextView) findViewById(R.id.postDetailTitle);
        nick = (TextView) findViewById(R.id.postDetailNick);
        id = (TextView) findViewById(R.id.postDetailID);
        date = (TextView) findViewById(R.id.postDetailDate);
        contents = (TextView) findViewById(R.id.postDetailContents);
        comments = (ListView) findViewById(R.id.postDetailComments);
        writeComment = (TextInputLayout) findViewById(R.id.postDetailWriteComment);
        CommentsAdapter adapter = new CommentsAdapter();
        comments.setAdapter(adapter);

        CommentItem item = new CommentItem();
        item.setNick("testNick");
        item.setContents("testContents");
        item.setDate("testDate");
        item.setId("testID");
        adapter.addItem(item);

        Intent intent = getIntent();
        title.setText(intent.getStringExtra("TITLE"));
        nick.setText(intent.getStringExtra("NICK"));
        id.setText(intent.getStringExtra("ID"));
        date.setText(intent.getStringExtra("DATE"));
        contents.setText(intent.getStringExtra("CONTENTS"));

        writeComment.setCounterMaxLength(200);
        writeComment.setCounterEnabled(true);
    }
}