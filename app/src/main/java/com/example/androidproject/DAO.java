package com.example.androidproject;

import android.util.Log;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

public class DAO {
    public HttpURLConnection getConnection(URL url) {
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
            buffer.append("name").append("=")
                    .append(dto.getId()).append("/")
                    .append(dto.getPwd()).append("/")
                    .append(dto.getNick()).append("/")
                    .append(dto.getName()).append("/")
                    .append(dto.getAge()).append("/")
                    .append(dto.getPhone());
            OutputStreamWriter outStream = new OutputStreamWriter(http.getOutputStream(), "euc-kr");
            outStream.write(buffer.toString());
            outStream.flush();
            InputStreamReader tmp = new InputStreamReader(http.getInputStream(), "euc-kr");
            final BufferedReader reader = new BufferedReader(tmp);
            while (reader.readLine() != null) {
                System.out.println(reader.readLine());
            }
        } catch (Exception e) {
            Log.e("", "Error", e);
        }
    }
}
