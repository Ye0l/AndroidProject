package com.example.androidproject;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;

public class fragment_profile extends Fragment {
    private TextView tvNick, tvId, tvIntro;
    private ImageButton btnProfileEdit;
    private TextInputLayout editNick, editIntro;
    private Button editSend;
    private View view;
    private Handler handler = new Handler();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_profile, container, false);

        tvNick = view.findViewById(R.id.profile_tv_nick);
        tvId = view.findViewById(R.id.profile_tv_id);
        tvIntro = view.findViewById(R.id.profile_tvIntro);
        btnProfileEdit = view.findViewById(R.id.profile_btnEdit);

        editNick = view.findViewById(R.id.profileEditNick);
        editNick.setCounterMaxLength(20);
        editNick.setCounterEnabled(true);

        editIntro = view.findViewById(R.id.profileEditIntro);
        editIntro.setCounterMaxLength(20);
        editIntro.setCounterEnabled(true);

        editSend = view.findViewById(R.id.profileEditSend);
        editSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DTO dto = new DTO();
                dto.setId(PreferenceManager.getString(getContext(), "id"));
                dto.setNick(editNick.getEditText().getText().toString());
                dto.setIntro(editIntro.getEditText().getText().toString());
                dto.setPwd(PreferenceManager.getString(getContext(), "pwd"));
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        DAO dao = new DAO();
                        dao.updateAccount(dto);
                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(getContext(), "UPDATE Complete.", Toast.LENGTH_SHORT).show();
                                editNick.setVisibility(View.GONE);
                                editIntro.setVisibility(View.GONE);
                                editSend.setVisibility(View.GONE);
                                tvNick.setVisibility(View.VISIBLE);
                                tvIntro.setVisibility(View.VISIBLE);
                                btnProfileEdit.setVisibility(View.VISIBLE);
                            }
                        });
                    }
                }).start();
            }
        });
        btnProfileEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tvNick.setVisibility(View.GONE);
                tvIntro.setVisibility(View.GONE);
                btnProfileEdit.setVisibility(View.GONE);
                editNick.getEditText().setText(tvNick.getText());
                editIntro.getEditText().setText(tvIntro.getText());
                editNick.setVisibility(View.VISIBLE);
                editIntro.setVisibility(View.VISIBLE);
                editSend.setVisibility(View.VISIBLE);
            }
        });

        refresh();

        return view;
    }
    public void refresh() {
        tvNick.setText(PreferenceManager.getString(view.getContext(), "nick"));
        tvId.setText("@" + PreferenceManager.getString(view.getContext(), "id"));
        tvIntro.setText(PreferenceManager.getString(view.getContext(), "intro"));

    }
}