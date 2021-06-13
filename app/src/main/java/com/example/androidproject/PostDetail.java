package com.example.androidproject;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;

public class PostDetail extends AppCompatActivity {
    private TextView title, nick, id, date, contents;
    private ListView comments;
    private TextInputLayout writeComment;
    private Button btnCommentSend;
    private Handler handler = new Handler();
    private static String BOARD_ID;
    private static String POST_ID;
    private CommentsAdapter adapter = new CommentsAdapter();

    private void initiate() {
        title = (TextView) findViewById(R.id.postDetailTitle);
        nick = (TextView) findViewById(R.id.postDetailNick);
        id = (TextView) findViewById(R.id.postDetailID);
        date = (TextView) findViewById(R.id.postDetailDate);
        contents = (TextView) findViewById(R.id.postDetailContents);
        comments = (ListView) findViewById(R.id.postDetailComments);
        writeComment = (TextInputLayout) findViewById(R.id.postDetailWriteComment);
        btnCommentSend = (Button) findViewById(R.id.btnCommentSend);
        comments.setAdapter(adapter);

        Intent intent = getIntent();
        title.setText(intent.getStringExtra("TITLE"));
        nick.setText(intent.getStringExtra("NICK"));
        id.setText(intent.getStringExtra("ID"));
        date.setText(intent.getStringExtra("DATE"));
        contents.setText(intent.getStringExtra("CONTENTS"));

        BOARD_ID = intent.getStringExtra("BOARDID");
        POST_ID = intent.getStringExtra("POSTID");

        writeComment.setCounterMaxLength(200);
        writeComment.setCounterEnabled(true);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_detail);
        initiate();

        btnCommentSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        new GroupBoardDAO().sendComment(getApplicationContext(),
                                writeComment.getEditText().getText().toString(),
                                BOARD_ID,
                                POST_ID);
                    }
                }).start();
                Toast.makeText(PostDetail.this, "Send Comment.", Toast.LENGTH_SHORT).show();
                writeComment.getEditText().setText("");
                commentsRefresh();
            }
        });

        commentsRefresh();

    }

    private void commentsRefresh() {
        adapter.clearItem();
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    ArrayList<CommentItem> groupBoardItems = new GroupBoardDAO().loadComments(BOARD_ID, POST_ID);
                    for(CommentItem item : groupBoardItems) {
                        adapter.addItem(item);
                    }
                } catch (Exception e) {
                    Log.e("", "error", e);
                }
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        adapter.notifyDataSetChanged();
                        setListViewHeightBasedOnChildren(comments);
                    }
                });
            }
        }).start();
    }

    public static void setListViewHeightBasedOnChildren(ListView listView) {
        CommentsAdapter listAdaper = (CommentsAdapter) listView.getAdapter();
        if(listAdaper == null)
            return;

        int totalHeight = 0;
        int desiredWidth = View.MeasureSpec.makeMeasureSpec(listView.getWidth(), View.MeasureSpec.AT_MOST);

        for(int i = 0; i < listAdaper.getCount(); i++) {
            View listItem = listAdaper.getView(i, null, listView);
            listItem.measure(desiredWidth, View.MeasureSpec.UNSPECIFIED);
            totalHeight += listItem.getMeasuredHeight();
        }

        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height = totalHeight + (listView.getDividerHeight() * (listAdaper.getCount() - 1));
        listView.setLayoutParams(params);
        listView.requestLayout();
    }
}