package com.example.androidproject;

import android.content.Context;
import android.content.SharedPreferences;

public class PreferenceManager {
    public static final String PREFERENCE_NAME = "fence_preference";

    private static final String DEFAULT_VALUE_STRING = "";
    private static final int DEFAULT_VALUE_INT = -1;

    private static SharedPreferences getPreferences(Context context) {
        return context.getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE);
    }

    public static void setString(Context context, String key, String value) {
        SharedPreferences prefs = getPreferences(context);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString(key, value);
        editor.commit();
    }

    public static void setInt(Context context, String key, int value) {
        SharedPreferences prefs = getPreferences(context);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putInt(key, value);
        editor.commit();
    }

    public static String getString(Context context, String key) {
        SharedPreferences prefs = getPreferences(context);
        String value = prefs.getString(key, DEFAULT_VALUE_STRING);
        return value;
    }

    public static int getInt(Context context, String key) {
        SharedPreferences prefs = getPreferences(context);
        int value = prefs.getInt(key, DEFAULT_VALUE_INT);
        return value;
    }

    public static void setDTO(Context context, DTO dto) {
        SharedPreferences prefs = getPreferences(context);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString("id", dto.getId());
        editor.putString("nick", dto.getNick());
        editor.putString("pwd", dto.getPwd());
        editor.putString("name", dto.getName());
        editor.putString("age", dto.getAge());
        editor.putString("phone", dto.getPhone());
        editor.commit();
    }

    public static DTO getDTO(Context context) {
        SharedPreferences prefs = getPreferences(context);
        DTO dto = new DTO(
                prefs.getString("id", DEFAULT_VALUE_STRING),
                prefs.getString("pwd", DEFAULT_VALUE_STRING),
                prefs.getString("nick", DEFAULT_VALUE_STRING),
                prefs.getString("name", DEFAULT_VALUE_STRING),
                prefs.getString("age", DEFAULT_VALUE_STRING),
                prefs.getString("phone", DEFAULT_VALUE_STRING)
        );
        return dto;
    }
}
