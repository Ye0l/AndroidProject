package com.example.androidproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;

public class GroupBoardWrite extends AppCompatActivity {
    private TextInputLayout etTitle, etContents;
    private Button btnSend;
    private Intent intent;
    private static String BOARD_ID, ACCOUNT_ID;
    private Handler handler = new Handler();

    private void initiate() {
        etTitle = findViewById(R.id.groupBoardWriteTitle);
        etContents = findViewById(R.id.groupBoardWriteContents);
        btnSend = findViewById(R.id.groupBoardWriteSend);

        etTitle.setCounterMaxLength(50);
        etTitle.setCounterEnabled(true);
        etContents.setCounterMaxLength(1000);
        etContents.setCounterEnabled(true);

        intent = getIntent();

        BOARD_ID = intent.getStringExtra("BOARDID");
        ACCOUNT_ID  = PreferenceManager.getString(getApplicationContext(), "id");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group_board_write);

        initiate();

        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        new GroupBoardDAO().writePost(BOARD_ID, ACCOUNT_ID, etTitle.getEditText().getText().toString(), etContents.getEditText().getText().toString());
                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(GroupBoardWrite.this, "POST.", Toast.LENGTH_SHORT).show();
                                finish();
                            }
                        });
                    }
                }).start();
            }
        });
    }
}