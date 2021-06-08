package com.example.androidproject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;

public class CreateAccount extends AppCompatActivity {
    Handler handler = new Handler();
    boolean[] submitCheck = {false, false, false};
    Button btnSubmit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);

        btnSubmit = findViewById(R.id.btnSubmit);
        final TextInputLayout tlID = findViewById(R.id.login_tlID);
        final TextInputLayout tlPwd = findViewById(R.id.tlPwd);
        final TextInputLayout tlPwdCheck = findViewById(R.id.tlPwdCheck);
        final TextInputLayout tlNick = findViewById(R.id.tlNick);
        final TextInputLayout tlName = findViewById(R.id.tlName);
        final TextInputLayout tlAge = findViewById(R.id.tlAge);
        final TextInputLayout tlPhone = findViewById(R.id.tlTel);

        btnSubmit.setEnabled(false);

        EditText etID = tlID.getEditText();
        etID.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (etID.length() < 5) {
                    tlID.setError("ID는 5자 이상 20자 이하로 작성해주세요.");
                    submitCheck[0] = false;
                } else {
                    tlID.setErrorEnabled(false);
                    tlID.setError(null);
                    submitCheck[0] = true;
                }
                submitEnable();
            }
        });

        EditText etPwd = tlPwd.getEditText();
        EditText etPwdCheck = tlPwdCheck.getEditText();

        etPwd.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (etPwd.length() < 8) {
                    tlPwd.setError("패스워드는 8자 이상 20자 미만의 영어 소문자, 대문자 및 숫자로 입력해주세요.");
                    submitCheck[1] = false;
                } else {
                    tlPwd.setErrorEnabled(false);
                    tlPwd.setError(null);

                    if(!(etPwdCheck.getText().toString().equals(etPwd.getText().toString()))) {
                        tlPwdCheck.setError("패스워드를 확인해주세요.");
                        submitCheck[1] = false;
                    } else {
                        tlPwdCheck.setErrorEnabled(false);
                        tlPwdCheck.setError(null);
                        submitCheck[1] = true;
                    }
                }
                submitEnable();
            }
        });
        etPwdCheck.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(!(etPwdCheck.getText().toString().equals(etPwd.getText().toString()))) {
                    tlPwdCheck.setError("패스워드를 확인해주세요.");
                    submitCheck[1] = false;
                } else {
                    tlPwdCheck.setErrorEnabled(false);
                    tlPwdCheck.setError(null);
                    if(etPwd.length() >= 8) submitCheck[1] = true;
                }
                submitEnable();
            }
        });

        EditText etNick = tlNick.getEditText();
        etNick.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (etNick.length() < 5) {
                    tlNick.setError("닉네임은 5자 이상 20자 미만으로 입력해주세요.");
                    submitCheck[2] = false;
                } else {
                    tlNick.setErrorEnabled(false);
                    tlNick.setError(null);
                    submitCheck[2] = true;
                }
                submitEnable();
            }
        });

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DTO dto = new DTO(
                        tlID.getEditText().getText().toString(),
                        tlPwd.getEditText().getText().toString(),
                        tlNick.getEditText().getText().toString(),
                        tlName.getEditText().getText().toString(),
                        tlAge.getEditText().getText().toString(),
                        tlPhone.getEditText().getText().toString()
                );

                DAO dao = new DAO();
                new Thread() {
                    @Override
                    public void run() {
                        try {
                            dao.createAccount(dto);
                            handler.post(new Runnable() {
                                @Override
                                public void run() {
                                    Toast.makeText(CreateAccount.this, "회원가입 완료!", Toast.LENGTH_SHORT).show();
                                    finish();
                                }
                            });
                        } catch (Exception e) {
                            Toast.makeText(CreateAccount.this, "회원가입 오류 발생. 관리자에 문의바람.", Toast.LENGTH_SHORT).show();
                        }
                    }
                }.start();
            }
        });
    }

    public void submitEnable() {
        if(submitCheck[0] == true && submitCheck[1] == true && submitCheck[2] == true)
            btnSubmit.setEnabled(true);
        else
            btnSubmit.setEnabled(false);
    }
}