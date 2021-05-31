package com.example.androidproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.material.textfield.TextInputLayout;

// 첫화면: 로그인 상태 확인 필요

public class MainActivity extends AppCompatActivity {
    Button btnCreate, btnLogin;
    TextInputLayout tlID, tlPwd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tlID = findViewById(R.id.login_tlID);
        tlPwd = findViewById(R.id.login_tlPwd);

        btnCreate = findViewById(R.id.btnCreate);
        btnLogin = findViewById(R.id.btnLogin);
        btnCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), CreateAccount.class);
                startActivity(intent);
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DTO dto = new DTO(tlID.getEditText().getText().toString(), tlPwd.getEditText().getText().toString());

            }
        });

    }
}