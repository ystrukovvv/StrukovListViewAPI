package com.example.strukov;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;


public class SyncManager {
    private static SyncManager INSTANCE;
    private static String serverPath = "https://api.github.com/";

    private SyncManager() {

    }

    public static SyncManager GetInstance() {
        if (INSTANCE == null) {
            INSTANCE = new SyncManager();
        }
        return INSTANCE;
    }

    public static String getData(String method) {
        try {
            URL url = new URL(serverPath + method);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            InputStream str = connection.getInputStream();
            BufferedReader rdr = new BufferedReader(new InputStreamReader(str));
            String line = "";
            StringBuilder result = new StringBuilder();
            while ((line = rdr.readLine()) != null) {
                result.append(line);
            }
            rdr.close();
            connection.disconnect();
            return result.toString();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public Bitmap DownloadImage(String path)
    {
        try {
            URL url = new URL(path);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            return BitmapFactory.decodeStream(connection.getInputStream());
        }
        catch (Exception e)
        {
            return null;
        }
    }
}