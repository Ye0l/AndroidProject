package com.example.androidproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;

// 첫화면: 로그인 상태 확인 필요

public class MainActivity extends AppCompatActivity {
    Handler handler = new Handler();
    Button btnCreate, btnLogin;
    TextInputLayout tlID, tlPwd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DAO dao = new DAO();
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
                new Thread() {
                    @Override
                    public void run() {
                        DTO result = dao.login(dto);
                        if(result != null)
                            handler.post(new Runnable() {
                                @Override
                                public void run() {
                                    Toast.makeText(MainActivity.this, "로그인 성공!", Toast.LENGTH_SHORT).show();
                                    PreferenceManager.setDTO(MainActivity.this, "login", result);
                                    Intent intent = new Intent(getApplicationContext(), MainMenu.class);
                                    startActivity(intent);
                                }
                            });
                    }
                }.start();
            }
        });

    }
}