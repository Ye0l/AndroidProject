package com.example.androidproject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.google.android.material.textfield.TextInputLayout;

// 첫화면: 로그인 상태 확인 필요

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextInputLayout etPwd = findViewById(R.id.etPwd);
    }
}