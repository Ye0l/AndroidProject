package com.example.androidproject;

import android.os.Handler;
import android.util.Log;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

public class DAO {
    private HttpURLConnection getConnection(URL url) {
        try {
            HttpURLConnection http;
            http = (HttpURLConnection) url.openConnection();
            http.setDefaultUseCaches(false);
            http.setDoInput(true);
            http.setRequestMethod("POST");
            http.setRequestProperty("content-type", "application/x-www-form-urlencoded");

            return http;

        } catch (Exception e) {
            Log.e("", "Error", e);
        }

        return null;
    }

    public void createAccount(DTO dto) {
        try {
            URL setURL = new URL("http://10.0.2.2/createAccount.php");
            HttpURLConnection http = getConnection(setURL);
            StringBuffer buffer = new StringBuffer();
            buffer.append("data").append("=")
                    .append(dto.getId()).append("/")
                    .append(dto.getPwd()).append("/")
                    .append(dto.getNick()).append("/")
                    .append(dto.getName()).append("/")
                    .append(dto.getAge()).append("/")
                    .append(dto.getPhone());
            OutputStreamWriter outStream = new OutputStreamWriter(http.getOutputStream(), "utf-8");
            outStream.write(buffer.toString());
            outStream.flush();
            InputStreamReader tmp = new InputStreamReader(http.getInputStream(), "utf-8");
            final BufferedReader reader = new BufferedReader(tmp);
            while (reader.readLine() != null) {
                System.out.println(reader.readLine());
            }
        } catch (Exception e) {
            Log.e("", "Error", e);
        }
    }

    public DTO login(DTO dto) {
        try {
            URL setURL = new URL("http://10.0.2.2/login.php");
            HttpURLConnection http = getConnection(setURL);
            StringBuffer buffer = new StringBuffer();
            buffer.append("data").append("=")
                    .append(dto.getId()).append("/")
                    .append(dto.getPwd());
            OutputStreamWriter outStream = new OutputStreamWriter(http.getOutputStream(), "utf-8");
            outStream.write(buffer.toString());
            outStream.flush();
            InputStreamReader tmp = new InputStreamReader(http.getInputStream(), "utf-8");
            final BufferedReader reader = new BufferedReader(tmp);
            String str = reader.readLine();;
            final String[] sResult = str.split("/");
            System.out.println(str);
            if(sResult != null)
                if(sResult[0].equals(dto.getId()) && sResult[1].equals(dto.getPwd())) {
                    DTO prefsDto = new DTO(sResult[0], sResult[1], sResult[2]);
                    try {
                        prefsDto.setName(sResult[3]);
                    } catch (Exception e) {
                        System.out.println("null name");
                    }
                    try {
                        prefsDto.setName(sResult[4]);
                    } catch (Exception e) {
                        System.out.println("null age");
                    }
                    try {
                        prefsDto.setName(sResult[5]);
                    } catch (Exception e) {
                        System.out.println("null phone");
                    }
                    try {
                        prefsDto.setIntro(sResult[6]);
                    } catch (Exception e) {
                        System.out.println("null intro");
                    }
                    return prefsDto;
                }
        } catch (Exception e) {
            Log.e("", "Error", e);
            return null;
        }
        return null;
    }
}
