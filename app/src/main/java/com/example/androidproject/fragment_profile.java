package com.example.androidproject;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

public class fragment_profile extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        TextView tvNick = view.findViewById(R.id.profile_tv_nick);
        TextView tvId = view.findViewById(R.id.profile_tv_id);

        tvNick.setText(PreferenceManager.getString(view.getContext(), "nick"));
        tvId.setText(PreferenceManager.getString(view.getContext(), "id"));

        return view;
    }
}